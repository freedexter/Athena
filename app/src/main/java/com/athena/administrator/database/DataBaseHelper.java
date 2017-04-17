package com.athena.administrator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.athena.administrator.athena.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class DataBaseHelper extends SQLiteOpenHelper{

    static String dbname="daydayup.db";
    static int dbVersion=1;
    static String usertable = "user";

    Context context;
    private SQLiteDatabase myDataBase;

    private final int BUFFER_SIZE = 400000;

    public static final String PACKAGE_NAME = "com.athena.administrator.athena";//包名
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME+"/databases"; //在手机里存放数据库的位置
    public static final String DB_FILE = DB_PATH+"/"+dbname;

    private static DataBaseHelper instance;//这里主要解决死锁问题,是static就能解决死锁问题

    private DataBaseHelper(Context context) {
        super(context, dbname, null, dbVersion);
        this.context = context;
    }
    public synchronized static DataBaseHelper getDataBaseHelper(Context context){
        if(instance==null)
            instance=new DataBaseHelper(context);
        return instance;
    }

    public void onCreate(SQLiteDatabase db) {
        String strSql;
/*
        strSql="create table  "+usertable+" ( username varchar(20) primary key NOT NULL UNIQUE,password varchar(20),stat varchar(5))";
        db.execSQL(strSql);

        strSql="Insert into "+usertable+" values('admin','admin','00')";
        db.execSQL(strSql);


        strSql="create table  "+tool_dtltable+" ( sourcefrom varchar(20) NOT NULL, toolname varchar(20) NOT NULL, trandate varchar(8) NOT NULL, currency varchar(4),"
                                            +"number integer ,soldnumber integer ,totprice real,feepercent real,feeprice real,exchangerate real,"
                                            +"rmb_totprice real,rmb_singprice real,transprice real ,singlesum real,weight integer ,totweight integer ,"
                                            +"beizhu varchar(255) ,user varchar(20) )";
        //Log.i("tips", strSql);
      strIdx="CREATE UNIQUE INDEX  idx_tool_dlt ON tool_dtl( sourcefrom, toolname, trandate )";
       // Log.i("tips", strIdx);
       db.execSQL(strSql);
       db.execSQL(strIdx);

       strSql="create table  "+tool_ordertable+" ( customer varchar(10) NOT NULL, orderdate varchar(8) NOT NULL, toolsource varchar(20) NOT NULL, toolname varchar(20) NOT NULL,"
                                              +"trandate varchar(8),number integer ,toolprice real,exchangerate real,toolbuyprice real,toolflag varchar(2),user varchar(20))";
       strIdx="CREATE UNIQUE INDEX  idx_tool_order ON tool_order( customer, orderdate, toolsource, toolname )";
       db.execSQL(strSql);
       // Log.i("tips", strSql);
       db.execSQL(strIdx);
       // Log.i("tips", strIdx);

       strSql="create table  "+custom_ordertable+" ( customer varchar(10) NOT NULL, orderdate varchar(8) NOT NULL, number integer, totprice real,"
                +"needprice real,realprice real,paychnl varchar(20),exprprice real,exprnum varchar(25),exprdate varchar(8),user varchar(20))";
       strIdx="CREATE UNIQUE INDEX  idx_custom_order ON custom_order( customer, orderdate )";
       db.execSQL(strSql);
       // Log.i("tips", strSql);
        db.execSQL(strIdx);
       // Log.i("tips", strIdx);

        try {
            this.initDataBase();
           // myDataBase.endTransaction();
           // myDataBase.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        try {
            this.initDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void  initDataBase() throws IOException {

        // 打开静态数据库文件的输入流
        InputStream is = context.getResources().openRawResource( R.raw.daydayup );
        // 通过Context类来打开目标数据库文件的输出流，这样可以避免将路径写死。

        //     File f=new File(DB_FILE);
        //    if( !f.exists() ) {
        //     myDataBase.endTransaction();
        //   myDataBase.beginTransaction();
        FileOutputStream os = new FileOutputStream(DB_FILE);
        Log.i("tips", DB_FILE);
        byte[] buffer = new byte[BUFFER_SIZE];
        int count = 0;
        // 将静态数据库文件拷贝到目的地
        while ((count = is.read(buffer)) > 0) {
            os.write(buffer, 0, count);
        }
        is.close();
        os.close();
        myDataBase.openOrCreateDatabase(DB_FILE, null);
        //    }
        return;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}


}