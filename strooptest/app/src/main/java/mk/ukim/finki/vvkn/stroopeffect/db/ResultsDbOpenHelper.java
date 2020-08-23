package mk.ukim.finki.vvkn.stroopeffect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResultsDbOpenHelper extends SQLiteOpenHelper {
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


    public ResultsDbOpenHelper(Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
