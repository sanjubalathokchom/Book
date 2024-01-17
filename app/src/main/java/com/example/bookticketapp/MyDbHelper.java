package com.example.bookticketapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String Db_Name="User_Db";
    public static final int Db_version=1;

    //database details for user details
    public static final String Table_name="User_info";
    public static final String usr_id="id";
    public static final String usr_name="name";
    public static final String usr_mobile="mobile_no";
    public static final String usr_email="email";
    public static final String usr_pass="password";
    public static final String usr_conf_pass="confirm_pass";


    //database details for location based movies page
    public static final String Table_name1="movie_list";
    public static final String movie_id="id";

    public static final String movie_name="movie_name";
    public static final String movie_location="location";
    public static final String movie_image="image_id";
    public static final String movie_desc1="descr1";
    public static final String movie_desc2="descr2";
    public static final String movie_genre="genre";




    // SQL query to create the table with NOT NULL constraints
    public  final String CREATE_TABLE = "CREATE TABLE " + Table_name + " ("
            + usr_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + usr_name + " TEXT NOT NULL, "
            + usr_mobile + " TEXT NOT NULL, "
            + usr_email + " TEXT NOT NULL, "
            + usr_pass + " TEXT NOT NULL, "
            + usr_conf_pass + " TEXT NOT NULL);";

//    public  final String CREATE_TABLE1 = "CREATE TABLE " + Table_name1 + " ("
//            + movie_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + movie_name + " TEXT NOT NULL, "
//            + movie_location + " TEXT NOT NULL, "
//            + movie_image + " INTEGER NOT NULL, "
//            + movie_desc1 + " TEXT NOT NULL, "
//            + movie_desc2 + " TEXT NOT NULL);";

    public final String CREATE_TABLE1 = "CREATE TABLE " + Table_name1 + " ("
            + movie_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + movie_name + " TEXT NOT NULL, "
            + movie_location + " TEXT NOT NULL, "
            + movie_image + " INTEGER NOT NULL, "
            + movie_desc1 + " TEXT NOT NULL, "
            + movie_desc2 + " TEXT NOT NULL, "
            + movie_genre + " TEXT NOT NULL);";

    public MyDbHelper(Context context) {
        super(context, Db_Name, null, Db_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Table_name);
            db.execSQL("DROP TABLE IF EXISTS " + Table_name1);
        }

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
        onCreate(db);
    }

    public boolean insert(String name,String phone,String email,String pass,String conf_pass){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(usr_name,name);
        values.put(usr_mobile,phone);
        values.put(usr_email,email);
        values.put(usr_pass,pass);
        values.put(usr_conf_pass,conf_pass);
        db.insert(Table_name,null,values);
        return true;
    }
    public ArrayList<Store_usr_details> select(){
        SQLiteDatabase db= this.getReadableDatabase();
        ContentValues values=new ContentValues();
        String query="SELECT * FROM "+Table_name+";";
        Cursor cursor= db.rawQuery(query,null);
        ArrayList<Store_usr_details> al= new ArrayList<>();
        while(cursor.moveToNext()){
            Store_usr_details sd =new Store_usr_details();
            sd.id=cursor.getInt(0);
            sd.name=cursor.getString(1);
            sd.phone=cursor.getString(2);
            sd.email_address=cursor.getString(3);
            sd.password=cursor.getString(4);
            sd.confirm_password=cursor.getString(5);
            al.add(sd);
        }
        return al;
    }

    public void insert_movies(int image,String name,String location,String desc1,String desc2,String genre){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(movie_name,name);
        values.put(movie_location,location);
        values.put(movie_image,image);
        values.put(movie_desc1,desc1);
        values.put(movie_desc2,desc2);
        values.put(movie_genre,genre);
        db1.insert(Table_name1,null,values);
    }

    public ArrayList<Store_movie_details> selectMoviesByCity(String city) {
        SQLiteDatabase db = this.getReadableDatabase();
        String city1="";
        city1=city;
        String query = "SELECT * FROM " + Table_name1 + " WHERE " + movie_location + " = '" + city1 + "'";

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Store_movie_details> al = new ArrayList<>();
        while (cursor.moveToNext()) {
            Store_movie_details sd = new Store_movie_details();
            sd.id = cursor.getInt(0);
            sd.movie_name = cursor.getString(1);
            sd.location = cursor.getString(2);
            sd.image = cursor.getInt(3);
            sd.desc1 = cursor.getString(4);
            sd.desc2 = cursor.getString(5);
            al.add(sd);
        }
        return al;
    }
    public ArrayList<Store_movie_details> selectMoviesBy_City_Genre(String city,String genre_1,String genre_2) {
        SQLiteDatabase db = this.getReadableDatabase();
        String city1="",genre1="",genre2="";
        city1=city;
        genre1=genre_1;
        genre2=genre_2;

        String query = "SELECT * FROM " + Table_name1 + " WHERE " + movie_location + " = '" + city1  +"'" + " AND (" + movie_genre + " LIKE '%" + genre1 + "%'" + " OR " + movie_genre + " LIKE '%" + genre2 + "%')";

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Store_movie_details> al = new ArrayList<>();
        while (cursor.moveToNext()) {
            Store_movie_details sd = new Store_movie_details();
            sd.id = cursor.getInt(0);
            sd.movie_name = cursor.getString(1);
            sd.location = cursor.getString(2);
            sd.image = cursor.getInt(3);
            sd.desc1 = cursor.getString(4);
            sd.desc2 = cursor.getString(5);
            sd.genre= cursor.getString(6);
            al.add(sd);
        }
        return al;
    }

    public ArrayList<Store_movie_details> selectMoviesBy_City_Genre_Lang(String city,String genre_1,String genre_2,String lang1,String lang2) {
        SQLiteDatabase db = this.getReadableDatabase();
        String city1="",genre1="",genre2="",language1="",language2="";
        city1=city;
        genre1=genre_1;
        genre2=genre_2;
        language1=lang1;
        language2=lang2;
        String query = "SELECT * FROM " + Table_name1 + " WHERE " + movie_location + " = '" + city1  +"'" + " AND (" + movie_genre + " LIKE '%" + genre1 + "%'" + " OR " + movie_genre + " LIKE '%" + genre2 + "%' AND (" + movie_desc2 + " LIKE '%" + language1 +"%' OR " + movie_desc2 + " LIKE '%" + language2 +"%'))";

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Store_movie_details> al = new ArrayList<>();
        while (cursor.moveToNext()) {
            Store_movie_details sd = new Store_movie_details();
            sd.id = cursor.getInt(0);
            sd.movie_name = cursor.getString(1);
            sd.location = cursor.getString(2);
            sd.image = cursor.getInt(3);
            sd.desc1 = cursor.getString(4);
            sd.desc2 = cursor.getString(5);
            sd.genre= cursor.getString(6);
            al.add(sd);
        }
        return al;
    }
public ArrayList<Store_movie_details> selectMoviesBy_City_Lang(String city,String lang1,String lang2) {
        SQLiteDatabase db = this.getReadableDatabase();
        String language1="",language2="",city1="";
        city1=city;
        language1=lang1;
        language2=lang2;
       // Toast.makeText(context,"values: "+lang1+" "+lang2, Toast.LENGTH_SHORT).show();
        String query = "SELECT * FROM " + Table_name1 + " WHERE " + movie_location + " = '" + city1 + "' AND (" + movie_desc1 + " LIKE '%" + language1 +"%' OR " + movie_desc1 + " LIKE '%" + language2 +"%')";

        Cursor cursor = db.rawQuery(query, null);
       // Cursor cursor = db.rawQuery("select * from movie_list where location= , null);
        ArrayList<Store_movie_details> al = new ArrayList<>();
        while (cursor.moveToNext()) {
            Store_movie_details sd = new Store_movie_details();
            sd.id = cursor.getInt(0);
            sd.movie_name = cursor.getString(1);
            sd.location = cursor.getString(2);
            sd.image = cursor.getInt(3);
            sd.desc1 = cursor.getString(4);
            sd.desc2 = cursor.getString(5);
            sd.genre= cursor.getString(6);
            al.add(sd);
        }
        return al;
    }
    public boolean update_Usr(String email, String number, String pass, String conf_pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Check if the email and mobile number exist in the database
        boolean userExists = false;
        ArrayList<Store_usr_details> al = select();
        if(pass.equals(conf_pass)) {
            for (Store_usr_details user : al) {
                if (user.email_address.equals(email) && user.phone.equals(number)) {
                    userExists = true;
                    break;
                }
            }
        }else
            Toast.makeText(context, "Pass and conf pass do not match", Toast.LENGTH_SHORT).show();

        if (userExists) {
            // The user exists, so update the password and confirm password fields
            values.put(usr_pass, pass);
            values.put(usr_conf_pass, conf_pass);

            // Use selection arguments and placeholders in the SQL query
            String selection = usr_email + " = ? AND " + usr_mobile + " = ?";
            String[] selectionArgs = {email, number};

            db.update(Table_name, values, selection, selectionArgs);
            return true;
        } else {
            // The user does not exist
            Toast.makeText(context, "Invalid email or mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean delete_usr(String res_email){
        SQLiteDatabase db= getWritableDatabase();
        String where= usr_email + " = ?";
        String s[]={res_email};
        db.delete(Table_name,where,s);
        return true;
    }




}


/*---------------Rough------------*/
//    public ArrayList<Store_movie_details> select_movie(){
//        SQLiteDatabase db= this.getReadableDatabase();
//        ContentValues values=new ContentValues();
//        String query="SELECT * FROM "+Table_name1+";";
//        Cursor cursor= db.rawQuery(query,null);
//        ArrayList<Store_movie_details> al= new ArrayList<>();
//        while(cursor.moveToNext()){
//            Store_movie_details sd =new Store_movie_details();
//            sd.id=cursor.getInt(0);
//            sd.movie_name=cursor.getString(1);
//            sd.location=cursor.getString(2);
//            sd.image=cursor.getInt(3);
//            sd.desc1=cursor.getString(4);
//            sd.desc2=cursor.getString(5);
//            al.add(sd);
//        }
//        return al;
//    }
