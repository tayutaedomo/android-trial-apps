package net.tayutaedomo.applongclick;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MyActivity extends Activity {

    final String LOG_TAG = "applongclick";

    @InjectView(R.id.button)
    Button button;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);

        // Refer: https://gist.github.com/shisashi/1718672
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i(LOG_TAG, "action up on touch");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i(LOG_TAG, "action down on touch");
                }

                Log.d(LOG_TAG, "onTouch");

                return false;
            }
        });

        // TODO: http://stackoverflow.com/questions/9460170/how-to-config-the-response-time-for-longclick
        // TODO: http://stackoverflow.com/questions/7934245/longclick-event-happens-too-quickly-how-can-i-increase-the-clicktime-required-t
    }

    @OnClick(R.id.button)
    protected void onClick(Button button) {
        Log.i(LOG_TAG, "onClick");
    }

    @OnLongClick(R.id.button)
    protected boolean onLongClick(Button button) {
        Log.i(LOG_TAG, "onLongClick");

        return true;
    }
}
