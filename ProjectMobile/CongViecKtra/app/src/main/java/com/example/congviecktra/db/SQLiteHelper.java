package com.example.congviecktra.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.congviecktra.models.CongViec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String dbName = "congviecDB";

    public SQLiteHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE congviecs(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "noidung TEXT," +
                "ngayht TEXT," +
                "tinhtrang TEXT," +
                "congtac TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addCongViec(CongViec congviec){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congviec.getTencongviec());
        contentValues.put("noidung", congviec.getNoidungcongviec());
        contentValues.put("ngayht", congviec.getNgayhoanthanh());
        contentValues.put("tinhtrang", congviec.getTinhtrang());
        contentValues.put("congtac", congviec.getCongtac());
        long result = sqLiteDatabase.insert("congviecs", null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<CongViec> getAllCongViec(){
        List<CongViec> congViecList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("congviecs",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getString(5);
            CongViec congviec = new CongViec(id,name,author,publishDate,publisher,price);
            congViecList.add(congviec);
        }
        return congViecList;
    }

    public long updateCongViec(CongViec congviec){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congviec.getTencongviec());
        contentValues.put("noidung", congviec.getNoidungcongviec());
        contentValues.put("ngayht", congviec.getNgayhoanthanh());
        contentValues.put("tinhtrang", congviec.getTinhtrang());
        contentValues.put("congtac", congviec.getCongtac());
        long result = sqLiteDatabase.update("congviecs",
                contentValues,"_id=?",
                new String[]{congviec.getId()+""});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteCongViec(int congViecId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("congviecs", "_id=?",
                new String[]{congViecId+""});
        sqLiteDatabase.close();
        return result;
    }

    public List<CongViec> findCongViecByNgayHoanThanh(String tencongviec) {
        List<CongViec> congViecList = new ArrayList<>();

        // Xác định các cột bạn muốn trả về
        String[] projection = {
                "_id",
                "ten",
                "noidung",
                "ngayht",
                "tinhtrang",
                "congtac"
        };

        // Tạo điều kiện WHERE để tìm kiếm theo tên công việc
        String selection = "ten LIKE ?";
        String[] selectionArgs = { "%" + tencongviec + "%" };

        // Sắp xếp kết quả theo ngày hoàn thành từ sớm đến muộn
        String orderBy = "ngayht ASC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        // Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.query(
                "congviecs", projection, selection, selectionArgs, null, null, orderBy
        );

        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getString(5);
            CongViec congviec = new CongViec(id,name,author,publishDate,publisher,price);
            congViecList.add(congviec);
        }
        Collections.sort(congViecList, new Comparator<CongViec>() {
            @Override
            public int compare(CongViec cv1, CongViec cv2) {
                String[] viec1 = cv1.getNgayhoanthanh().split("/");
                String[] viec2 = cv2.getNgayhoanthanh().split("/");
                if(!viec1[2].equals(viec2[2])){
                    return viec1[2].compareTo(viec2[2]);
                }
                if(!viec1[1].equals(viec2[1])){
                    return viec1[1].compareTo(viec2[1]);
                }
                return viec1[0].compareTo(viec2[0]);
            }
        });
        return congViecList;
    }


    public List<CongViec> getStatistic(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<CongViec> congViecList = new ArrayList<>();
//        String[] projection = {
//                "_id",
//                "ten",
//                "noidung",
//                "ngayht",
//                "tinhtrang",
//                "COUNT(*) AS soLuongCongViec",
//                "congtac"
//        };
        String[] projection = {
                "tinhtrang",
                "COUNT(*) AS soLuongCongViec"
        };
        String orderBy = "soLuongCongViec DESC"; // Sắp xếp kết quả theo số lượng công việc giảm dần

        Cursor cursor = sqLiteDatabase.query(
                "congviecs",   // Tên bảng
                projection,    // Các cột cần trả về
                null,          // Điều kiện WHERE (không có điều kiện)
                null,          // Giá trị của điều kiện WHERE
                "tinhtrang",          // Không cần GROUP BY vì chúng ta chỉ có một hàm tổng hợp
                null,          // HAVING (không có điều kiện)
                orderBy        // Sắp xếp kết quả
        );
        while(cursor!=null && cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String author = cursor.getString(2);
//            String publishDate = cursor.getString(3);
//            int soluong = cursor.getInt(5);
//            String publisher = cursor.getString(4) + " có " + String.valueOf(soluong) +" công việc";
//            String congtac = cursor.getString(6);
//            CongViec congviec = new CongViec(id,name,author,publishDate,publisher,congtac);
            String tinhtang = cursor.getString(0);
            int soluong = cursor.getInt(1);
            CongViec congviec = new CongViec(tinhtang,soluong);
            congViecList.add(congviec);
        }
        return congViecList;
    }
}
