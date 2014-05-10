package net.tayutaedomo.appdialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

// Refer: http://www.adakoda.com/android/000206.html
public class MyActivity extends Activity {

    @InjectView(R.id.button1)
    Button button1;

    @InjectView(R.id.button2)
    Button button2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.button1)
    protected void onClickButton1(Button button) {
        Log.d("appdialer", "onClickButton1");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:08066864040"));
        startActivity(intent);
    }

    @OnClick(R.id.button2)
    protected void onClickButton2(Button button) {
        Log.d("appdialer", "onClickButton2");
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:08066864040"));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("appdialer", "onActivityResult");
    }
}
