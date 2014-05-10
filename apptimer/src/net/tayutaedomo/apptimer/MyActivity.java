package net.tayutaedomo.apptimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

// Reference: http://techbooster.org/android/application/934/
public class MyActivity extends Activity {

    @InjectView(R.id.textView)
    TextView mTextView;

    float mLaptime = 0.0f;

    MyTimerTask timerTask = null;
    Timer mTimer = null;
    Handler mHandler = new Handler();

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
        if(mTimer == null){
            //タイマーの初期化処理
            mLaptime = 0.0f;
            mTimer = new Timer(true);

            //timerTask = new MyTimerTask();
            //mTimer.schedule(timerTask, 100, 100);
            mTimer.schedule( new TimerTask(){
                @Override
                public void run() {
                    // mHandlerを通じてUI Threadへ処理をキューイング
                    mHandler.post( new Runnable() {
                        public void run() {

                            //実行間隔分を加算処理
                            mLaptime +=  0.1d;

                            //計算にゆらぎがあるので小数点第1位で丸める
                            BigDecimal bi = new BigDecimal(mLaptime);
                            float outputValue = bi.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                            //現在のLapTime
                            mTextView.setText(Float.toString(outputValue));
                        }
                    });
                }
            }, 100, 100);
        }
    }

    @OnClick(R.id.button2)
    protected void onClickButton2(Button button) {
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            // Handlerを通じてUI Threadへ処理をキューイング
            mHandler.post( new Runnable() {
                public void run() {

                    //実行間隔分を加算処理
                    mLaptime +=  0.1d;

                    //計算にゆらぎがあるので小数点第1位で丸める
                    BigDecimal bi = new BigDecimal(mLaptime);
                    float outputValue = bi.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                    //現在のLapTime
                    mTextView.setText(Float.toString(outputValue));
                }
            });
        }
    }
}
