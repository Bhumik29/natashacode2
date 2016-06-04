package com.bhumika29.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.buddy.sdk.Buddy;
import com.buddy.sdk.BuddyCallback;
import com.buddy.sdk.BuddyClient;
import com.buddy.sdk.BuddyFile;
import com.buddy.sdk.BuddyResult;
import com.buddy.sdk.models.Checkin;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Natasha on 5/7/2016.
 */
public class profileActivity extends AppCompatActivity {
    static final int CAM_REQUEST = 1;


    private File getFile() {
        // Create an image file name
        File image = null;
        try {
            String imageFileName = "JPEG_";
            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            image = File.createTempFile("MyPicture", ".jpg", storageDir);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return image;}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        final BuddyFile buddyFile = new BuddyFile(getFile(), "image/jpg");
        Context myContext = getApplicationContext(); // If there is no context, set myContext to null
        final BuddyClient init = Buddy.init(getApplicationContext(), "bbbbbc.plvnwCtmFqFhc", "f2f7c6c8-fbce-5ca5-4dc3-b98aa661755b");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        ImageButton button= (ImageButton) (findViewById(R.id.profile));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_Intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();


                camera_Intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                camera_Intent.setType("image/*");

                startActivityForResult(camera_Intent, CAM_REQUEST);



        }

}
        );
        Button delete= (Button) findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Buddy.<Checkin>delete("/checkins/my_id", null, new BuddyCallback<Checkin>(Checkin.class) {
                    @Override
                    public void completed(BuddyResult<Checkin> result) {
                        if(result.getIsSuccess()){System.out.println("Successful delete");                    }
                }
            });
        }
    });


}
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_CANCELED) return;

        ParcelFileDescriptor fd;
        try {
            fd = getContentResolver().openFileDescriptor(data.getData(), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Bitmap bmp = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor());
        ImageButton button2= (ImageButton) (findViewById(R.id.profile));
        button2.setImageBitmap(bmp);
    }}
