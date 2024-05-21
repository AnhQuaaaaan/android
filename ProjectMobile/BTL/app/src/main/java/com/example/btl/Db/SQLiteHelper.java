package com.example.btl.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.btl.Dto.OrderDto;
import com.example.btl.Model.Clothes;
import com.example.btl.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DBV5";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT," +
                "isadmin INTEGER)";
        sqLiteDatabase.execSQL(sql);
        String createClothesTable = "CREATE TABLE clothes(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "img TEXT,"+
                "name TEXT," +
                "size TEXT," +
                "color TEXT,"+
                "price REAL)";
        sqLiteDatabase.execSQL(createClothesTable);
        String orderstable="CREATE TABLE orders(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT, " +
                "phoneNumber TEXT, " +
                "quantity INTEGER, " +
                "address TEXT, " +
                "totalPrice TEXT," +
                "clothes_id INTEGER," +
                "FOREIGN KEY(clothes_id) REFERENCES clothes(id))";
        sqLiteDatabase.execSQL(orderstable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long addUser(String username, String password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("users", null,contentValues);
        return result;

    }
    public long addOrder(OrderDto orderDto){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", orderDto.getTen());
        contentValues.put("email", orderDto.getEmail());
        contentValues.put("phoneNumber", orderDto.getDienthoai());
        contentValues.put("address", orderDto.getDiachi());
        contentValues.put("quantity", orderDto.getQuantity());
        contentValues.put("totalPrice", orderDto.getTongtien());
        contentValues.put("clothes_id", orderDto.getId());
        long result = sqLiteDatabase.insert("orders", null,contentValues);
        return result;

    }
    public User findUsersByUsernameAndPassword(String username, String password){
        User user = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("users",null,"username = ? AND password = ?"
                , new String[]{username, password},null,null,null);
        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String uname = cursor.getString(1);
            String pword = cursor.getString(2);
            user = new User(id, username, password);
        }
        return user;
    }
    public List<Clothes> getAll(){
        List<Clothes> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        Cursor cursor = st.query("clothes",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String img=cursor.getString(1);
            String name = cursor.getString(2);
            String size = cursor.getString(3);
            String color = cursor.getString(4);
            Float price = cursor.getFloat(5);
            Clothes clothes = new Clothes(id,img,name,size,color,price);
            list.add(clothes);
        }
        return list;
    }
    public long addClothes(Clothes clothes){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img",clothes.getImg());
        contentValues.put("name", clothes.getName());
        contentValues.put("size", clothes.getSize());
        contentValues.put("color", clothes.getColor());
        contentValues.put("price", clothes.getPrice());
        long result = sqLiteDatabase.insert("clothes", null,contentValues);
        return result;
    }
    public long updateClothes(Clothes clothes){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img",clothes.getImg());
        contentValues.put("name", clothes.getName());
        contentValues.put("size", clothes.getSize());
        contentValues.put("color", clothes.getColor());
        contentValues.put("price", clothes.getPrice());
        long result = sqLiteDatabase.update("clothes",
                contentValues,"id=?",
                new String[]{String.valueOf(clothes.getId())});
        return result;
    }
    public long deleteClothes(int clothesId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("clothes", "id=?",
                new String[]{clothesId+""});
        sqLiteDatabase.close();
        return result;
    }
    public List<Clothes> findClothesByPrice(Float startPrice, Float endPrice){
        String sx="price DESC";
        List<Clothes> listClothes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] selectionArgs = {String.valueOf(startPrice), String.valueOf(endPrice)};
        Cursor cursor = sqLiteDatabase.query("clothes", null, "price BETWEEN ? AND ?", selectionArgs, null, null, sx);
        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String img=cursor.getString(1);
            String name = cursor.getString(2);
            String size = cursor.getString(3);
            String color = cursor.getString(4);
            Float price = cursor.getFloat(5);
            Clothes clothes = new Clothes(id,img,name,size,color,price);
            listClothes.add(clothes);
        }
        return listClothes;
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
//        onUpgrade(db, 5, 6);

    }
}
