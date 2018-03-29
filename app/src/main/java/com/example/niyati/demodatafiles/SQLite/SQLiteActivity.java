package com.example.niyati.demodatafiles.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.niyati.demodatafiles.R;

/**
 * Created by Niyati on 2018/3/27.
 */

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper helper;
    private SQLiteDatabase database;
    private static int VERSION = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //初始化
        initViews();
        //创建DatebaseHelp对象，传入context
        helper = new DatabaseHelper(this);

    }
    //初始化按钮组件，设置监听
    private void initViews(){
        findViewById(R.id.createDatabase).setOnClickListener(this);
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //创建数据库
            case R.id.createDatabase:
                helper.getWritableDatabase();
                break;
            //插入数据
            case R.id.insert:
                database = helper.getWritableDatabase();
                //通过ContentValues对象，以键值对的方式存储
                ContentValues values = new ContentValues();
                values.put(Contract.User.COLUMN_ID,1);
                values.put(Contract.User.COLUMN_NAME,"whdalive");
                //插入数据到数据库
                database.insert(Contract.User.TABLE_NAME,null,values);
                // 参数1：要操作的表名称
                // 参数2：SQl不允许一个空列，若ContentValues是空，那么这一列被明确的指明为NULL值
                // 参数3：ContentValues对象

                //用完关闭数据库
                database.close();
                //或者通过的execSQL()，再里面直接输入SQL语句
                //不过缺点在于可能被恶意SQL注入
                //database.execSQL("insert into user values(?,?)",new String[]{String.valueOf(2),"whdalive"});
                //Android studio 3.0上会提示(?,?)部分有错误，但是实际编译没问题，不知道是什么原因
                break;
            //删除数据
            case R.id.delete:
                database = helper.getWritableDatabase();
                String selection = Contract.User.COLUMN_ID + " = ?";
                String[] selectionArgs = {String.valueOf(1)};
                database.delete(Contract.User.TABLE_NAME,selection,selectionArgs);
                // 参数1：表名(String)
                // 参数2：WHERE表达式（String），需删除数据的行； 若该参数为 null, 就会删除所有行；？号是占位符
                // 参数3：WHERE选择语句的参数(String[]), 逐个替换 WHERE表达式中 的“？”占位符;


                //database.execSQL("delete from user where id = ?",new String[]{String.valueOf(1)});
                database.close();
                break;
            //更新数据
            case R.id.update:
                database = helper.getWritableDatabase();
                ContentValues values1 = new ContentValues();
                values1.put(Contract.User.COLUMN_NAME,"chuang");
                String selection1 = Contract.User.COLUMN_ID + " = ?";
                String[] selectionArgs1 = {String.valueOf(1)};
                database.update(Contract.User.TABLE_NAME,values1,selection1,selectionArgs1);
                // 参数1：表名(String)
                // 参数2：需修改的ContentValues对象
                // 参数3：WHERE表达式（String），需数据更新的行； 若该参数为 null, 就会修改所有行；？号是占位符
                // 参数4：WHERE选择语句的参数(String[]), 逐个替换 WHERE表达式中 的“？”占位符;

                //database.execSQL("update user set name = ? where id = ?", new String[]{String.valueOf(1),"chuang"});
                database.close();
                break;
            //查询数据
            case R.id.query:
                database = helper.getWritableDatabase();
                String[] projection = {Contract.User.COLUMN_ID, Contract.User.COLUMN_NAME};
                Cursor cursor = database.query(Contract.User.TABLE_NAME,projection,null,null,null,null,null);
                // 参数说明
                // table：要操作的表
                // columns：查询的列所有名称集
                // selection：WHERE之后的条件语句，可以使用占位符
                // groupBy：指定分组的列名
                // having指定分组条件，配合groupBy使用
                // orderBy指定排序的列名
                // limit指定分页参数
                // distinct可以指定“true”或“false”表示要不要过滤重复值

                //SQL输入方式
                //Cursor cursor = database.rawQuery("select * from user",null);
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_NAME));
                    int id = cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_ID));
                    Log.d("11111", "查询结果： id:"+id+" name: " + name );
                }
                /*Cursor对象常用方法如下：
                cursor.move(int offset); //以当前位置为参考,移动到指定行
                cursor.moveToFirst();    //移动到第一行
                cursor.moveToLast();     //移动到最后一行
                cursor.moveToPosition(int position); //移动到指定行
                cursor.moveToPrevious(); //移动到前一行
                cursor.moveToNext();     //移动到下一行
                cursor.isFirst();        //是否指向第一条
                cursor.isLast();     //是否指向最后一条
                cursor.isBeforeFirst();  //是否指向第一条之前
                cursor.isAfterLast();    //是否指向最后一条之后
                cursor.isNull(int columnIndex);  //指定列是否为空(列基数为0)
                cursor.isClosed();       //游标是否已关闭
                cursor.getCount();       //总数据项数
                cursor.getPosition();    //返回当前游标所指向的行数
                cursor.getColumnIndex(String columnName);//返回某列名对应的列索引值
                cursor.getString(int columnIndex);   //返回当前行指定列的值
                */

                cursor.close();
                database.close();
                break;
        }
    }
}
