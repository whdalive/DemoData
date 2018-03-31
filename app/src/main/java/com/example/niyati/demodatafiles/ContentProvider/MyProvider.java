package com.example.niyati.demodatafiles.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvider extends ContentProvider {
    private Context mContext;
    DBHelper mDBHelper = null;
    SQLiteDatabase mDatabase = null;

    public static final String AUTHORITY = "com.example.niyati.myprovider";
    public static final int player_code = 1;
    public static final int team_code = 2;

    private static UriMatcher mMatcher;
    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        mMatcher.addURI(AUTHORITY,"player",player_code);
        mMatcher.addURI(AUTHORITY,"team",team_code);
    }
    @Override
    public boolean onCreate() {

        mContext = getContext();

        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();

        mDatabase.execSQL("delete from player");
        mDatabase.execSQL("insert into player values(1,'Kobe')");
        mDatabase.execSQL("insert into player values(2,'Iverson')");

        mDatabase.execSQL("delete from team");
        mDatabase.execSQL("insert into team values(1,'Lakers')");
        mDatabase.execSQL("insert into team values(2,'76ers')");
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableName(uri);

        mDatabase.insert(table,null,values);

        mContext.getContentResolver().notifyChange(uri,null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);

        return mDatabase.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    private String getTableName(Uri uri){
        String name = null;
        switch (mMatcher.match(uri)){
            case player_code:
                name = DBHelper.PLAYER_TABLE_NAME;
                break;
            case team_code:
                name = DBHelper.TEAM_TABLE_NAME;
                break;
        }
        return name;
    }
}
