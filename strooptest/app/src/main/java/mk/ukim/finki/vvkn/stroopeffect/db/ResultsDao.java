package mk.ukim.finki.vvkn.stroopeffect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.models.Result;

public class ResultsDao  {
    private SQLiteDatabase database;
    private ResultsDbOpenHelper dbHelper;
    private String [] allColumns = { ResultsDbOpenHelper.COLUMN_ID, ResultsDbOpenHelper.COLUMN_GENDER, ResultsDbOpenHelper.COLUMN_AGE,
            ResultsDbOpenHelper.COLUMN_ERROR_LETTERSCONGRUENT, ResultsDbOpenHelper.COLUMN_ERROR_LETTERSINCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSCONGRUENT, ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSINCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT, ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONCONGRUENT, ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONINCONGRUENT};

    public ResultsDao(Context c)
    {
        dbHelper = new ResultsDbOpenHelper(c);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        database.close();
        dbHelper.close();
    }

    public boolean insert(Result r)
    {
        if (r.getId() != null)
        {
            return false;
        }

        long insertedId = database.insert(ResultsDbOpenHelper.TABLE_NAME, null, resultToContentValues(r));
        if (insertedId > 0)
        {
            r.setId(insertedId);
            return true;
        }
        return false;
    }

    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();

        Cursor cursor = database.query(ResultsDbOpenHelper.TABLE_NAME, allColumns,
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                results.add(cursorToResult(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return results;
    }

    protected Result cursorToResult(Cursor cursor) {
        Result result = new Result();
        result.setGender(cursor.getString(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_GENDER)));
        result.setAge(cursor.getString(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_AGE)));
        result.setElapsedTimeLettersCongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSCONGRUENT)));
        result.setErrorPercentageLettersCongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_LETTERSCONGRUENT)));
        result.setElapsedTimeLettersIncongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSINCONGRUENT)));
        result.setErrorPercentageLettersIncongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_LETTERSINCONGRUENT)));

        result.setElapsedTimeEmotionCongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONCONGRUENT)));
        result.setErrorPercentageEmotionCongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT)));
        result.setElapsedTimeEmotionIncongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONINCONGRUENT)));
        result.setErrorPercentageEmotionIncongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT)));
        return result;
    }

    protected ContentValues resultToContentValues(Result r)
    {
        ContentValues contentValues = new ContentValues();
        System.out.println(ResultsDbOpenHelper.COLUMN_GENDER);
        System.out.println(r.getGender());
        System.out.println(ResultsDbOpenHelper.COLUMN_AGE);
        System.out.println(r.getAge());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_LETTERSCONGRUENT);
        System.out.println(r.getErrorPercentageLettersCongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_LETTERSINCONGRUENT);
        System.out.println(r.getErrorPercentageLettersIncongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSCONGRUENT);
        System.out.println(r.getElapsedTimeLettersCongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSINCONGRUENT);
        System.out.println(r.getElapsedTimeLettersIncongruent());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT);
        System.out.println(r.getErrorPercentageEmotionCongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT);
        System.out.println(r.getErrorPercentageEmotionIncongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONCONGRUENT);
        System.out.println(r.getElapsedTimeEmotionCongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONINCONGRUENT);
        System.out.println(r.getElapsedTimeEmotionIncongruent());


        contentValues.put(ResultsDbOpenHelper.COLUMN_GENDER, r.getGender());
        contentValues.put(ResultsDbOpenHelper.COLUMN_AGE, r.getAge());

        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_LETTERSCONGRUENT, r.getErrorPercentageLettersCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_LETTERSINCONGRUENT, r.getErrorPercentageLettersIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSCONGRUENT, r.getElapsedTimeLettersCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_LETTERSINCONGRUENT, r.getElapsedTimeLettersIncongruent());

        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT, r.getErrorPercentageEmotionCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT, r.getErrorPercentageEmotionIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONCONGRUENT, r.getElapsedTimeEmotionCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_EMOTIONINCONGRUENT, r.getElapsedTimeEmotionIncongruent());
        return contentValues;
    }

}
