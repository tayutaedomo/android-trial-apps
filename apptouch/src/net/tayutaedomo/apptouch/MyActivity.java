package net.tayutaedomo.apptouch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {

    final String LOG_TAG = "apptouch";

    @InjectView(R.id.button1)
    Button button1;

    @InjectView(R.id.button2)
    Button button2;

    @InjectView(R.id.textView)
    TextView textView;

    Button touchedButton;
    long then;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);

        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(LOG_TAG, "BUTTON 1 onTouch");

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchedButton = null;
                    then = 0;
                    textView.setText("");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchedButton = button1;
                    then = System.currentTimeMillis();
                    textView.setText("BUTTON 1 onTouch");
                }

                if (then != 0 && System.currentTimeMillis() - then > 2000) {
                    showMessage("BUTTON 1");
                }

                return false;
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(LOG_TAG, "BUTTON 2 onTouch");

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchedButton = null;
                    then = 0;
                    textView.setText("");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchedButton = button2;
                    then = System.currentTimeMillis();
                    textView.setText("BUTTON 2 onTouch");
                }

                if (then != 0 && System.currentTimeMillis() - then > 2000) {
                    showMessage("BUTTON 2");
                }

                return false;
            }
        });
    }

    protected void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

//    @OnClick(R.id.button1)
//    protected void onClickButton1(Button button) {
//        showMessage("Test Button1");
//    }
}
