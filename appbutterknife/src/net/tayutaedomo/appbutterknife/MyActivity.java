package net.tayutaedomo.appbutterknife;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {
    @InjectView(R.id.editText) EditText editText;
    @InjectView(R.id.button) Button button;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void submit(Button button) {
        Log.d("appbutterknife", "submit");
        Log.d("appbutterknife", button.toString());
    }
}
