package com.sourcey.materiallogindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="deadliono.db";
    public static final String USERS_TABLE="Users";
    public static final String USER_COL_1="EMAIL";
    public static final String USER_COL_2="NAME";
    public static final String USER_COL_3="COURSES";
    public static final String USER_COL_4="INSTRUCTOR";
    public static final String USER_COL_5="PASSWORD";
    public static final String DEADLINES_TABLE="Deadlines";
    public static final String DEADLINE_COL_1="ID";
    public static final String DEADLINE_COL_2="COURSE_ID";
    public static final String DEADLINE_COL_3="DATE";
    public static final String DEADLINE_COL_4="INSTRUCTOR";
    public static final String DEADLINE_COL_5="DESCRIPTION";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USERS_TABLE +" (EMAIL TEXT PRIMARY KEY,NAME TEXT,COURSES TEXT,INSTRUCTOR BOOLEAN,PASSWORD TEXT)");
        db.execSQL("create table " + DEADLINES_TABLE +" (ID TEXT PRIMARY KEY,COURSE_ID TEXT,DATE TEXT,INSTRUCTOR TEXT,DESCRIPTION TEXT)");}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+DEADLINES_TABLE);
        onCreate(db);
    }
    public boolean insertUserData(String email, String name,boolean instructor,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1,email);
        contentValues.put(USER_COL_2,name);
        contentValues.put(USER_COL_3,"");
        contentValues.put(USER_COL_4,instructor);
        contentValues.put(USER_COL_5,password);
        long result = db.insert(USERS_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDeadlineData(String id, String course_id, String date, String instructor,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEADLINE_COL_1,id);
        contentValues.put(DEADLINE_COL_2,course_id);
        contentValues.put(DEADLINE_COL_3,date);
        contentValues.put(DEADLINE_COL_4,instructor);
        contentValues.put(DEADLINE_COL_5,description);
        long result = db.insert(DEADLINES_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select USER_COL_1, USER_COL_2, USER_COL_3 ,USER_COL_4  from "+USERS_TABLE ,null);
        return res;
    }
    public Cursor getAllDeadlines() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DEADLINES_TABLE,null);
        return res;
    }

    public boolean authenticateLogin(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + USERS_TABLE + " WHERE EMAIL='?' AND PASSWORD='?';",
                new String[]{email,password});
        if (mCursor != null && mCursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateUserData(String email,String name,String courses,boolean instructor,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1,email);
        contentValues.put(USER_COL_2,name);
        contentValues.put(USER_COL_3,courses);
        contentValues.put(USER_COL_4,instructor);
        contentValues.put(USER_COL_5,password);

        db.update(USERS_TABLE, contentValues, "ID = ?",new String[] { email });
        return true;
    }

    public boolean updateDeadlineData(String id,String course_id,String date,String instructor,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEADLINE_COL_1,id);
        contentValues.put(DEADLINE_COL_2,course_id);
        contentValues.put(DEADLINE_COL_3,date);
        contentValues.put(DEADLINE_COL_4,instructor);
        contentValues.put(DEADLINE_COL_5,description);
        db.update(DEADLINES_TABLE, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteUserData (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE, "ID = ?",new String[] {email});
    }

    public Integer deleteDeadlineData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DEADLINES_TABLE, "ID = ?",new String[] {id});
    }
}
