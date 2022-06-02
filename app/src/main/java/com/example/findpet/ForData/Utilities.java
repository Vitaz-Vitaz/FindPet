package com.example.findpet.ForData;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

public class Utilities {
    private static byte[] bytes;
    public static Bitmap getBitmapFromUri(Uri uri, Context context) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
    public static Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("TAG", "Error getting bitmap", e);
        }
        return bm;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap b) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        Bitmap.CompressFormat imFor = Bitmap.CompressFormat.PNG;
//        b.compress(imFor, 0, stream);
//        bytes= stream.toByteArray();
//        b.recycle();
//        return bytes;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        if (image.length  > 0 )
        {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        else{
            return null;
        }

    }
    public static  void n(Activity activity, String text)
    {
        Toast.makeText(activity,
                text,
                Toast.LENGTH_SHORT).show();
    }
}