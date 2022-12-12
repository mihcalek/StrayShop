package com.example.projektmb4pp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "StrayShop.db";
    public static final int DATABASE_VERSION = 1;

    public static final String ACCOUNT_TABLE_NAME = "Account";
    public static final String ACCOUNT_COLUMN_ID = "id_account"; // integer autoincrement primarykey
    //ACCOUNT_COLUMN_CLIENT_ID = "id_client" integer unique foreignkey  ref: Client/id_client
    public static final String ACCOUNT_COLUMN_EMAIL = "email"; // varchar(50) unique
    public static final String ACCOUNT_COLUMN_PASSWORD = "password"; //varchar(150)

    public static final String CLIENT_TABLE_NAME = "Client";
    public static final String CLIENT_COLUMN_ID = "id_client"; //integer autoincrement primarykey
    public static final String CLIENT_COLUMN_NAME = "name"; //varchar(30)
    public static final String CLIENT_COLUMN_SURNAME = "surname"; //varchar(30)
    public static final String CLIENT_COLUMN_DOB = "date_of_birth"; //datetime
    public static final String CLIENT_COLUMN_TELNUMBER = "telephone_number"; //varchar(15) unique

    public static final String CART_TABLE_NAME = "Cart";
    public static final String CART_COLUMN_ID = "id_cart"; //integer autoincrement primarykey
    //ORDER_COLUMN_CLIENT_ID = "id_client integer foreignkey ref: Client/id_client
    public static final String CART_COLUMN_DATE = "date"; //datetime
    public static final String CART_COLUMN_HOMEADDRESS = "home_address"; //varchar(50)
    public static final String CART_COLUMN_POSTALCODE = "postal_code"; //varchar(15)
    public static final String CART_COLUMN_POSTALCITY = "postal_city"; //varchar(30)

    public static final String ORDERPRODUCT_TABLE_NAME = "Order_Product";
    public static final String ORDERPRODUCT_COLUMN_ID = "id_order_product"; //integer autoincrement primarykey
    //ORDERPRODUCT_COLUMN_ORDER_ID = "id_order" integer foreignkey ref: Order/id_order
    //ORDERPRODUCT_COLUMN_PRODUCT_ID = "id_product" integer foreignkey ref: Product/id_product
    public static final String ORDERPRODUCT_COLUMN_COUNT = "count"; //integer
    public static final String ORDERPRODUCT_COLUMN_SIZE = "size"; //varchar(20)

    public static final String PRODUCT_TABLE_NAME = "Product";
    public static final String PRODUCT_COLUMN_ID = "id_product"; //integer autoincrement primarykey
    public static final String PRODUCT_COLUMN_NAME = "name"; //varchar(60)
    public static final String PRODUCT_COLUMN_COST = "cost"; //real(8, 2)
    public static final String PRODUCT_COLUMN_TYPE = "type"; //varchar(30)
    public static final String PRODUCT_COLUMN_IMAGE = "image"; //blob



    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables();
        insertClientData("Jan", "Kowalski", "1990-01-01", "123456789");
        Log.i("database", readClientData());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
        onCreate(db);
    }

    //create method that will create tables
    public void createTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "CREATE TABLE " + ACCOUNT_TABLE_NAME +
                        " (" +
                        ACCOUNT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ACCOUNT_COLUMN_EMAIL + " VARCHAR(50) UNIQUE, " +
                        ACCOUNT_COLUMN_PASSWORD + " VARCHAR(150))";
        db.execSQL(query);
        query =
                "CREATE TABLE " + CLIENT_TABLE_NAME +
                        " (" +
                        CLIENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CLIENT_COLUMN_NAME + " TEXT, " +
                        CLIENT_COLUMN_SURNAME + " TEXT, " +
                        CLIENT_COLUMN_DOB + " DATETIME, " +
                        CLIENT_COLUMN_TELNUMBER + " TEXT UNIQUE);";
        db.execSQL(query);
        query =
                "CREATE TABLE " + CART_TABLE_NAME +
                        " (" +
                        CART_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CART_COLUMN_DATE + " DATETIME, " +
                        CART_COLUMN_HOMEADDRESS + " TEXT, " +
                        CART_COLUMN_POSTALCODE + " TEXT, " +
                        CART_COLUMN_POSTALCITY + " TEXT);";
        db.execSQL(query);
        query =
                "CREATE TABLE " + ORDERPRODUCT_TABLE_NAME +
                        " (" +
                        ORDERPRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ORDERPRODUCT_COLUMN_COUNT + " INTEGER, " +
                        ORDERPRODUCT_COLUMN_SIZE + " TEXT);";
        db.execSQL(query);
        query =
                "CREATE TABLE " + PRODUCT_TABLE_NAME +
                        " (" +
                        PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PRODUCT_COLUMN_NAME + " TEXT, " +
                        PRODUCT_COLUMN_COST + " REAL, " +
                        PRODUCT_COLUMN_TYPE + " TEXT, " +
                        PRODUCT_COLUMN_IMAGE + " BLOB);";
        db.execSQL(query);
    }

    //create method that will insert data into client table
    public boolean insertClientData(String name, String surname, String dob, String telnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLIENT_COLUMN_NAME, name);
        cv.put(CLIENT_COLUMN_SURNAME, surname);
        cv.put(CLIENT_COLUMN_DOB, dob);
        cv.put(CLIENT_COLUMN_TELNUMBER, telnumber);
        long result = db.insert(CLIENT_TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //create method that will read data from client table
    public String readClientData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CLIENT_TABLE_NAME;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor.toString();
    }

    //create register method
    public boolean register(String name, String surname, String dob, String telnumber) {
        if (insertClientData(name, surname, dob, telnumber)) {
            return true;
        } else {
            return false;
        }
    }

    //create login method
    public boolean login(String telnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CLIENT_TABLE_NAME + " WHERE " + CLIENT_COLUMN_TELNUMBER + " = " + telnumber;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
