package com.mentor.pipes.utils;

import android.content.Context;
import android.content.SharedPreferences;



public class LoginManager
{
    public static final String CODE = "code";
    public static final String PROFILE_PHOTO = "profilePhoto";
    public static final String PANCARD = "pancard";
    public static final String AADHAR_CARD = "aadhar_card";
    public static final String Driving_License = "Driving_License";
    public static final String VEHICLE_RC = "vehicle_rc";
    public static final String TOKEN = "token";
    public static final String TSHIRT_SIZE = "t_shirt_size";
    public static final String DELIVERY_MODE = "delivery_mode";
    public static final String FEEES = "fees";
    public static final String PHONE = "phone";

    public static final String CITY_NAME = "city_name";
    public static final String VEHICLE_TYPE = "vehicle_type";
    public static final String ZONE_AREA = "zone_area";
    public static final String SHIFT_NAME = "shift_name";
    public static final String CITY_CODE = "city_code";

    public static final String PAN_NUMBER = "pan_number";
    public static final String PAN_NAME = "pan_name";
    //public static final String PAN_IMG = "img_name";

    public static final String FULL_NAME = "full_name";
    public static final String FATHER_NAME = "father_name";
    public static final String DOB = "DOB";
    public static final String GENDER="gender";
    public static final String ADDRESS="address";
    public static final String ADDRESS1="address1";
    public static final String DISTRICT="district";
    public static final String STATE="state";
    public static final String PINCODE="pincode";
    public static final String CURRENT_ADDRESS1="current_address";
    public static final String CURRENT_ADDRESS2="current_address2";
    public static final String CURRENT_DISTRICT="current_city";
    public static final String CURRENT_STATE="current_state";
    public static final String CURRENT_PINCODE="current_pincode";
    public static final String IS_PERMANENT="isPermanent";
    public static final String IS_ACTIVE="active";


    public static final String DRIVING_LICENSE="driving_license";
    public static final String DL_NUMBER="dl_number";
    public static final String DL_EXPIRY="dl_expiry";

    public static final String BANK_NAME="bank_name";
    public static final String ACCOUNT_NUMBER="acc_number";
    public static final String IFSC_CODE="ifsc_code";
    public static final String BANK_DOCUMENT="bank_doc";

    public static final String VEHICLE_NUMBER="vehicle_number";
    public static final String VEHICLE_MAKE="vehicle_make";
    public static final String VEHICLE_REGISTRATION="vehicle_registartion_certificate";
    public static final String VEHICLE_INSURANCE="vehicle_insurance_number";
    public static final String VEHICLE_VALIDATE="vehicle_validate";

    public static final String AADHAR_NUMBER="aadhar_card_no";
    public static final String AADHAR_NAME="aadhar_card_name";
    public static final String AADHAR_IMG="aadhar_card";

    public static final String INSURANCE_ID="id";
    public static final String USER_ID="user_id";
    public static final String NOMINEE_RELATION="nominee_relationship";
    public static final String NOMINEE_NAME="nominee_name";
    public static final String NOMINEE_MOBILE="nominee_mobile";
    public static final String NOMINEE_DOB="nominee_dob";
    public static final String NOMINEE_ENAME="emergency_contact_name";
    public static final String NOMINEE_ECONTACT="emergency_contact_number";
    public static final String SAME_NOMINEE="same_nominee";

    public static SharedPreferences userPreferences;

    public static void inItUserPreferences(String id,String token, Context context) {

        getInstance(context);
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString(CODE, id);
        editor.putString(TOKEN, token);
        editor.apply();
        editor.commit();
    }

    public static void SaveShiftZone(String city_name,String city_code,String zone,String shift,String bike){
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString(CITY_NAME,city_name);
        editor.putString(CITY_CODE,city_code);
        editor.putString(ZONE_AREA,zone);
        editor.putString(SHIFT_NAME,shift);
        editor.putString(VEHICLE_TYPE,bike);
        editor.apply();
        editor.commit();
    }



    public static SharedPreferences getInstance(Context context) {

        return userPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
    }


}
