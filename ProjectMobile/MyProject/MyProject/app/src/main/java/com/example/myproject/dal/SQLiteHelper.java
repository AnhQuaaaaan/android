package com.example.myproject.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myproject.DTO.OrderDTO;
import com.example.myproject.DTO.OrderDetailDTO;
import com.example.myproject.model.Category;
import com.example.myproject.model.Order;
import com.example.myproject.model.OrderDetail;
import com.example.myproject.model.Product;
import com.example.myproject.model.ShoppingCart;
import com.example.myproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "deviceShop.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCategoryTable =
                "CREATE TABLE categories( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "img TEXT)";
        db.execSQL(createCategoryTable);

        String createProductTable =
                "CREATE TABLE products( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "image TEXT, " +
                        "price TEXT, " +
                        "description TEXT, " +
                        "category_id INTEGER, " +
                        "FOREIGN KEY(category_id) REFERENCES categories(id))";
        db.execSQL(createProductTable);

        String createUserTable =
                "CREATE TABLE users( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "email TEXT, " +
                        "password TEXT, " +
                        "phoneNumber TEXT)";
        db.execSQL(createUserTable);

        String createOrderTable =
                "CREATE TABLE orders( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "totalMoney TEXT, " +
                        "totalQuantity INTEGER, " +
                        "email TEXT, " +
                        "phoneNumber TEXT, " +
                        "address TEXT, " +
                        "user_id INTEGER, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id))";
        db.execSQL(createOrderTable);

        String createOrderDetailTable =
                "CREATE TABLE order_details( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "quantity INTEGER, " +
                        "price TEXT, " +
                        "product_id INTEGER, " +
                        "order_id INTEGER, " +
                        "FOREIGN KEY(product_id) REFERENCES products(id), " +
                        "FOREIGN KEY(order_id) REFERENCES orders(id))";
        db.execSQL(createOrderDetailTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // cap nhat db khi tao them bang o day
       // onUpgrade(db, 0, 1);
    }

    // categories
    public List<Category> getAll(){
        List<Category> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("categories", null, null,
                null, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String img = rs.getString(2);
            list.add(new Category(id, name, img));
        }

        return list;
    }

    public Category getCategoryByCategoryId(int categoryId){
        List<Category> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("categories", null, "id=?",
                new String[]{String.valueOf(categoryId)}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String img = rs.getString(2);
            return new Category(id, name, img);
        }

        return null;
    }

    public long addCategory(Category c){
        ContentValues values = new ContentValues();
        values.put("name", c.getName());
        values.put("img", c.getImg());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("categories", null, values);
    }

    public int deleteCategory(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("categories", whereClause, whereArgs);
    }

    // products

    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("products", null, null,
                null, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String image = rs.getString(2);
            String price = rs.getString(3);
            String description = rs.getString(4);
            int categoryId = rs.getInt(5);
            list.add(new Product(id, name,image, price, description, categoryId));
        }

        return list;
    }
    public List<Product> getTop10Product(){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("products", null, null,
                null, null, null, "id DESC", "10");

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String image = rs.getString(2);
            String price = rs.getString(3);
            String description = rs.getString(4);
            int categoryId = rs.getInt(5);
            list.add(new Product(id, name,image, price, description, categoryId));
        }

        return list;
    }

    public List<Product> getProductsByKey(String key){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String sql = "SELECT * FROM products WHERE name like ?";
        String[] selectionArgs = {"%" + key + "%"};
        Cursor rs = st.rawQuery(sql, selectionArgs);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String image = rs.getString(2);
            String price = rs.getString(3);
            String description = rs.getString(4);
            int categoryId = rs.getInt(5);
            list.add(new Product(id, name,image, price, description, categoryId));
        }

        return list;
    }
    public List<Product> getProductByCategoryId(int page, int categoryID){
        int total = 6;
        int pos = (page - 1) * total;

        List<Product> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String sql = "SELECT * FROM products WHERE category_id = ? LIMIT ?, ?";
        String[] selectionArgs = {String.valueOf(categoryID), String.valueOf(pos), String.valueOf(total)};
        Cursor rs = st.rawQuery(sql, selectionArgs);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String image = rs.getString(2);
            String price = rs.getString(3);
            String description = rs.getString(4);
            int categoryId = rs.getInt(5);
            list.add(new Product(id, name,image, price, description, categoryId));
        }

        return list;
    }

    public Product getProductByProductId(int productID){
        SQLiteDatabase st = getReadableDatabase();
        String sql = "SELECT * FROM products WHERE id = ? LIMIT 1";
        String[] selectionArgs = {String.valueOf(productID)};
        Cursor rs = st.rawQuery(sql, selectionArgs);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String image = rs.getString(2);
            String price = rs.getString(3);
            String description = rs.getString(4);
            int categoryId = rs.getInt(5);
            return new Product(id, name,image, price, description, categoryId);
        }

        return null;
    }

    public long addProduct(Product p){
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("image", p.getImage());
        values.put("price", p.getPrice());
        values.put("description", p.getDescription());
        values.put("category_id", p.getCategoryId());
        SQLiteDatabase st = getWritableDatabase();
        long kq = 0;
        try {
            kq = st.insert("products", null, values);
        } catch (Exception e){
            e.printStackTrace();
        }
        return kq;
    }
    public int updateProduct(Product p){
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("image", p.getImage());
        values.put("price", p.getPrice());
        values.put("description", p.getDescription());
        values.put("category_id", p.getCategoryId());
        SQLiteDatabase db = getWritableDatabase();
        int kq = 0;
        try {
            db.update("products", values, "id=?", new String[]{String.valueOf(p.getId())});
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.close();
        }
        return kq;
    }
    public Product getLastestProduct(){
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("products", null, null,
                null, null, null, "id DESC", "1");

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String image = rs.getString(2);
            String price = rs.getString(3);
            String description = rs.getString(4);
            int categoryId = rs.getInt(5);
            return new Product(id, name,image, price, description, categoryId);
        }

        return null;
    }

    public int deleteProduct(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        try {
            return st.delete("products", whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    // users

    public User login(String emailRaw, String passwordRaw) {
//        Product p = getLastestProduct();
//        deleteProduct(p.getId());
        User u = new User();
        SQLiteDatabase st = getReadableDatabase();

        Cursor rs = st.query("users", null, "email=? AND password=?",
                new String[]{emailRaw, passwordRaw}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String email = rs.getString(1);
            String password = rs.getString(2);
            String phoneNumber = rs.getString(3);
            int role = rs.getInt(4);
            return new User(id, email, password, phoneNumber, role);
        }

        return null;
    }

    public long addUser(User u){
        ContentValues values = new ContentValues();
        values.put("email", u.getEmail());
        values.put("password", u.getPassword());
        values.put("phoneNumber", u.getPhoneNumber());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("users", null, values);
    }

    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("users", null, null,
                null, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String email = rs.getString(1);
            String password = rs.getString(2);
            String phoneNumber = rs.getString(3);
            int role = rs.getInt(4);
            list.add(new User(id, email, password, phoneNumber, role));
        }

        return list;
    }

    public User getUserByUserId(int userId){
        SQLiteDatabase st = getReadableDatabase();

        Cursor rs = st.query("users", null, "id=?",
                new String[]{String.valueOf(userId)}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String email = rs.getString(1);
            String password = rs.getString(2);
            String phoneNumber = rs.getString(3);
            int role = rs.getInt(4);
            return new User(id, email, password, phoneNumber, role);
        }

        return null;
    }

    public User getUserByEmail(String emailRaw){
        SQLiteDatabase st = getReadableDatabase();

        Cursor rs = st.query("users", null, "email=?",
                new String[]{emailRaw}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String email = rs.getString(1);
            String password = rs.getString(2);
            String phoneNumber = rs.getString(3);
            int role = rs.getInt(4);
            return new User(id, email, password, phoneNumber, role);
        }

        return null;
    }

    // order
    public void addOrder(Order order, List<ShoppingCart> list){
        // add order
        ContentValues values = new ContentValues();
        values.put("totalMoney", order.getTotalMoney());
        values.put("totalQuantity", order.getTotalQuantity());
        values.put("email", order.getEmail());
        values.put("phoneNumber", order.getPhoneNumber());
        values.put("address", order.getAddress());
        values.put("user_id", order.getUser().getId());

        SQLiteDatabase st = getWritableDatabase();
        try {
            st.insert("orders", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Order lastestOrder = getLastestOrder();

        // add order detail
        for (ShoppingCart cart : list) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getPrice() + "");
            orderDetail.setProduct(getProductByProductId(cart.getId()));
            orderDetail.setOrder(lastestOrder);

            values = new ContentValues();
            values.put("quantity", orderDetail.getQuantity());
            values.put("price", orderDetail.getPrice());
            values.put("product_id", orderDetail.getProduct().getId());
            values.put("order_id", orderDetail.getOrder().getId());

            try {
                st.insert("order_details", null, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Order getLastestOrder(){
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("orders", null, null,
                null, null, null, "id DESC", "1");

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String totalMoney = rs.getString(1);
            int totalQuantity = rs.getInt(2);
            String email = rs.getString(3);
            String phoneNumber = rs.getString(4);
            String address = rs.getString(5);
            int userId = rs.getInt(6);
            User u = getUserByUserId(userId);
            return new Order(id, totalMoney, totalQuantity, email, phoneNumber, address, u);
        }

        return null;
    }

    public Order getOrderByOrderId(int orderId){
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("orders", null, "id=?",
                new String[]{String.valueOf(orderId)}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String totalMoney = rs.getString(1);
            int totalQuantity = rs.getInt(2);
            String email = rs.getString(3);
            String phoneNumber = rs.getString(4);
            String address = rs.getString(5);
            int userId = rs.getInt(6);
            User u = getUserByUserId(userId);
            return new Order(id, totalMoney, totalQuantity, email, phoneNumber, address, u);
        }

        return null;
    }

    public List<Order> getAllOrder(){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("orders", null, null,
                null, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String totalMoney = rs.getString(1);
            int totalQuantity = rs.getInt(2);
            String email = rs.getString(3);
            String phoneNumber = rs.getString(4);
            String address = rs.getString(5);
            int userId = rs.getInt(6);
            User u = getUserByUserId(userId);
            list.add(new Order(id, totalMoney, totalQuantity, email, phoneNumber, address, u));
        }

        return list;
    }

    public List<Order> getOrdersByUserId(int uid){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("orders", null, "user_id=?",
                new String[]{String.valueOf(uid)}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String totalMoney = rs.getString(1);
            int totalQuantity = rs.getInt(2);
            String email = rs.getString(3);
            String phoneNumber = rs.getString(4);
            String address = rs.getString(5);
            int userId = rs.getInt(6);
            User u = getUserByUserId(userId);
            list.add(new Order(id, totalMoney, totalQuantity, email, phoneNumber, address, u));
        }

        return list;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int oid){
        List<OrderDetail> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("order_details", null, "order_id=?",
                new String[]{String.valueOf(oid)}, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            int quantity = rs.getInt(1);
            String price = rs.getString(2);
            int productId = rs.getInt(3);
            int orderId = rs.getInt(4);
            Product p = getProductByProductId(productId);
            Order o = getOrderByOrderId(orderId);
            list.add(new OrderDetail(id, quantity, price, p, o));
        }

        return list;
    }

    public List<OrderDetail> getAllOrderDetail(){
        List<OrderDetail> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("order_details", null, null,
                null, null, null, null);

        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            int quantity = rs.getInt(1);
            String price = rs.getString(2);
            int productId = rs.getInt(3);
            int orderId = rs.getInt(4);
            Product p = getProductByProductId(productId);
            Order o = getOrderByOrderId(orderId);
            list.add(new OrderDetail(id, quantity, price, p, o));
        }

        return list;
    }

    public int deleteOrder(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        try {
            return st.delete("orders", whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteOrderDetail(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        try {
            return st.delete("order_details", whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<OrderDTO> getListOrderDTO(int userId) {
        List<OrderDTO> listOrderDTO = new ArrayList<>();
        List<Order> listOrders = getOrdersByUserId(userId);

        for (Order o : listOrders) {
            OrderDTO orderDTO = new OrderDTO();
            List<OrderDetailDTO> listOrderDetailDTO = new ArrayList<>();

            List<OrderDetail> listOrderDetails = getOrderDetailsByOrderId(o.getId());
            for (OrderDetail od : listOrderDetails) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setImage(od.getProduct().getImage());
                orderDetailDTO.setName(od.getProduct().getName());
                orderDetailDTO.setQuantity(od.getQuantity());

                listOrderDetailDTO.add(orderDetailDTO);
            }

            orderDTO.setOrderId(o.getId());
            orderDTO.setListOrderDetailDTO(listOrderDetailDTO);
            listOrderDTO.add(orderDTO);
        }

        return listOrderDTO;
    }
}
