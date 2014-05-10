package net.tayutaedomo.appimages;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    private final String LOG_TAG = "appimages";

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

        Log.d(LOG_TAG, "Start");

        //loadFromSDCard();

        List<Uri> uriList = getImageUriListFromSDCard();

        // Refer: http://www.javadrive.jp/android/imageview/index2.html
        try {
            Bitmap bitmap = getBitmap(uriList.get(0));
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Refer: http://labs.techfirm.co.jp/android/cho/2154
    private void loadFromSDCard() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor c = getContentResolver().query(uri, null, null, null, null);

        Log.i(LOG_TAG, "DISPLAYING IMAGES = " + c.getCount());

        c.moveToFirst();
        for (int k = 0; k < c.getCount(); k++) {
            Log.i(LOG_TAG, "ID = " + c.getString(c.getColumnIndexOrThrow("_id")));
            for (String column : c.getColumnNames()) {
                Log.i(LOG_TAG, column + "=" + c.getString(c.getColumnIndexOrThrow(column)));
            }
            c.moveToNext();
        }
    }
    public List<Uri> getImageUriListFromSDCard() {
        List<Uri> uriList = new ArrayList<Uri>();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor c = getContentResolver().query(uri, null, null, null, null);

        c.moveToFirst();
        for (int k = 0; k < c.getCount(); k++) {
            int fieldIndex = c.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = c.getLong(fieldIndex);
            Log.i(LOG_TAG, String.valueOf(id));

            Uri imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + id);
            Log.i(LOG_TAG, imageUri.toString());

            uriList.add(imageUri);

            c.moveToNext();
        }

        return uriList;
    }

    // Refer: http://labs.techfirm.co.jp/android/cho/2154
    public Bitmap getBitmap(Uri imageUri) throws IOException {
        BitmapFactory.Options mOptions = new BitmapFactory.Options();
        mOptions.inSampleSize = 10;
        Bitmap resizeBitmap = null;

        InputStream is = getContentResolver().openInputStream(imageUri);
        resizeBitmap = BitmapFactory.decodeStream(is, null, mOptions);
        is.close();

        return resizeBitmap;
    }
}
