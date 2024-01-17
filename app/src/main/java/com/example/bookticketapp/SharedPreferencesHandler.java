package com.example.bookticketapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHandler {
    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_MOVIE_GENRE = "movie_name";
    private static final String KEY_MOVIE_GENRE1 = "movie_value";
    private static final String KEY_MOVIE_LANG = "movie_lang";
    private static final String KEY_VERSION = "version";
    private static final String KEY_SEAT_PRICE = "seat_price";
    private static final String KEY_SEAT_QTY = "seat_qty";
    private static final String KEY_Movie_name = "selected_movie_name";
    private static final String KEY_Theatre_name = "selected_theatre_name";
    private static final String KEY_movie_DATE = "movie_date";
    private static final String KEY_movie_Time = "movie_time";
    private static final String KEY_beverage_qty = "beverage_qty";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHandler(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public void saveCityName(String cityName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CITY_NAME, cityName);
        editor.apply();
    }

    //bool value to make sure that insertion in the home_frag does not happen more than once, therefore after insertion we are changing the Key_version default value to TRUE .
    public void saveVersion(boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_VERSION, value);
        editor.apply();
    }
    public void saveTheatre(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Theatre_name, value);
        editor.apply();
    }
    public void saveMovieDate(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_movie_DATE, value+" Nov");
        editor.apply();
    }
    public void saveMovieTime(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_movie_Time, value);
        editor.apply();
    }
    public void saveMovieName(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Movie_name, value);
        editor.apply();
    }
    public void saveSeatQty(int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SEAT_QTY, value);
        editor.apply();
    }
    public void saveSeatPrice(int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SEAT_PRICE, value);
        editor.apply();
    }
    public void saveBeverage(int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_beverage_qty, value);
        editor.apply();
    }
    public void saveMovieGenre(String value,boolean b,String lang) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(value==""){

        }else{
            editor.putString(KEY_MOVIE_GENRE, value);
            editor.putBoolean(KEY_MOVIE_GENRE1, b);
            editor.putString(KEY_MOVIE_LANG, lang);
            editor.apply();
        }

    }
    public int getSeatPrice() {
        return sharedPreferences.getInt(KEY_SEAT_PRICE, 0); // Default value is an string
    }
    public int getBeverageQty() {
        return sharedPreferences.getInt(KEY_beverage_qty, 0); // Default value is an string
    }
    public String getMovieTime() {
        return sharedPreferences.getString(KEY_movie_Time, ""); // Default value is an string
    }
    public String getMovieDate() {
        return sharedPreferences.getString(KEY_movie_DATE, "21 Nov"); // Default value is an string
    }
    public String getTheatre() {
        return sharedPreferences.getString(KEY_Theatre_name, ""); // Default value is an string
    }
    public String getMovieName() {
        return sharedPreferences.getString(KEY_Movie_name, ""); // Default value is an string
    }
    public int getSeatQty() {
        return sharedPreferences.getInt(KEY_SEAT_QTY, 0); // Default value is an string
    }
    public String getCityName() {
        return sharedPreferences.getString(KEY_CITY_NAME, "Bangalore"); // Default value is an string
    }
    public String getMovieGenre() {
        return sharedPreferences.getString(KEY_MOVIE_GENRE, "All,"); // Default value is an empty string
    }
    public boolean getMovieValue() {
        return sharedPreferences.getBoolean(KEY_MOVIE_GENRE1, false); // Default value is an empty string
    }
    public String getLanguage() {
        return sharedPreferences.getString(KEY_MOVIE_LANG, "All,"); // Default value is an empty string
    }

    //while insertion we call this method using the shared preference object to get the Default value FALSE
    public boolean getVersion() {
        return sharedPreferences.getBoolean(KEY_VERSION, false); // Default value is an empty string
    }

}
