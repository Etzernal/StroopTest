package mk.ukim.finki.vvkn.stroopeffect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResultsDbOpenHelper extends SQLiteOpenHelper {
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_ERROR_LETTERSCONGRUENT = "errorLettersCongruent";
        public static final String COLUMN_ERROR_LETTERSINCONGRUENT = "errorLettersIncongruent";
        public static final String COLUMN_ELAPSED_TIME_LETTERSCONGRUENT = "elapsedTimeLettersCongruent";
        public static final String COLUMN_ELAPSED_TIME_LETTERSINCONGRUENT = "elapsedTimeLettersIncongruent";
        public static final String COLUMN_ERROR_EMOTIONCONGRUENT = "errorEmotionCongruent";
        public static final String COLUMN_ERROR_EMOTIONINCONGRUENT = "errorEmotionIncongruent";
        public static final String COLUMN_ELAPSED_TIME_EMOTIONCONGRUENT = "elapsedTimeEmotionCongruent";
        public static final String COLUMN_ELAPSED_TIME_EMOTIONINCONGRUENT = "elapsedTimeEmotionIncongruent";
        public static final String TABLE_NAME = "Results";

    private static final String DATABASE_NAME = "helloworld.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = String.format("create table %s (" +
        "%s integer primary key autoincrement, %s text not null, %s text, " +
        "%s real, %s real, %s integer, %s integer, %s real, %s real, %s integer, %s integer);",
            TABLE_NAME, COLUMN_ID, COLUMN_GENDER, COLUMN_AGE, COLUMN_ERROR_LETTERSCONGRUENT, COLUMN_ERROR_LETTERSINCONGRUENT,
            COLUMN_ELAPSED_TIME_LETTERSCONGRUENT, COLUMN_ELAPSED_TIME_LETTERSINCONGRUENT, COLUMN_ERROR_EMOTIONCONGRUENT,
            COLUMN_ERROR_EMOTIONINCONGRUENT, COLUMN_ELAPSED_TIME_EMOTIONCONGRUENT, COLUMN_ELAPSED_TIME_EMOTIONINCONGRUENT);


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
