package net.tayutaedomo.apptimer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {

    Button currentButton;

    @InjectView(R.id.textView)
    TextView textView;

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
    }

    @OnClick(R.id.button2)
    protected void onClickButton2(Button button) {
    }
}
