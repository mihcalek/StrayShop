package com.example.projektmb4pp;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.projektmb4pp.adapter.Item;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public final class DatabaseLMAO {

    public DatabaseLMAO(Context context) {}

    private static class Account implements BaseColumns {
        public static final String TABLE_NAME = "Account";
        public static final String COLUMN_ID_CLIENT = "id_client"; //integer
        public static final String COLUMN_EMAIL = "email"; //text unique
        public static final String COLUMN_PASSWORD = "password"; //text
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
                        Account._ID + " INTEGER PRIMARY KEY, " +
                        Account.COLUMN_ID_CLIENT + " INTEGER NOT NULL, " +
                        Account.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        Account.COLUMN_PASSWORD + " TEXT NOT NULL);";

        private static final String sqlCreateClient =
                "CREATE TABLE " + Client.TABLE_NAME +
                        " (" +
                        Client._ID + " INTEGER PRIMARY KEY, " +
                        Client.COLUMN_NAME + " TEXT NOT NULL, " +
                        Client.COLUMN_SURNAME + " TEXT NOT NULL, " +
                        Client.COLUMN_DOB + " DATETIME NOT NULL, " +
                        Client.COLUMN_TELNUMBER + " TEXT UNIQUE NOT NULL);";

        private static final String sqlCreateCart =
                "CREATE TABLE " + Cart.TABLE_NAME +
                        " (" +
                        Cart._ID + " INTEGER PRIMARY KEY, " +
                        Cart.COLUMN_ID_CLIENT + " INTEGER NOT NULL, " +
                        Cart.COLUMN_DATE + " DATETIME NOT NULL, " +
                        Cart.COLUMN_HOMEADDRESS + " TEXT NOT NULL, " +
                        Cart.COLUMN_POSTALCODE + " TEXT NOT NULL, " +
                        Cart.COLUMN_POSTALCITY + " TEXT NOT NULL);";

        private static final String sqlCreateCartProduct =
                "CREATE TABLE " + CartProduct.TABLE_NAME +
                        " (" +
                        CartProduct._ID + " INTEGER PRIMARY KEY, " +
                        CartProduct.COLUMN_ID_ORDER + " INTEGER NOT NULL, " +
                        CartProduct.COLUMN_ID_PRODUCT + " INTEGER NOT NULL, " +
                        CartProduct.COLUMN_COUNT + " INTEGER NOT NULL, " +
                        CartProduct.COLUMN_SIZE + " TEXT NOT NULL);";

        private static final String sqlCreateProduct =
                "CREATE TABLE " + Product.TABLE_NAME +
                        " (" +
                        Product._ID + " INTEGER PRIMARY KEY, " +
                        Product.COLUMN_NAME + " TEXT NOT NULL, " +
                        Product.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                        Product.COLUMN_TYPE + " TEXT NOT NULL, " +
                        Product.COLUMN_COST + " REAL NOT NULL, " +
                        Product.COLUMN_IMAGE + " TEXT NOT NULL);";
    }
    private static class methodsDrop {
        private static final String sqlDropAccount = "DROP TABLE IF EXISTS " + Account.TABLE_NAME + ";";
        private static final String sqlDropClient = "DROP TABLE IF EXISTS " + Client.TABLE_NAME + ";";
        private static final String sqlDropCart = "DROP TABLE IF EXISTS " + Cart.TABLE_NAME + ";";
        private static final String sqlDropCartProduct = "DROP TABLE IF EXISTS " + CartProduct.TABLE_NAME + ";";
        private static final String sqlDropProduct = "DROP TABLE IF EXISTS " + Product.TABLE_NAME + ";";
    }
    private static class methodsInsert{
        private static String sqliteInsertAccount(int idClient, String email, String password){
            return "INSERT INTO " + Account.TABLE_NAME + " (" +
                    Account.COLUMN_ID_CLIENT + ", " + Account.COLUMN_EMAIL + ", " + Account.COLUMN_PASSWORD + ") " +
                    "VALUES ("+idClient+", '"+email+"', '"+password+"');";
        }
        private static String sqliteInsertClient(String name, String surname, String dateOfBirth, String telephoneNumber){
            return "INSERT INTO " + Client.TABLE_NAME + " (" +
                    Client.COLUMN_NAME + ", " + Client.COLUMN_SURNAME + ", " + Client.COLUMN_DOB + ", " + Client.COLUMN_TELNUMBER + ") " +
                    "VALUES ('"+name+"', '"+surname+"', '"+dateOfBirth+"', '"+telephoneNumber+"');";
        }
        private static String sqliteInsertCart(int idClient, String date, String homeAddress, String postalCode, String postalCity){
            return "INSERT INTO " + Cart.TABLE_NAME + " (" +
                    Cart.COLUMN_ID_CLIENT + ", " + Cart.COLUMN_DATE + ", " + Cart.COLUMN_HOMEADDRESS + ", " + Cart.COLUMN_POSTALCODE + ", " + Cart.COLUMN_POSTALCITY + ") " +
                    "VALUES ("+idClient+", '"+date+"', '"+homeAddress+"', '"+postalCode+"', '"+postalCity+"');";
        }
        private static String sqliteInsertCartProduct(int idOrder, int idProduct, int count, String size){
            return "INSERT INTO " + CartProduct.TABLE_NAME + " (" +
                    CartProduct.COLUMN_ID_ORDER + ", " + CartProduct.COLUMN_ID_PRODUCT + ", " + CartProduct.COLUMN_COUNT + ", " + CartProduct.COLUMN_SIZE + ") " +
                    "VALUES ("+idOrder+", "+idProduct+", "+count+", '"+size+"');";
        }
        private static String sqliteInsertProduct(String name, String description, String type, double cost, String image){ //byte[] image
            return "INSERT INTO " + Product.TABLE_NAME + " (" +
                    Product.COLUMN_NAME + ", " + Product.COLUMN_DESCRIPTION + ", " + Product.COLUMN_TYPE + ", " + Product.COLUMN_COST + ", " + Product.COLUMN_IMAGE + ") " +
                    "VALUES ('"+name+"', '"+description+"', '"+type+"', "+cost+", '"+image+"');";
        }
    }

    public static class DBHelper extends SQLiteOpenHelper{

        private Context context;
        public static final String DATABASE_NAME = "StrayShop.db";
        public static final int DATABASE_VERSION = 1;

        public DBHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(methodsCreate.sqlCreateAccount);
            sqLiteDatabase.execSQL(methodsCreate.sqlCreateClient);
            sqLiteDatabase.execSQL(methodsCreate.sqlCreateCart);
            sqLiteDatabase.execSQL(methodsCreate.sqlCreateCartProduct);
            sqLiteDatabase.execSQL(methodsCreate.sqlCreateProduct);

            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertAccount(1, "admin@gmail.com", "admin"));
            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertClient("imie", "nazwisko", "2004-08-16", "123456789"));

            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertAccount(2, "user@gmail.com", "user"));
            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertClient("Jan", "Kowalski", "1998-05-11", "987654321"));

            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertAccount(3, "michal@gmail.com", "michal"));
            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertClient("Michał", "Brzeziński", "2004-08-16", "111222333"));

            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertCart(1, "2022-12-13", "ul. świętego józefa 26", "87-100", "Toruń"));
            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertCartProduct(1, 1, 1, "S"));
            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertCart(2, "2022-12-11", "ul. Wybickiego 13", "32-200", "Gdańsk"));
            sqLiteDatabase.execSQL(methodsInsert.sqliteInsertCartProduct(2, 2, 5, "L"));

            String[] productNames = {"Bluza z kapturem", "Spodnie jeansowe", "Koszulka", "Kurtka", "Golf"};
            String[] productDescriptions = {"Opis fajna bluza", "Opis fajne spodnie", "Opis fajna koszulka", "Opis fajna kurtka", "Opis fajny golf"};
            String[] productTypes = {"bluza", "spodnie", "koszulka", "kurtka", "golf"};
            String[] productCosts = {"139.99", "129.99", "39.99", "249.99", "159.99"};

            TypedArray article_photos = context.getResources().obtainTypedArray(R.array.article_photos);
            for(int i = 0; i < article_photos.length(); i++){
                String[] imgPath = String.valueOf(article_photos.getText(i)).split("/");
                String img = imgPath[imgPath.length - 1].replace(".png", "").trim();
                sqLiteDatabase.execSQL(methodsInsert.sqliteInsertProduct(productNames[i], productDescriptions[i], img, Double.parseDouble(productCosts[i]), productTypes[i]));
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //usunac tabele z bazy wszustkie
            sqLiteDatabase.execSQL(methodsDrop.sqlDropAccount);
            sqLiteDatabase.execSQL(methodsDrop.sqlDropClient);
            sqLiteDatabase.execSQL(methodsDrop.sqlDropCart);
            sqLiteDatabase.execSQL(methodsDrop.sqlDropCartProduct);
            sqLiteDatabase.execSQL(methodsDrop.sqlDropProduct);
            onCreate(sqLiteDatabase);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public Item[] getItemList(SQLiteDatabase db){
            Cursor cursor = db.rawQuery("SELECT * FROM " + Product.TABLE_NAME, null);
            Item[] items = new Item[cursor.getCount()];
            int i = 0;
            while (cursor.moveToNext()){
                items[i] = new Item(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getFloat(4),
                        cursor.getString(5)
                );
                i++;
            }
            cursor.close();
            return items;
        }
    }
}