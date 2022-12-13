package com.example.projektmb4pp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DatabaseLMAO {

    private Context context;
    public static final String DATABASE_NAME = "StrayShop.db";
    public static final int DATABASE_VERSION = 1;

    private static class Account implements BaseColumns {
        public static final String TABLE_NAME = "Account";
        public static final String COLUMN_EMAIL = "email"; //text unique
        public static final String COLUMN_PASSWORD = "password"; //text
        //klient id
    }
    private static class Client implements BaseColumns{
        public static final String TABLE_NAME = "Client";
        public static final String COLUMN_NAME = "name"; //text
        public static final String COLUMN_SURNAME = "surname"; //text
        public static final String COLUMN_DOB = "date_of_birth"; //datetime
        public static final String COLUMN_TELNUMBER = "telephone_number"; //text unique
    }
    private static class Cart implements BaseColumns{
        public static final String TABLE_NAME = "Cart";
        public static final String COLUMN_DATE = "date"; //datetime
        public static final String COLUMN_HOMEADDRESS = "home_address"; //text
        public static final String COLUMN_POSTALCODE = "postal_code"; //text
        public static final String COLUMN_POSTALCITY = "postal_city"; //text
        //ORDER_COLUMN_CLIENT_ID = "id_client integer foreignkey ref: Client/id_client
    }
    private static class CartProduct implements BaseColumns{
        public static final String TABLE_NAME = "Order_Product";
        //ORDERPRODUCT_COLUMN_ORDER_ID = "id_order" integer foreignkey ref: Order/id_order
        //ORDERPRODUCT_COLUMN_PRODUCT_ID = "id_product" integer foreignkey ref: Product/id_product
        public static final String COLUMN_COUNT = "count"; //integer
        public static final String COLUMN_SIZE = "size"; //text
    }
    private static class Product implements BaseColumns{
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_NAME = "name"; //text
        public static final String COLUMN_COST = "cost"; //real(8, 2)
        public static final String COLUMN_TYPE = "type"; //text
        public static final String COLUMN_IMAGE = "image"; //blob
    }


    //create query that creates account table
    private static final String sqlCreateAccount =
            "CREATE TABLE " + Account.TABLE_NAME +
                    " (" +
                    Account._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Account.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                    Account.COLUMN_PASSWORD + " TEXT NOT NULL);";

    //create query that creates client table
    private static final String sqlCreateClient =
            "CREATE TABLE " + Client.TABLE_NAME +
                    " (" +
                    Client._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Client.COLUMN_NAME + " TEXT NOT NULL, " +
                    Client.COLUMN_SURNAME + " TEXT NOT NULL, " +
                    Client.COLUMN_DOB + " DATETIME NOT NULL, " +
                    Client.COLUMN_TELNUMBER + " TEXT UNIQUE NOT NULL);";

    //create query that creates cart table
    private static final String sqlCreateCart =
            "CREATE TABLE " + Cart.TABLE_NAME +
                    " (" +
                    Cart._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Cart.COLUMN_DATE + " DATETIME NOT NULL, " +
                    Cart.COLUMN_HOMEADDRESS + " TEXT NOT NULL, " +
                    Cart.COLUMN_POSTALCODE + " TEXT NOT NULL, " +
                    Cart.COLUMN_POSTALCITY + " TEXT NOT NULL);";

    //create query that creates cartproduct table
    private static final String sqlCreateCartProduct =
            "CREATE TABLE " + CartProduct.TABLE_NAME +
                    " (" +
                    CartProduct._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CartProduct.COLUMN_COUNT + " INTEGER NOT NULL, " +
                    CartProduct.COLUMN_SIZE + " TEXT NOT NULL);";

    //create query that creates product table
    private static final String sqlCreateProduct =
            "CREATE TABLE " + Product.TABLE_NAME +
                    " (" +
                    Product._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Product.COLUMN_NAME + " TEXT NOT NULL, " +
                    Product.COLUMN_COST + " REAL(8, 2) NOT NULL, " +
                    Product.COLUMN_TYPE + " TEXT NOT NULL, " +
                    Product.COLUMN_IMAGE + " BLOB NOT NULL);";

    //drop query that drops account table
    private static final String sqlDropAccount =
            "DROP TABLE IF EXISTS " + Account.TABLE_NAME + ";";

    //drop query that drops client table
    private static final String sqlDropClient =
            "DROP TABLE IF EXISTS " + Client.TABLE_NAME + ";";

    //drop query that drops cart table
    private static final String sqlDropCart =
            "DROP TABLE IF EXISTS " + Cart.TABLE_NAME + ";";

    //drop query that drops cartproduct table
    private static final String sqlDropCartProduct =
            "DROP TABLE IF EXISTS " + CartProduct.TABLE_NAME + ";";

    //drop query that drops product table
    private static final String sqlDropProduct =
            "DROP TABLE IF EXISTS " + Product.TABLE_NAME + ";";

    //create query that creates all tables
    private static final String sqlCreateAll =
            sqlCreateAccount + sqlCreateClient + sqlCreateCart + sqlCreateCartProduct + sqlCreateProduct;

    //drop query that drops all tables
    private static final String sqlDropAll =
            sqlDropAccount + sqlDropClient + sqlDropCart + sqlDropCartProduct + sqlDropProduct;

    public static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //tworzyc tabele do bazy
            sqLiteDatabase.execSQL(sqlCreateAccount);
            sqLiteDatabase.execSQL(sqlCreateClient);
            sqLiteDatabase.execSQL(sqlCreateCart);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //usunac tabele z bazy wszustkie
            sqLiteDatabase.execSQL(sqlDropAll);
            onCreate(sqLiteDatabase);
        }


    }
}