package com.example.findpet.Map;

import static com.example.findpet.ForData.Data.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.R;
import com.example.findpet.rest.LibraryApiVolley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class NewPointActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    public byte[]  bytes;
    NewPoint pForServer;
    LibraryApiVolley libraryApiVolley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_point);
        libraryApiVolley = new LibraryApiVolley(this);

        getSupportActionBar().hide();
        Button button = findViewById(R.id.addPhoto);
        EditText t = findViewById(R.id.addDescription);
        EditText t2 = findViewById(R.id.petName);
        EditText t3 = findViewById(R.id.petColor);
        EditText t4 = findViewById(R.id.petContact);
        Spinner spinner = findViewById(R.id.spinner);

        ImageView im = findViewById(R.id.addIMage);









        im.setImageResource(R.drawable.nophoto4);

       // Uri uri = Uri.parse(String.valueOf(R.drawable.nophoto4));

      //  im.setImageURI(null);
      //  im.setImageURI(uri);
        Button button2 =  findViewById(R.id.addPoint);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String h = t.getText().toString();
                String h2 = t2.getText().toString();
                String h3 = t3.getText().toString();
                String h4 = t4.getText().toString();
                String status = spinner.getSelectedItem().toString();
                if (status.equals("Я нашёл(а)"))
                {
                    h2 = "          ";
                }

                if (h.length() >= 6 && h2.length() >= 4 && h3.length() >= 4 && h4.length() >= 5)
                {

                   n("Сохранено!");

//                    x.add(dataX);
//                    y.add(dataY);
//                    description.add(h);
//                    photo.add(im);


                    Bitmap r = Bitmap.createScaledBitmap(((BitmapDrawable)im.getDrawable()).getBitmap(), 1000, 1000, true);

                   // byte[] a = Utilities.getBytes(((BitmapDrawable)im.getDrawable()).getBitmap());
                    byte[] a1 = Utilities.getBytes(r);
                    //n(a.toString());

                    //Bitmap bitmap = ((BitmapDrawable)((LayerDrawable)image.getDrawable()).getDrawable(0)).getBitmap(‌​);


//                    imageView.setDrawingCacheEnabled(true);
//                    imageView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
//                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//                    imageView.layout(0, 0,
//                            imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
//                    imageView.buildDrawingCache(true);
//                    Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
//                    imageView.setDrawingCacheEnabled(false);

                    NewPoint p = new NewPoint( h, a1 , dataX, dataY, h2, h3, h4, status);



                    DB db = new DB(NewPointActivity.this);
                    db.insert(p);
                    pForServer = p;
                    AnotherThread2 anotherThread=new AnotherThread2();
                    anotherThread.start();

                    Intent intent=new Intent(NewPointActivity.this, MapsActivity.class);

                    startActivity(intent);




                }
                else{
                    n("Добавьте описание");

                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*м");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
    }
    
    public void n(String text)
    {
        Toast.makeText(this,
                text,
                Toast.LENGTH_SHORT).show();
    }
    class AnotherThread2 extends Thread {
        @Override
        public void run() {

                try{

                    libraryApiVolley.addPoint(pForServer);
                } catch(Exception e) {}

            }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.addIMage);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }
}