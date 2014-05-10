package net.tayutaedomo.appinstalledlist;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    @InjectView(R.id.listView) ListView listView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);

        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> applicationInfo = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        ArrayList<String> appList = new ArrayList<String>();
        for (ApplicationInfo info : applicationInfo) {
            // For avoiding preinstall apps
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) continue;

            // For avoiding self app
            if (info.packageName.equals(this.getPackageName())) continue;

            appList.add((String)packageManager.getApplicationLabel(info));
            Log.d("appinstalledlist", (String) packageManager.getApplicationLabel(info));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appList);
        listView.setAdapter(adapter);
    }
}
