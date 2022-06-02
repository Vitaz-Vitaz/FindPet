package com.example.findpet.ForData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.Point2;

import java.util.ArrayList;

public class DB2 {
    private static final String DATABASE_NAME = "points22.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "ForPoints2";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Descriptions = "Descriptions";
    private static final String COLUMN_Image = "Image";
    private static final String COLUMN_X = "X";
    private static final String COLUMN_Y = "Y";
    private static final String COLUMN_PetName = "PetName";
    private static final String COLUMN_PetColor = "PetColor";
    private static final String COLUMN_PetContact = "PetContact";
    private static final String COLUMN_PetStatus = "PetStatus";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_Descriptions = 1;
    private static final int NUM_COLUMN_Image = 2;
    private static final int NUM_COLUMN_X = 3;
    private static final int NUM_COLUMN_Y = 4;
    private static final int NUM_COLUMN_PetName = 5;
    private static final int NUM_COLUMN_PetColor = 6;
    private static final int NUM_COLUMN_PetContact = 7;
    private static final int NUM_COLUMN_PetStatus = 8;
    private SQLiteDatabase MyDataBase;
    public DB2(Context context) {
        DB2.OpenHelper2 mOpenHelper = new DB2.OpenHelper2(context);
        MyDataBase = mOpenHelper.getWritableDatabase();
    }
    public long insert(Point2 p) {
        //String f = p.photo.toString();
        //Uri g = Uri.parse(f);


        ContentValues cv=new ContentValues();
        //  cv.put(COLUMN_ID, p.id);
        cv.put(COLUMN_Descriptions, p.description);
        cv.put(COLUMN_Image, p.photo);
        cv.put(COLUMN_X, p.x);
        Log.e("DB2", "insert2");
        cv.put(COLUMN_Y, p.y);
        cv.put(COLUMN_PetName, p.petName);
        cv.put(COLUMN_PetColor, p.petColor);
        cv.put(COLUMN_PetContact, p.petContact);
        cv.put(COLUMN_PetStatus, p.status);
        return MyDataBase.insert(TABLE_NAME, null, cv);
    }
//    public int update(NewPoint p) {
//        ContentValues cv=new ContentValues();
//        cv.put(COLUMN_Descriptions, p.description);
//        cv.put(COLUMN_Image, p.photo.toString());
//        cv.put(COLUMN_X, p.x);
//        cv.put(COLUMN_Y, p.y);
//        return MyDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(Data.x.indexOf(p.x))});
//    }

    public void deleteAll() {
        MyDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        MyDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }


    public ArrayList<Point2> findPointsBySpecialParametr(String status2){
        ArrayList<Point2> arrPoint = new ArrayList<>();
        ArrayList<Point2> arrPoint2 = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = MyDataBase.query(TABLE_NAME,   null, null, null, null, null, null);
        if(cursor!=null && cursor.getCount()>0 && cursor.moveToFirst()){
            cursor.moveToFirst();

            do {
                long id = cursor.getLong(NUM_COLUMN_ID);
                String des = cursor.getString(NUM_COLUMN_Descriptions);
                String photo = cursor.getString(NUM_COLUMN_Image);
                double x = cursor.getDouble(NUM_COLUMN_X );
                double y = cursor.getDouble(NUM_COLUMN_Y);

                String petName = cursor.getString(NUM_COLUMN_PetName);
                String petColor = cursor.getString(NUM_COLUMN_PetColor);
                String petContact = cursor.getString(NUM_COLUMN_PetContact);
                String petStatus = cursor.getString(NUM_COLUMN_PetStatus);
                Point2 point  = new Point2(des ,photo, x, y, petName, petColor, petContact, petStatus);
                arrPoint2.add(new Point2(des ,photo, x, y, petName, petColor, petContact, petStatus));
                if(point.status.equals(status2))
                {
                    arrPoint.add(point);
                }

            }while (cursor.moveToNext());

            if (status2.equals("Все") || status2.equals(""))
            {
                return arrPoint2;
            }
            else{
                return arrPoint;
            }

        }
        else{
            return null;
        }
    }
    public ArrayList<Point2> findAllPoints(){
        ArrayList<Point2> arrPoint = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = MyDataBase.query(TABLE_NAME,   null, null, null, null, null, null);
        if(cursor!=null && cursor.getCount()>0 && cursor.moveToFirst()){
            cursor.moveToFirst();

            do {
                long id = cursor.getLong(NUM_COLUMN_ID);
                String des = cursor.getString(NUM_COLUMN_Descriptions);
                String photo = cursor.getString(NUM_COLUMN_Image);
                double x = cursor.getDouble(NUM_COLUMN_X );
                double y = cursor.getDouble(NUM_COLUMN_Y);

                String petName = cursor.getString(NUM_COLUMN_PetName);
                String petColor = cursor.getString(NUM_COLUMN_PetColor);
                String petContact = cursor.getString(NUM_COLUMN_PetContact);
                String petStatus = cursor.getString(NUM_COLUMN_PetStatus);

                arrPoint.add(new Point2(des ,photo, x, y, petName, petColor, petContact, petStatus));
            }while (cursor.moveToNext());
            return arrPoint;
        }
        else{
            return null;
        }

    }


    private class OpenHelper2 extends SQLiteOpenHelper {

        public OpenHelper2(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION); }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_Descriptions + " TEXT, " +
                    COLUMN_Image + " TEXT, " +
                    COLUMN_X + " DOUBLE, " +
                    COLUMN_Y + " DOUBLE, " +
                    COLUMN_PetName + " TEXT, " +
                    COLUMN_PetColor + " TEXT, " +
                    COLUMN_PetContact + " TEXT, " +
                    COLUMN_PetStatus + " TEXT" + ");" ;
            db.execSQL(query);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }




    }
}
