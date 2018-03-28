package com.example.niyati.demodatafiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLDataException;

/**
 * Created by Niyati on 2018/3/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatebaseHelper";

    //数据库版本
    private static final int DATABASE_VERSION = 1;
    //数据库名称
    private static final String DATABASE_NAME = "whd_db";

    //组装创建表的SQL语句
    private static final String SQL_CREATE =
            "create table " + Contract.User.TABLE_NAME + " ( " +
                    Contract.User.COLUMN_ID + " integer primary key," +
                    Contract.User.COLUMN_NAME + " text)";

    //组装删除表的语句，一般用于升级数据库时，删除已有的表，防止无法更新数据库
    private static final String SQL_DELETE=
            "DROP TABLE IF EXISTS " + Contract.User.TABLE_NAME;

    //构造函数，必须有
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //context：上下文对象
    //name：数据库名称
    //param：factory
    //version：当前数据库版本，值递增

    //第一次创建数据库时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: "+ "创建数据库和表");
        //执行sql语句
        db.execSQL(SQL_CREATE);

    }

    //升级数据库时调用（数据库版本发生变化时）
    //数据库版本只能递增！
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
        Log.d(TAG, "onUpgrade: "+ "更新数据库，当前版本号：" + newVersion);
    }
}
