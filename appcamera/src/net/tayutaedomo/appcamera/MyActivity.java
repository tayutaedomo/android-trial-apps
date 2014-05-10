package net.tayutaedomo.appcamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {

    // Refer: http://android.keicode.com/basics/multimedia-camera-1.php
    static final int REQUEST_CAPTURE_IMAGE = 100;

    @InjectView(R.id.button)
    Button button;

    @InjectView(R.id.imageView)
    ImageView imageView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);
    }

//    protected void setListeners() {
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
//            }
//        });
//    }
    @OnClick(R.id.button)
    protected void onClick(Button button) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CAPTURE_IMAGE == requestCode && resultCode == Activity.RESULT_OK ){
            Bitmap capturedImage = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(capturedImage);
        }
    }
}
