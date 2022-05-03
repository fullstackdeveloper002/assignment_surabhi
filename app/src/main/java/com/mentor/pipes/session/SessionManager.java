package com.mentor.pipes.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
    private Editor editor;
    private SharedPreferences pref;

    // prefrence name
    private static final String PREF_NAME = "UniCharm";
    //
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_LOGIN = "IsLoggedIn";
    //
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_DISTRIBUTOR_ID = "dis_id";
    public static final String IP_ADDRESS = "ip_address";
    public static final String BRAND_ID = "brand_id";
    public static final String DISTRIBUTOR_NAME = "distributor_name";
    public static final String LOGIN_TYPE = "type";
    public static final String IMAGE = "image";
    public static final String BRAND_NAME = "brand_name";

    public static final String KEY_TOKEN = "token";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LONG = "long";
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String FULL_NAME = "full_name";
    public static final String FATHER_NAME = "father_name";
    public static final String ADDRESS = "address";
    public static final String VEHICLE_NO = "vehicle_no";
    public static final String PANCARD = "pan";
    public static final String VEHICLE_REGISTRAION_no = "vehivle_registration_no";
    public static final String DL_NUMBER = "dl_number";
    public static final String LIVE_LOCATION_ADDRESS = "location_address";
    public static final String ADHAR_NO = "adhar_no";
    public static final String HELP_TYPE = "help_type";

    public static final String ADHAR_PIC = "adhar_no_pic";


    public static final String LANGUAGE = "language";
    public static final String PURPOSE = "use";

    public static final String DEPARTMENT_Name = "Department_Name";
    public static final String DESIGNATION_NAME = "Designationname";
    public static final String ROLE_ID = "RoleId";
    public static final String BIRTTH_DATE = "Birthdate";

    public static final String MOBILE_2 = "MobileNo2";
    public static final String EMAIL = "Email";
    public static final String USERPHOTO = "UserPhoto";
    public static final String PASSWORD = "password";
    public static final String BANNER_IMAGE_HOME = "banner_image";
    public static final String BANNER_SHOW_TIME_HOME = "banner_show_time";
    public static final String BANNER_TYPE = "banner_type";
    public static final String BANNER_OPEN_wall = "false";
    public static final String BANNER_OPEN_Home = "false";
    public static final String AGREE_MSG = "false";
    public static final String Is_TODAY_BIRTHDAY = "today_birthday";
    public static final String Is_TODAY_ANNIVERSARY = "today_anniversary";

    //EDIT_DEALER,CREATE_DEALER
    public static final String CALL_FOR = "call_for";
    public static final String BANNER_SHOW_TIME_WALL = "banner_show_time_wall";
    public static final HashMap<String, String> TIMEHASH = null;
    private static final String NOTIFICATION_COUNT = "notification_count";
    public static final String SPLASH_SCREEN = "splash_screen";
    public static final String UPDATE_APP = "update_app_key";
    public static final String Is_dialog_show_birthda = "show_dialog_birthday";
    public static final String Is_dialog_show_anniversary = "show_dialog_anniversary";


    // Constructor
    public SessionManager(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLoginSession(String userID, String full_name, String mobile, String address, String adhar_card_number, String help_type,
                                String live_picture, String adhar_card_photo
    ) {
        editor.putString(KEY_USER_ID, userID);
        editor.putString(FULL_NAME, full_name);
        editor.putString(LIVE_LOCATION_ADDRESS, address);
        editor.putString(ADHAR_NO, adhar_card_number);
        editor.putString(HELP_TYPE, help_type);
        editor.putString(USERPHOTO, live_picture);
        editor.putString(ADHAR_PIC, adhar_card_photo);


        // commit changes
        editor.apply();
    }

    public void setPersonalDetails(String userID, String full_name, String father_name, String address, String vehicle_number,String vehicle_type,
                                   String pancard_number,
                                   String vehicle_registration_number, String dl_number, String aadhar_card_no,
                                   String profile
    ) {
        editor.putString(KEY_USER_ID, userID);
        editor.putString(FULL_NAME, full_name);
        editor.putString(FATHER_NAME, father_name);
        editor.putString(ADDRESS, address);
        editor.putString(VEHICLE_NO, vehicle_number);
        editor.putString(PANCARD, pancard_number);
        editor.putString(VEHICLE_REGISTRAION_no, vehicle_registration_number);
        editor.putString(DL_NUMBER, dl_number);
        editor.putString(ADHAR_NO, aadhar_card_no);
        editor.putString(USERPHOTO, profile);


        // commit changes
        editor.apply();
    }


    public void setStringvalue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }


    public void setFirstTimeLaunch(boolean b) {
        this.editor.putBoolean(IS_FIRST_TIME_LAUNCH, b);
        this.editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return this.pref.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

    public void setLoggedIn(boolean b) {
        this.editor.putBoolean(IS_LOGIN, b);
        this.editor.apply();
    }


    public void setagree(boolean b) {
        this.editor.putBoolean(AGREE_MSG, b);
        this.editor.apply();
    }

    public void setBannershow(boolean b) {
        this.editor.putBoolean(BANNER_OPEN_Home, b);
        this.editor.apply();
    }

    public boolean isBAnnerShow() {
        return this.pref.getBoolean(BANNER_OPEN_Home, false);
    }

    public void setBannershow_wall(boolean b) {
        this.editor.putBoolean(BANNER_OPEN_wall, b);
        this.editor.apply();
    }

    public boolean isBAnnerShow_wall() {
        return this.pref.getBoolean(BANNER_OPEN_wall, false);
    }

    public boolean isLoggedIn() {
        return this.pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isAgree() {
        return this.pref.getBoolean(AGREE_MSG, false);
    }

    public void logoutUser() {
        this.editor.clear();
        this.editor.apply();
    }

    public int getNotificationCount() {
        return this.pref.getInt(NOTIFICATION_COUNT, 0);
    }

    public void setNotificationCount(int count) {
        this.editor.putInt(NOTIFICATION_COUNT, count);
        this.editor.apply();
    }

    public void setUpdateAvailable(boolean b) {
        this.editor.putBoolean(UPDATE_APP, b);
        this.editor.apply();
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return pref.getString(key, "");
    }

    public void putBoolean(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }
}