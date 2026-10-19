package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseActivity extends Activity {
    private static final String LOG_TAG = "SoundEnhancement";
    private static final String SETTINGS_SEARCH_INTENT = "com.android.settings.action.SETTINGS_SEARCH";
    private static final String SETTINGS_SEARCH_PACKAGE_NAME = "com.android.settings.intelligence";

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu == null) {
            return false;
        }
        Intent intent = new Intent(SETTINGS_SEARCH_INTENT);
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, 0);
        if (resolveInfos.size() != 1) {
            return false;
        }
        MenuItem searchItem = menu.add(0, 0, 0, R.string.search_menu);
        searchItem.setIcon(R.drawable.ic_search_24dp);
        searchItem.setShowAsAction(2);
        searchItem.setOnMenuItemClickListener(new AnonymousClass1(intent));
        return true;
    }

    public boolean onCreateOptionsMenuOld(Menu menu) {
        if (menu == null) {
            return false;
        }
        Intent intent = new Intent(SETTINGS_SEARCH_INTENT);
        intent.setPackage(SETTINGS_SEARCH_PACKAGE_NAME);
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, 0);
        if (resolveInfos.size() != 1) {
            return false;
        }
        MenuItem searchItem = menu.add(0, 0, 0, R.string.search_menu);
        searchItem.setIcon(R.drawable.ic_search_24dp);
        searchItem.setShowAsAction(2);
        searchItem.setOnMenuItemClickListener(new AnonymousClass1(intent));
        return true;
    }

    /* JADX INFO: renamed from: com.sonyericsson.soundenhancement.BaseActivity$1, reason: invalid class name */
    class AnonymousClass1 implements MenuItem.OnMenuItemClickListener {
        final /* synthetic */ Intent val$intent;

        AnonymousClass1(Intent intent) {
            this.val$intent = intent;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                BaseActivity.this.startActivityForResult(this.val$intent, 0);
                return true;
            } catch (ActivityNotFoundException e) {
                return true;
            }
        }
    }
}
