package com.mentor.app.utils;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.exifinterface.media.ExifInterface;

import com.mentor.app.R;
import com.mentor.app.activity.login.SignInActivity2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonClass {

    public static Dialog dialog, dialog_banner;
    public static int count = 0;
    public static String pan_pattern = "(([A-Za-z]{5})([0-9]{4})([a-zA-Z]))";
    public static Pattern r = Pattern.compile(pan_pattern);

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static Bitmap rotatePhoto(Context context, Uri imageUri_camera, String ImageName) {
        Bitmap rotatedBitmap = null;
        try {
            InputStream image_stream = context.getContentResolver().openInputStream(imageUri_camera);
            Bitmap bmp = BitmapFactory.decodeStream(image_stream);
            // Bitmap bmp = decodeSmallUri(Uri.fromFile(getFileLocation(PancardActivity.this, ImageName)));
            //   img_profilePhoto.setImageBitmap(bmp);

            ExifInterface ei = null;
            try {
                ei = new ExifInterface(ImageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bmp, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bmp, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bmp, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bmp;
            }

            getfile(ImageName, rotatedBitmap, context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return rotatedBitmap;
    }

    public static String capitalizeString(String str) {
        String retStr = str;
        try { // We can face index out of bound exception if the string is null
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        }catch (Exception e){}
        return retStr;
    }

    public static boolean isPreviousDate(String froomdate, String toDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy");
        Date convertedDate = new Date();
        Date convertedDate2 = new Date();

        try {
            convertedDate = dateFormat.parse(froomdate);
            convertedDate2 = dateFormat.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (froomdate.equalsIgnoreCase(toDate)) {
            return true;
        } else if (convertedDate2.after(convertedDate)) {
            return true;
        } else {
            return false;
        }
    }


    @SuppressLint("LongLogTag")
    public static void getfile(String fileName, Bitmap bitmap, Context context) {
        try {
            Log.e("Get File Called", fileName);

            File imagefile = getFileLocation(context, fileName);
            if (imagefile.exists()) {
                //Bitmap myBitmap = BitmapFactory.decodeFile(imagefile.getAbsolutePath());

             /*   Bitmap myBitmap = decodeUri(Uri.fromFile(getFileLocation(PancardActivity.this, fileName)));
                Bitmap output = Bitmap.createScaledBitmap(myBitmap, 800, 800, true);
                Log.e("Out Put Bitmap width and heigth  ", output.getWidth() + " " + output.getWidth());*/
                imagefile.delete();

//                Drawable d = (Drawable) new BitmapDrawable(output);
//                d = getWaterMarkImage(d, getResources().getDrawable(R.drawable.alirajpur_logo));
//                output = (Bitmap) ((BitmapDrawable) d).getBitmap();

            }
        } catch (Exception e) {
        }
    }

    public static boolean isSdPresent() {

        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static File root = null;
    public static File file = null;
    public static File dir = null;

    public static File getFileLocation(Context con, String fileName) {
        if (isSdPresent() == true) {
            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) {
                root = Environment.getExternalStorageDirectory();
            } else {
                root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            }
        } else {
            root = con.getFilesDir();
        }
        try {
            if (root.canWrite()) {

                dir = new File(root.getAbsoluteFile() + "/kpt");

                if (dir.exists()) {

                    file = new File(dir, fileName);

                } else {
                    dir.mkdir();
                    file = new File(dir, fileName);
                }
            }
        } catch (Exception e) {
        }

        return file;
    }

    public static boolean getfile_camera(String fileName, Bitmap bitmap, Context context) {
        try {
            Log.e("Get File Called", fileName);

            File imagefile = getFileLocation(context, fileName);
            if (imagefile.exists()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }


    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static void saveBitmap(Bitmap bmp, Context con, String imageName) {

        try {
            if (isSdPresent() == true) {

                if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) {
                    root = Environment.getExternalStorageDirectory();
                } else {
                    root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                }
            } else {
                root = con.getFilesDir();
            }
            if (root.canWrite()) {
                dir = new File(root.getAbsoluteFile() + "/kpt");

                if (dir.exists()) {

                    file = new File(dir, imageName);
                } else {

                    dir.mkdir();
                    file = new File(dir, imageName);
                }
            }
            if (!dir.exists())
                dir.mkdirs();

            String filename = imageName.substring(imageName.lastIndexOf("/") + 1);

            File file = new File(dir, filename);
            FileOutputStream fOut = new FileOutputStream(file);
            Log.e("image save", "image save");
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
            fOut.flush();
            fOut.close();

           /* if (imageSizeCheck(file)) {
                FileOutputStream fOut = new FileOutputStream(file);
                Log.e("image save", "image save");
                bmp.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
                fOut.flush();
                fOut.close();
            } else {
                Toast.makeText(con, "Image size is too large,please upload image of <3MB", Toast.LENGTH_SHORT).show();
            }*/
        } catch (Exception e) {
            Log.e("Problem in file saving", e + "");
        }
    }

    public static String saveBitmap_gellary(Bitmap bmp, Context con, String imageName) {
        try {
            if (isSdPresent() == true) {

                if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P) {
                    root = Environment.getExternalStorageDirectory();
                } else {
                    root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                }

            } else {
                root = con.getFilesDir();

            }
            if (root.canWrite()) {
                dir = new File(root.getAbsoluteFile() + "/kpt");

                if (dir.exists()) {

                    file = new File(dir, imageName);
                } else {

                    dir.mkdir();
                    file = new File(dir, imageName);
                }
            }
            if (!dir.exists())
                dir.mkdirs();

            File file = new File(dir, imageName);
            FileOutputStream fOut = new FileOutputStream(file);
            Log.e("image save", "image save");
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, fOut);
            fOut.flush();
            fOut.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            Log.e("Problem in file saving", e + "");
        }
        return "";
    }

    public static boolean isValidPAN(String target) {
        return (!regex_matcher(r, target));
    }

    public static boolean regex_matcher(Pattern pattern, String string) {
        Matcher m = pattern.matcher(string);
        return m.find() && (m.group(0) != null);
    }



    public static boolean isValidLicenseNo(String str) {
        // Regex to check valid
        // Indian driving license number
        //RJ13 20120123456123
        String regex = "^(([A-Z]{2}[0-9]{2})"
                + "( )|([A-Z]{2}-[0-9]"
                + "{2}))((19|20)[0-9]"
                + "[0-9])[0-9]{7}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // uSing Pattern.matcher()

        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }


    public static String CreateImageName() {

        String ImageName = String.format("%d.jpg", System.currentTimeMillis());
        return ImageName;
    }

    public static boolean isValidVehicleNo(String str) {
        // Regex to check valid
        // Indian driving license number
        String regex = "^(([A-Z]{2}[0-9]{2})"
                + "( )|([A-Z]{2}-[0-9]"
                + "{2}))((19|20)[0-9]"
                + "[0-9])[0-9]{7}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // uSing Pattern.matcher()

        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }


    public static void callToast(Context context, String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean tokenExpire(Context context, String msg) {
        if (msg.equalsIgnoreCase("Token is Invalid")) {
            callToast(context, "Token is Invalid");
            Intent intent = null;
            intent = new Intent(
                    context, SignInActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } else
            return false;

    }

    public static Dialog showProgress(Context activity) {
        try {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_progress);
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String chnageDateFormateToShow(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = format.parse(time);

            format = new SimpleDateFormat("dd/MM/yyyy");
            String date = format.format(newDate);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }




    public static void closeDialog() {
        try {
            if (dialog != null)
                dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callWebsite(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.unicharm.co.jp/english/index.html"));
            Bundle b = new Bundle();
            b.putBoolean("new_window", true); //sets new window
            intent.putExtras(b);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String changeDateFormat(String str_date) {
        String str = null;
        try {
            String inputPattern = "yyyy-mm-dd";
            String outputPattern = "dd MMM yyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            Date date = null;
            date = inputFormat.parse(str_date);
            str = outputFormat.format(date);

        } catch (Exception e) {

            str = str_date;
            return str;
        }
        return str;
    }

    public static String changeDateFormatBirthday(String str_date) {
        String str = null;
        try {
            String inputPattern = "EEEE,MMMM dd";
            String outputPattern = "EEEE, MMMM dd";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            Date date = null;
            date = inputFormat.parse(str_date);
            str = outputFormat.format(date);

        } catch (Exception e) {

            str = str_date;
            return str;
        }
        return str;
    }

    public static String changeDateFormatMeeting(String str_date) {
        String str = null;
        try {
            String outputPattern = "yyyy-mm-dd";
            String inputPattern = "dd MMM yyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            Date date = null;
            date = inputFormat.parse(str_date);
            str = outputFormat.format(date);

        } catch (Exception e) {

            str = str_date;
            return str;
        }
        return str;
    }

    public static String getYear(String str_date) {
        String str = null;
        try {
            str_date = str_date.substring(0, 4);
            str = str_date;
        } catch (Exception e) {
            str = str_date;
            return str;
        }
        return str;
    }

    public static String capitalFirstLatter(String str) {
        String name = str;
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

    public static void closeBannerDialog() {
        if (dialog_banner != null) {
            dialog_banner.dismiss();
        }
    }

    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }
}
