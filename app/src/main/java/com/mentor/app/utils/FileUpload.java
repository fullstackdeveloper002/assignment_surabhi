package com.mentor.app.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class FileUpload extends AppCompatActivity {

    private  Uri fileUri;
    private  String filePath, image, image_profile = "", img_cover = "", imgExtension = "jpg", Str_image_share = "";
    private  ArrayList<String> list_path = null;
    private  ArrayList<String> list_image = null;
    private  final int STORAGE_REQUEST = 99, REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private  final String IMAGE_DIRECTORY_NAME = "Camera";


    public  String getDataColumn(Context context, Uri uri, String selection, String[]
            selectionArgs) {
        Cursor cursor = null;
        String result = null;
        final String column = "_data";
        final String[] projection = {
                column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                result = cursor.getString(index);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return result;
    }



    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public  void onCameraResult() {
        Log.e("onCameraResult", fileUri.getPath());
        File file = new File(fileUri.getPath());

        image = getStringImage(file);
        image_profile = "" + fileUri.getPath();
        // attachmentListRefresh(image_profile);

    }

    public  String getStringImage(File file) {
        String s = "";
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //  bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] imageBytes = baos.toByteArray();
            s = Base64.encodeBytes(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public  void onCaptureImageResult() {
        Log.e("onCaptureImageResult", filePath);
        Uri imageUri = Uri.parse(filePath);
        File file = new File(imageUri.getPath());

        // bitmap factory
        //  image = getStringImage(file);

        image_profile = file.getPath();
    //    attachmentListRefresh(image_profile);
       
        Log.e("onCaptureImageResult", "" + imageUri);
        //   getViewDataBinding().headLogo.setImageURI(imageUri);
        // mBinding.ivPan.loadUrl("data:image/png;base64," + panImage);
    }

    public void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // bitmap factory
        image = getStringForBitmap(bitmap);
    }

    public String getStringForBitmap(Bitmap bitmap) {
        String s = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] imageBytes = baos.toByteArray();
            s = Base64.encodeBytes(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = null;
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        filePath = "file:" + image.getAbsolutePath();
        image_profile = filePath;
     //   attachmentListRefresh(image_profile);
        img_cover = filePath;

        return image;
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public  File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  String getPath(final Context context, final Uri uri) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];
        Uri contentUri = null;
        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        final String selection = "_id=?";
        final String[] selectionArgs = new String[]{
                split[1]};
        return getDataColumn(context, contentUri, selection, selectionArgs);
    }
}
