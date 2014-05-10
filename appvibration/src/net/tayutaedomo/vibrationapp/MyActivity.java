package net.tayutaedomo.vibrationapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {

    final String LOG_TAG = "appvibration";

    private Context mContext;

    @InjectView(R.id.button)
    Button button;

    @InjectView(R.id.textView)
    TextView text;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mContext = this.getApplicationContext();

        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    protected void clickButton(Button button) {
        Log.i(LOG_TAG, "clickButton");
        ((Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(35);
    }

    @OnClick(R.id.textView)
    protected void clicktext(TextView textView) {
        Log.i(LOG_TAG, "clicktext");
        ((Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(70);
    }

//    @Override
//    public void onClick(View v) {
//        if (v == button) {
//            ((Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(35);
//            return ;
//        } else if (v == text) {
//            ((Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(70);
//            return ;
//        }
//        Log.d("vibrationapp", "onclick");
//    }
}
