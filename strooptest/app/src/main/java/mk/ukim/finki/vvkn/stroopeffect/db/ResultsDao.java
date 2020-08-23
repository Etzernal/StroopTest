package mk.ukim.finki.vvkn.stroopeffect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.JsonToken;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.models.Result;

public class ResultsDao  {
    private SQLiteDatabase database;
    private ResultsDbOpenHelper dbHelper;
    private String [] allColumns = { ResultsDbOpenHelper.COLUMN_ID, ResultsDbOpenHelper.COLUMN_GENDER, ResultsDbOpenHelper.COLUMN_AGE,
            ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACNEUTRAL, ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACNEUTRAL,
            ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACMIXED, ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACMIXED,
            ResultsDbOpenHelper.COLUMN_ERROR_WARPEDCONGRUENT, ResultsDbOpenHelper.COLUMN_TIME_WARPEDCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ERROR_WARPEDINCONGRUENT, ResultsDbOpenHelper.COLUMN_TIME_WARPEDINCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ERROR_WARPEDMIXED, ResultsDbOpenHelper.COLUMN_TIME_WARPEDMIXED,
            ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACNEUTRAL, ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACNEUTRAL,
            ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACMIXED, ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACMIXED,
            ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT, ResultsDbOpenHelper.COLUMN_TIME_EMOTIONCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT, ResultsDbOpenHelper.COLUMN_TIME_EMOTIONINCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONMIXED, ResultsDbOpenHelper.COLUMN_TIME_EMOTIONMIXED};

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
                System.out.println("HELLO");
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

        result.setElapsedTimeWarpedPracNeutral(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACNEUTRAL)));
        result.setElapsedTimeWarpedPracMixed(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACMIXED)));
        result.setElapsedTimeWarpedCongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_WARPEDCONGRUENT)));
        result.setElapsedTimeWarpedIncongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_WARPEDINCONGRUENT)));
        result.setElapsedTimeWarpedMixed(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_WARPEDMIXED)));

        result.setErrorPercentageWarpedPracNeutral(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACNEUTRAL)));
        result.setErrorPercentageWarpedPracMixed(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACMIXED)));
        result.setErrorPercentageWarpedCongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDCONGRUENT)));
        result.setErrorPercentageWarpedIncongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDINCONGRUENT)));
        result.setErrorPercentageWarpedMixed(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDMIXED)));

        result.setElapsedTimeEmotionPracNeutral(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACNEUTRAL)));
        result.setElapsedTimeEmotionPracMixed(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACMIXED)));
        result.setElapsedTimeEmotionCongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONCONGRUENT)));
        result.setElapsedTimeEmotionIncongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONINCONGRUENT)));
        result.setElapsedTimeEmotionMixed(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONMIXED)));

        result.setErrorPercentageEmotionPracNeutral(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACNEUTRAL)));
        result.setErrorPercentageEmotionPracMixed(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACMIXED)));
        result.setErrorPercentageEmotionCongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT)));
        result.setErrorPercentageEmotionIncongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT)));
        result.setErrorPercentageEmotionMixed(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONMIXED)));

        return result;
    }

    protected ContentValues resultToContentValues(Result r)
    {
        ContentValues contentValues = new ContentValues();
        System.out.println(ResultsDbOpenHelper.COLUMN_GENDER);
        System.out.println(r.getGender());
        System.out.println(ResultsDbOpenHelper.COLUMN_AGE);
        System.out.println(r.getAge());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACNEUTRAL);
        System.out.println(r.getErrorPercentageWarpedPracNeutral());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACNEUTRAL);
        System.out.println(r.getElapsedTimeWarpedPracNetural());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACMIXED);
        System.out.println(r.getErrorPercentageWarpedPracMixed());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACMIXED);
        System.out.println(r.getElapsedTimeWarpedPracMixed());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDCONGRUENT);
        System.out.println(r.getErrorPercentageWarpedCongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_WARPEDCONGRUENT);
        System.out.println(r.getElapsedTimeWarpedCongruent());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDINCONGRUENT);
        System.out.println(r.getErrorPercentageWarpedIncongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_WARPEDINCONGRUENT);
        System.out.println(r.getElapsedTimeWarpedIncongruent());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDMIXED);
        System.out.println(r.getErrorPercentageWarpedMixed());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_WARPEDMIXED);
        System.out.println(r.getElapsedTimeWarpedMixed());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACNEUTRAL);
        System.out.println(r.getErrorPercentageEmotionPracNeutral());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACNEUTRAL);
        System.out.println(r.getElapsedTimeEmotionPracNeutral());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACMIXED);
        System.out.println(r.getErrorPercentageEmotionPracMixed());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACMIXED);
        System.out.println(r.getElapsedTimeEmotionPracMixed());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT);
        System.out.println(r.getErrorPercentageEmotionCongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONCONGRUENT);
        System.out.println(r.getElapsedTimeEmotionCongruent());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT);
        System.out.println(r.getErrorPercentageEmotionIncongruent());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONINCONGRUENT);
        System.out.println(r.getElapsedTimeEmotionIncongruent());

        System.out.println(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONMIXED);
        System.out.println(r.getErrorPercentageEmotionMixed());
        System.out.println(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONMIXED);
        System.out.println(r.getElapsedTimeEmotionMixed());

        contentValues.put(ResultsDbOpenHelper.COLUMN_GENDER, r.getGender());
        contentValues.put(ResultsDbOpenHelper.COLUMN_AGE, r.getAge());

        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACNEUTRAL, r.getErrorPercentageWarpedPracNeutral());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDPRACMIXED, r.getErrorPercentageWarpedPracMixed());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDCONGRUENT, r.getErrorPercentageWarpedCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDINCONGRUENT, r.getErrorPercentageWarpedIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_WARPEDMIXED, r.getErrorPercentageWarpedMixed());

        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACNEUTRAL, r.getElapsedTimeWarpedPracNetural());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_WARPEDPRACMIXED, r.getElapsedTimeWarpedPracMixed());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_WARPEDCONGRUENT, r.getElapsedTimeWarpedCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_WARPEDINCONGRUENT, r.getElapsedTimeWarpedIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_WARPEDMIXED, r.getElapsedTimeWarpedMixed());

        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACNEUTRAL, r.getErrorPercentageEmotionPracNeutral());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONPRACMIXED, r.getErrorPercentageEmotionPracMixed());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONCONGRUENT, r.getErrorPercentageEmotionCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONINCONGRUENT, r.getErrorPercentageEmotionIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_EMOTIONMIXED, r.getErrorPercentageEmotionMixed());

        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACNEUTRAL, r.getElapsedTimeEmotionPracNeutral());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONPRACMIXED, r.getElapsedTimeEmotionPracMixed());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONCONGRUENT, r.getElapsedTimeEmotionCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONINCONGRUENT, r.getElapsedTimeEmotionIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_TIME_EMOTIONMIXED, r.getElapsedTimeEmotionMixed());

        return contentValues;
    }

}
