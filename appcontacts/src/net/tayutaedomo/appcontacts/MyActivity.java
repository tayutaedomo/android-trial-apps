package net.tayutaedomo.appcontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

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

        //String[] projection = null;
        //String selection = null;
        //String[] selectionArgs = new String[] {};
        //Cursor c = getContentResolver().query(
        //        ContactsContract.Data.CONTENT_URI, projection, selection,
        //        selectionArgs, null);

        String lastLookupKey = null;
        Cursor c = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.LOOKUP_KEY, ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null);
        c.moveToPosition(-1);
        while (c.moveToNext()) {
            int contactsId = c.getInt(0);
            String lookupKey = c.getString(1);
            String displayName = c.getString(2);
            Log.d("contacts", "contactsId" + contactsId);
            Log.d("lookupKey", "lookupKey" + lookupKey);
            Log.d("displayName", "displayName" + displayName);
            lastLookupKey = lookupKey;
        }

        logDisplayNameByLookupKey(lastLookupKey);
    }

    public void logDisplayNameByLookupKey(String lookupKey) {
        Uri lookupUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);

        Cursor c = getContentResolver().query(
                lookupUri,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null);
        try {
            c.moveToFirst();
            Log.d("logDisplayNameByLookupKey", c.getString(0));
        } finally {
            c.close();
        }
    }

    public void logDisplayNameByLookupKey(int contactId, String lookupKey) {
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(contactId, lookupKey);

        Cursor c = getContentResolver().query(
                lookupUri,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null);
        try {
            c.moveToFirst();
            Log.d("displayNameByLookupKey", c.getString(0));
        } finally {
            c.close();
        }
    }

    public void logTelByContactsId(String contactsId) {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",
                    new String[]{contactsId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE},
                    null);
            // 電話番号ループ
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                String tel = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.Data.DATA1));
                Log.d("logTelByContactsId", tel);
            }
        } finally {
            cursor.close();
        }
    }
}
