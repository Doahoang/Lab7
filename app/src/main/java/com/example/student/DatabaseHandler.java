package com.example.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "student_db";
    private static final int DB_VERSION = 1;

    private static final String TABLE = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                EMAIL + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // ➕ THÊM
    public void addStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, s.getName());
        cv.put(EMAIL, s.getEmail());
        db.insert(TABLE, null, cv);
        db.close();
    }

    // 📥 LẤY TẤT CẢ
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (c.moveToFirst()) {
            do {
                list.add(new Student(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2)
                ));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // ✏️ CẬP NHẬT
    public void updateStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, s.getName());
        cv.put(EMAIL, s.getEmail());
        db.update(TABLE, cv, ID + "=?",
                new String[]{String.valueOf(s.getId())});
        db.close();
    }

    // 🗑️ XÓA
    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
