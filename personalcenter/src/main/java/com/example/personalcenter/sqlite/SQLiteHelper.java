package com.example.personalcenter.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    public static String DB_NAME = "java.db";
    public static final String U_USERINFO = "userinfo";// 个人资料


    public SQLiteHelper(@Nullable Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 创建个人信息表
        db.execSQL("CREATE TABLE  IF NOT EXISTS " + U_USERINFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, "// 用户名
                + "nickName VARCHAR, "// 昵称
                + "sex VARCHAR, "// 性别
                + "signature VARCHAR"// 签名
                + ")");
//        db.execSQL("insert into userinfo values (null,'刘波')");
//        db.execSQL("insert into userinfo values (null,'java学习大牛')");
//        db.execSQL("insert into userinfo values (null,'男')");
//        db.execSQL("insert into userinfo values (null,'欢迎来到java世界')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);

        onCreate(db);
    }
}
