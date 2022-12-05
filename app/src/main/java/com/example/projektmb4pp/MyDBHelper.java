package com.example.projektmb4pp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public static final String ORDER_TABLE_NAME = "Order";
    public static final String ORDER_COLUMN_ID = "id_order"; //integer autoincrement primarykey
    //ORDER_COLUMN_CLIENT_ID = "id_client integer foreignkey ref: Client/id_client
    public static final String ORDER_COLUMN_DATE = "date"; //datetime
    public static final String ORDER_COLUMN_HOMEADDRESS = "home_address"; //varchar(50)
    public static final String ORDER_COLUMN_POSTALCODE = "postal_code"; //varchar(15)
    public static final String ORDER_COLUMN_POSTALCITY = "postal_city"; //varchar(30)

    public static final String ORDERPRODUCT_TABLE_NAME = "Order_Product";
    public static final String ORDERPRODUCT_COLUMN_ID = "id_order_product"; //integer autoincrement primarykey
    //ORDERPRODUCT_COLUMN_ORDER_ID = "id_order" integer foreignkey ref: Order/id_order
    //ORDERPRODUCT_COLUMN_PRODUCT_ID = "id_product" integer foreignkey ref: Product/id_product
    public static final String ORDERPRODUCT_COLUMN_COUNT = "count"; //integer

    public static final String PRODUCT_TABLE_NAME = "Product";
    public static final String PRODUCT_COLUMN_ID = "id_product"; //integer autoincrement primarykey
    public static final String PRODUCT_COLUMN_NAME = "name"; //varchar(60)
    public static final String PRODUCT_COLUMN_COST = "cost"; //real(8, 2)
    public static final String PRODUCT_COLUMN_SIZE = "size"; //varchar(20)
    public static final String PRODUCT_COLUMN_type = "type"; //varchar(30)



    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
