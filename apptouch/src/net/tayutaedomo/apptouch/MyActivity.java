package net.tayutaedomo.apptouch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

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

    @InjectView(R.id.timerView)
    TextView timerView;

    final long TOUCH_INTERVAL = 1000;

    float laptime = 0.0f;
    Timer timer;
    Handler handler = new Handler();

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
                Log.d(LOG_TAG, "BUTTON 1 onTouch, action:" + event.getAction());

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchedButton = null;
                    then = 0;
                    textView.setText("");

                    endTimer();
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchedButton = button1;
                    then = System.currentTimeMillis();
                    textView.setText("BUTTON 1 onTouch");

                    startTimer();
                }

                if (then != 0 && System.currentTimeMillis() - then > TOUCH_INTERVAL) {
                    showMessage("BUTTON 1");
                }

                return false;
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(LOG_TAG, "BUTTON 2 onTouch, action:" + event.getAction());

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchedButton = null;
                    then = 0;
                    textView.setText("");

                    endTimer();
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchedButton = button2;
                    then = System.currentTimeMillis();
                    textView.setText("BUTTON 2 onTouch");

                    startTimer();
                }

                if (then != 0 && System.currentTimeMillis() - then > TOUCH_INTERVAL) {
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

    protected void startTimer() {
        if(timer == null){
            //タイマーの初期化処理
            laptime = 0.0f;
            timer = new Timer(true);

            timer.schedule( new TimerTask(){
                @Override
                public void run() {
                    handler.post( new Runnable() {
                        public void run() {

                            laptime +=  0.1d;

                            BigDecimal bi = new BigDecimal(laptime);
                            float outputValue = bi.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                            timerView.setText(Float.toString(outputValue));
                        }
                    });
                }
            }, 100, 100);
        }
    }

    protected void endTimer() {
        if(timer != null){
            timer.cancel();
            timer = null;
        }

        timerView.setText("0.0");
    }
}
