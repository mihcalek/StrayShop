package com.example.projektmb4pp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public final class DatabaseLMAO {

    private DatabaseLMAO() {};

    public static class DBHelper extends SQLiteOpenHelper{

        private Context context;
        public static final String DATABASE_NAME = "StrayShop.db";
        public static final int DATABASE_VERSION = 1;

        public DBHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(methodsCreate.sqlCreateAll);
            sqLiteDatabase.execSQL(methodsInsert.sqlInsertAll);


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //usunac tabele z bazy wszustkie
            sqLiteDatabase.execSQL(methodsDrop.sqlDropAll);
            onCreate(sqLiteDatabase);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    private static class Account implements BaseColumns {
        public static final String TABLE_NAME = "Account";
        public static final String COLUMN_ID_CLIENT = "id_client"; //integer
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
        public static final String COLUMN_ID_CLIENT = "id_client"; //integer
        public static final String COLUMN_DATE = "date"; //datetime
        public static final String COLUMN_HOMEADDRESS = "home_address"; //text
        public static final String COLUMN_POSTALCODE = "postal_code"; //text
        public static final String COLUMN_POSTALCITY = "postal_city"; //text
    }
    private static class CartProduct implements BaseColumns{
        public static final String TABLE_NAME = "Order_Product";
        public static final String COLUMN_ID_ORDER = "id_order";
        public static final String COLUMN_ID_PRODUCT = "id_product";
        public static final String COLUMN_COUNT = "count"; //integer
        public static final String COLUMN_SIZE = "size"; //text
    }
    private static class Product implements BaseColumns{
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_NAME = "name"; //text
        public static final String COLUMN_DESCRIPTION = "description"; //text
        public static final String COLUMN_COST = "cost"; //real(8, 2)
        public static final String COLUMN_TYPE = "type"; //text
        public static final String COLUMN_IMAGE = "image"; //blob
    }

    private static class methodsCreate {
        private static final String sqlCreateAccount =
                "CREATE TABLE " + Account.TABLE_NAME +
                        " (" +
                        Account._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Account.COLUMN_ID_CLIENT + " INTEGER NOT NULL, " +
                        Account.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        Account.COLUMN_PASSWORD + " TEXT NOT NULL);";

        private static final String sqlCreateClient =
                "CREATE TABLE " + Client.TABLE_NAME +
                        " (" +
                        Client._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Client.COLUMN_NAME + " TEXT NOT NULL, " +
                        Client.COLUMN_SURNAME + " TEXT NOT NULL, " +
                        Client.COLUMN_DOB + " DATETIME NOT NULL, " +
                        Client.COLUMN_TELNUMBER + " TEXT UNIQUE NOT NULL);";

        private static final String sqlCreateCart =
                "CREATE TABLE " + Cart.TABLE_NAME +
                        " (" +
                        Cart._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Cart.COLUMN_ID_CLIENT + " INTEGER NOT NULL, " +
                        Cart.COLUMN_DATE + " DATETIME NOT NULL, " +
                        Cart.COLUMN_HOMEADDRESS + " TEXT NOT NULL, " +
                        Cart.COLUMN_POSTALCODE + " TEXT NOT NULL, " +
                        Cart.COLUMN_POSTALCITY + " TEXT NOT NULL);";

        private static final String sqlCreateCartProduct =
                "CREATE TABLE " + CartProduct.TABLE_NAME +
                        " (" +
                        CartProduct._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CartProduct.COLUMN_ID_ORDER + " INTEGER NOT NULL, " +
                        CartProduct.COLUMN_ID_PRODUCT + " INTEGER NOT NULL, " +
                        CartProduct.COLUMN_COUNT + " INTEGER NOT NULL, " +
                        CartProduct.COLUMN_SIZE + " TEXT NOT NULL);";

        private static final String sqlCreateProduct =
                "CREATE TABLE " + Product.TABLE_NAME +
                        " (" +
                        Product._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Product.COLUMN_NAME + " TEXT NOT NULL, " +
                        Product.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                        Product.COLUMN_COST + " REAL(8, 2) NOT NULL, " +
                        Product.COLUMN_TYPE + " TEXT NOT NULL, " +
                        Product.COLUMN_IMAGE + " BLOB NOT NULL);";

        private static final String sqlCreateAll =
                sqlCreateAccount + sqlCreateClient + sqlCreateCart + sqlCreateCartProduct + sqlCreateProduct;
    }
    private static class methodsDrop {
        private static final String sqlDropAccount = "DROP TABLE IF EXISTS " + Account.TABLE_NAME + ";";
        private static final String sqlDropClient = "DROP TABLE IF EXISTS " + Client.TABLE_NAME + ";";
        private static final String sqlDropCart = "DROP TABLE IF EXISTS " + Cart.TABLE_NAME + ";";
        private static final String sqlDropCartProduct = "DROP TABLE IF EXISTS " + CartProduct.TABLE_NAME + ";";
        private static final String sqlDropProduct = "DROP TABLE IF EXISTS " + Product.TABLE_NAME + ";";
        private static final String sqlDropAll = sqlDropAccount + sqlDropClient + sqlDropCart + sqlDropCartProduct + sqlDropProduct;
    }
    private static class methodsInsert{
        private static final String sqlInsertAccount =
                "INSERT INTO " + Account.TABLE_NAME + " (" +
                        Account.COLUMN_ID_CLIENT + ", " + Account.COLUMN_EMAIL + ", " + Account.COLUMN_PASSWORD + ") " +
                        "VALUES (1, 'admin@gmail.com', 'admin');";

        private static final String sqlInsertClient =
                "INSERT INTO " + Client.TABLE_NAME + " (" +
                        Client.COLUMN_NAME + ", " + Client.COLUMN_SURNAME + ", " + Client.COLUMN_DOB + ", " + Client.COLUMN_TELNUMBER + ") " +
                        "VALUES ('Jan', 'Kowalski', '2004-01-01', '123456789');";

        private static final String sqlInsertCart =
                "INSERT INTO " + Cart.TABLE_NAME + " (" +
                        Cart.COLUMN_ID_CLIENT + ", " + Cart.COLUMN_DATE + ", " + Cart.COLUMN_HOMEADDRESS + ", " + Cart.COLUMN_POSTALCODE + ", " + Cart.COLUMN_POSTALCITY + ") " +
                        "VALUES (1, '2022-01-01', 'ul. św. Józefa 26', '87-100', 'Toruń');";

        private static final String sqlInsertCartProduct =
                "INSERT INTO " + CartProduct.TABLE_NAME + " (" +
                        CartProduct.COLUMN_ID_ORDER + ", " + CartProduct.COLUMN_ID_PRODUCT + ", " + CartProduct.COLUMN_COUNT + ", " + CartProduct.COLUMN_SIZE + ") " +
                        "VALUES (1, 1, 1, 'M');";

        private static final String sqlInsertProduct
                = "INSERT INTO " + Product.TABLE_NAME + " (" +
                Product.COLUMN_NAME + ", " + Product.COLUMN_DESCRIPTION + ", " + Product.COLUMN_COST + ", " + Product.COLUMN_TYPE + ", " + Product.COLUMN_IMAGE + ") " +
                "VALUES ('Bluza z kapturem', 'Opis bluzy bardzo fajna', 99.99, 'bluza', 'obrazek');";

        private static final String sqlInsertAll = sqlInsertAccount + sqlInsertClient + sqlInsertCart + sqlInsertCartProduct + sqlInsertProduct;
    }
    private static class methodsSelect{
        private static final String sqlSelectAccount = "SELECT * FROM " + Account.TABLE_NAME + ";";
        private static final String sqlSelectClient = "SELECT * FROM " + Client.TABLE_NAME + ";";
        private static final String sqlSelectCart = "SELECT * FROM " + Cart.TABLE_NAME + ";";
        private static final String sqlSelectCartProduct = "SELECT * FROM " + CartProduct.TABLE_NAME + ";";
        private static final String sqlSelectProduct = "SELECT * FROM " + Product.TABLE_NAME + ";";
        private static final String sqlSelectAll = sqlSelectAccount + sqlSelectClient + sqlSelectCart + sqlSelectCartProduct + sqlSelectProduct;
    }
}