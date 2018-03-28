package com.example.niyati.demodatafiles;

import android.provider.BaseColumns;

/**
 * Created by Niyati on 2018/3/28.
 */

//创建"契约"类，存放数据库各个表，及其相关属性
public class Contract {

    //私有化，防止意外的实例化此类
    private Contract() {
    }
    //为User表创建一个匿名类，声明其中属性
    public static class User{
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
    }
}
