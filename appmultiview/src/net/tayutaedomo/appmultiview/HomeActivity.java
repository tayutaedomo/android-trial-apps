package net.tayutaedomo.appmultiview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;

// Refer: http://techbooster.org/android/ui/3383/
// Refer: http://ougiitirou.web.fc2.com/AndroidMain/option_menu.html
public class HomeActivity extends Activity {

    public static final int MENU_NORMAL_ITEM = 0;
    public static final int MENU_ADDITIONAL_ITEM = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //setTheme(android.R.style.Theme_Black_NoTitleBar);   //タイトルバー(アクションバー)なし
        //setTheme(android.R.style.Theme.WithActionBar);    //アクションバーあり

        setContentView(R.layout.main);

        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        // メニューの要素を追加
        //menu.add("Normal item");
        menu.add(0, MENU_NORMAL_ITEM, 1, getString(R.string.label_menu_normal_item));

        // メニューの要素を追加して取得
        //MenuItem actionItem = menu.add("Action Button");
        MenuItem actionItem = menu.add(0, MENU_ADDITIONAL_ITEM, 2, getString(R.string.label_menu_additional_item));

        // SHOW_AS_ACTION_IF_ROOM:余裕があれば表示
        //actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        // アイコンを設定
        //actionItem.setIcon(android.R.drawable.ic_menu_share);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        Log.d("appmultiview", String.valueOf(item.getItemId()));

        switch (item.getItemId()) {
            case MENU_NORMAL_ITEM:
                Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case MENU_ADDITIONAL_ITEM:
                Intent intent = new Intent();
                intent.setClassName(
                        "net.tayutaedomo.appmultiview",
                        "net.tayutaedomo.appmultiview.SecondActivity");
                startActivity(intent);
                break;
        }

        return true;
    }

    @OnClick(R.id.button)
    protected void onClickButton1(Button button) {
        Intent intent = new Intent();
        intent.setClassName(
                "net.tayutaedomo.appmultiview",
                "net.tayutaedomo.appmultiview.SecondActivity");
        startActivity(intent);
    }
}
