package net.tayutaedomo.apppreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {

    @InjectView(R.id.editText)
    EditText editText;

    @InjectView(R.id.textView)
    TextView textView;

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

        textView.setText(loadText(getApplicationContext()));
    }

    @OnClick(R.id.button)
    protected void onClick(Button button) {
        String text = editText.getText().toString();
        textView.setText(text);
        saveText(getApplicationContext(), text);
    }

    public void saveText(Context context, String text) {
        SharedPreferences pref = context.getSharedPreferences("input_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("text", text);
        editor.commit();
    }

    public String loadText(Context context) {
        SharedPreferences pref = context.getSharedPreferences("input_data", Context.MODE_PRIVATE);
        return pref.getString("text", "");
    }
}
