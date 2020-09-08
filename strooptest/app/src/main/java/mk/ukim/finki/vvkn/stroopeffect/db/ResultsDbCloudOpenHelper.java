package mk.ukim.finki.vvkn.stroopeffect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener("Creates a connection pool that is stored in the Servlet's context for later use.")
public class ResultsDbCloudOpenHelper implements ServletContextListener   {

    private static final String CLOUD_SQL_CONNECTION_NAME =
            System.getenv("practice-48fa2:asia-southeast1:stroop-test");
    private static final String DB_USER = System.getenv("stroop-user");
    private static final String DB_PASS = System.getenv("WarpedEmotion@234");
    private static final String DB_NAME = System.getenv("Results");

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_AGE = "age";

    public static final String COLUMN_ERROR_WARPEDPRACNEUTRAL = "errorWarpedPracNeutral";
    public static final String COLUMN_ERROR_WARPEDPRACMIXED = "errorWarpedPracMixed";
    public static final String COLUMN_ERROR_WARPEDCONGRUENT = "errorWarpedCongruent";
    public static final String COLUMN_ERROR_WARPEDINCONGRUENT = "errorWarpedIncongruent";
    public static final String COLUMN_ERROR_WARPEDMIXED = "errorWarpedMixed";

    public static final String COLUMN_TIME_WARPEDPRACNEUTRAL = "elapsedTimeWarpedPracNeutral";
    public static final String COLUMN_TIME_WARPEDPRACMIXED = "elapsedTimeWarpedPracMixed";
    public static final String COLUMN_TIME_WARPEDCONGRUENT = "elapsedTimeWarpedCongruent";
    public static final String COLUMN_TIME_WARPEDINCONGRUENT = "elapsedTimeWarpedIncongruent";
    public static final String COLUMN_TIME_WARPEDMIXED = "elapsedTimeWarpedMixed";

    public static final String COLUMN_ERROR_EMOTIONPRACNEUTRAL = "errorEmotionPracNeutral";
    public static final String COLUMN_ERROR_EMOTIONPRACMIXED = "errorEmotionPracMixed";
    public static final String COLUMN_ERROR_EMOTIONCONGRUENT = "errorEmotionCongruent";
    public static final String COLUMN_ERROR_EMOTIONINCONGRUENT = "errorEmotionIncongruent";
    public static final String COLUMN_ERROR_EMOTIONMIXED = "errorEmotionMixed";

    public static final String COLUMN_TIME_EMOTIONPRACNEUTRAL = "elapsedTimeEmotionPracNeutral";
    public static final String COLUMN_TIME_EMOTIONPRACMIXED = "elapsedTimeEmotionPracMixed";
    public static final String COLUMN_TIME_EMOTIONCONGRUENT = "elapsedTimeEmotionCongruent";
    public static final String COLUMN_TIME_EMOTIONINCONGRUENT = "elapsedTimeEmotionIncongruent";
    public static final String COLUMN_TIME_EMOTIONMIXED = "elapsedTimeEmotionMixed";

    public static final String TABLE_NAME = "Results";

    private static final String DATABASE_NAME = "tryagain3.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = String.format("create table %s (" +
                    "%s integer primary key autoincrement, %s text not null, %s text, " +
                    "%s real, %s integer, %s real, %s integer, " +
                    "%s real, %s integer, %s real, %s integer, " +
                    "%s real, %s integer, %s real, %s integer, " +
                    "%s real, %s integer, %s real, %s integer, " +
                    "%s real, %s integer, %s real, %s integer);",
            TABLE_NAME, COLUMN_ID, COLUMN_GENDER, COLUMN_AGE,
            COLUMN_ERROR_WARPEDPRACNEUTRAL, COLUMN_TIME_WARPEDPRACNEUTRAL, COLUMN_ERROR_WARPEDPRACMIXED, COLUMN_TIME_WARPEDPRACMIXED,
            COLUMN_ERROR_WARPEDCONGRUENT, COLUMN_TIME_WARPEDCONGRUENT, COLUMN_ERROR_WARPEDINCONGRUENT, COLUMN_TIME_WARPEDINCONGRUENT,
            COLUMN_ERROR_WARPEDMIXED, COLUMN_TIME_WARPEDMIXED, COLUMN_ERROR_EMOTIONPRACNEUTRAL, COLUMN_TIME_EMOTIONPRACNEUTRAL,
            COLUMN_ERROR_EMOTIONPRACMIXED, COLUMN_TIME_EMOTIONPRACMIXED, COLUMN_ERROR_EMOTIONCONGRUENT, COLUMN_TIME_EMOTIONCONGRUENT,
            COLUMN_ERROR_EMOTIONINCONGRUENT, COLUMN_TIME_EMOTIONINCONGRUENT, COLUMN_ERROR_EMOTIONMIXED, COLUMN_TIME_EMOTIONMIXED);

    private DataSource createConnectionPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
        config.setUsername(DB_USER); // e.g. "root", "postgres"
        config.setPassword(DB_PASS); // e.g. "my-password"
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(10000); // 10 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes
        DataSource pool = new HikariDataSource(config);
        return pool;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createTable(DataSource pool) throws SQLException {
        // Safely attempt to create the table schema.
        try (Connection conn = pool.getConnection()) {
//            String stmt =
//                    "CREATE TABLE IF NOT EXISTS votes ( "
//                            + "vote_id SERIAL NOT NULL, time_cast timestamp NOT NULL, candidate CHAR(6) NOT NULL,"
//                            + " PRIMARY KEY (vote_id) );";
            try (PreparedStatement createTableStatement = conn.prepareStatement(DATABASE_CREATE);) {
                createTableStatement.execute();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // This function is called when the Servlet is destroyed.
        HikariDataSource pool = (HikariDataSource) event.getServletContext().getAttribute("my-pool");
        if (pool != null) {
            pool.close();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // This function is called when the application starts and will safely create a connection pool
        // that can be used to connect to.
        ServletContext servletContext = event.getServletContext();
        DataSource pool = (DataSource) servletContext.getAttribute("my-pool");
        if (pool == null) {
            pool = createConnectionPool();
            servletContext.setAttribute("my-pool", pool);
        }
        try {
            createTable(pool);
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Unable to verify table schema. Please double check the steps"
                            + "in the README and try again.",
                    ex);
        }
    }

}
