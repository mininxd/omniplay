package com.dolby.daxappui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.usb.UsbDevice;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dolby.dax.DolbyAudioEffect;
import com.dolby.daxappui.exploreDolby.FragExploreDolbyAtmos;
import com.dolby.daxappui.headphoneTuning.FragHeadPhoneTuning;
import com.dolby.daxappui.reset.FragReset;
import com.dolby.daxappui.tutorial.TutorialActivity;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IDsFragObserver {
    private static AudioManager mAudioManager;
    private static Method methodGetDevicesForStream;
    private int mDevice;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Handler mHandler;
    private RelativeLayout mHdmiLayout;
    Notification.Builder mNotifBuilder;
    Notification mNotification;
    NotificationManager mNotificationManager;
    private RelativeLayout mPowerOffLayout;
    private String mProductVersion;
    private boolean mTabletLayout;
    private DolbyAudioEffect mDolbyAudio = null;
    private BroadcastReceiver mDolbyIntentReceiver = new BroadcastReceiver() { // from class: com.dolby.daxappui.MainActivity.1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                Log.d("MainActivity", "mDolbyIntentReceiver, action = " + action);
                if (action.equals("dolby_dap_params_update")) {
                    String stringExtra = intent.getStringExtra("event name");
                    byte b = -1;
                    switch (stringExtra.hashCode()) {
                        case -1146943738:
                            if (stringExtra.equals("profile_change")) {
                                b = 1;
                            }
                            break;
                        case 173585614:
                            if (stringExtra.equals("ds_state_change")) {
                                b = 3;
                            }
                            break;
                        case 893024970:
                            if (stringExtra.equals("reset_profile_setting")) {
                                b = 2;
                            }
                            break;
                        case 1577092565:
                            if (stringExtra.equals("profile_setting_change")) {
                                b = 0;
                            }
                            break;
                    }
                    if (b == 0 || b == 1) {
                        int intExtra = intent.getIntExtra("Integer Value", 0);
                        if (MainActivity.this.mDolbyAudio == null || MainActivity.this.mDolbyAudio.getProfile() != intExtra) {
                            return;
                        }
                        if (intExtra >= 0 && intExtra < 4) {
                            MainActivity.this.profileSettingsChanged(intExtra);
                            return;
                        }
                        Log.d("MainActivity", "profile index is out of 0~3");
                        return;
                    }
                    if (b == 2) {
                        int intExtra2 = intent.getIntExtra("Integer Value", 0);
                        if (intExtra2 >= 0 && intExtra2 < 4) {
                            MainActivity.this.resetProfile(intExtra2);
                            return;
                        }
                        Log.d("MainActivity", "profile index is out of 0~3");
                        return;
                    }
                    if (b != 3) {
                        return;
                    }
                    MainActivity.this.dsPowerChanged(intent.getIntExtra("Integer Value", 0) > 0);
                    return;
                }
                if (action.equals("audio_server_restarted")) {
                    if (MainActivity.this.mDolbyAudio != null) {
                        MainActivity.this.mDolbyAudio.release();
                        MainActivity.this.mDolbyAudio = new DolbyAudioEffect(0, 0);
                    }
                    Log.d("MainActivity", "Dax effect recreate successfully");
                }
            } catch (Exception e) {
                Log.e("MainActivity", "Exception found in MainActivity::onReceive()");
                e.printStackTrace();
            }
        }
    };
    private final BroadcastReceiver mDeviceReceiver = new BroadcastReceiver() { // from class: com.dolby.daxappui.MainActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ViewGroup viewGroup;
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            Log.d("MainActivity", "mDeviceReceiver, action = " + action);
            int i = 0;
            int i2 = 1;
            if ("android.intent.action.HEADSET_PLUG".equals(action)) {
                if (extras.getInt("state") != 0) {
                    MainActivity.this.mDevice = 8;
                    i = 1;
                }
            } else {
                if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                    if (usbDevice != null) {
                        int i3 = 0;
                        while (i < usbDevice.getInterfaceCount()) {
                            if (usbDevice.getInterface(i).getInterfaceClass() == 1) {
                                MainActivity.this.mDevice = 16384;
                                i3 = 1;
                            }
                            i++;
                        }
                        i = i3;
                    }
                } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    if ((MainActivity.this.getActiveDevices() & 67125248) != 0) {
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if ("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == 2) {
                        MainActivity.this.mDevice = 128;
                        i = 1;
                    } else if (intExtra == 0 && (MainActivity.this.getActiveDevices() & 896) != 0) {
                        try {
                            Thread.sleep(200L);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                    if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1) == 10 && (MainActivity.this.getActiveDevices() & 896) != 0) {
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                } else if ("android.intent.action.HDMI_PLUGGED".equals(action) || "android.media.action.HDMI_AUDIO_PLUG".equals(action)) {
                    if (intent.getBooleanExtra("state", false)) {
                        if (MainActivity.this.mDrawerLayout != null) {
                            ViewGroup viewGroup2 = (ViewGroup) MainActivity.this.mHdmiLayout.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(MainActivity.this.mHdmiLayout);
                            }
                            MainActivity.this.mDrawerLayout.addView(MainActivity.this.mHdmiLayout);
                        }
                    } else if (MainActivity.this.mHdmiLayout != null && (viewGroup = (ViewGroup) MainActivity.this.mHdmiLayout.getParent()) != null) {
                        viewGroup.removeView(MainActivity.this.mHdmiLayout);
                    }
                    i = 1;
                }
                i2 = i;
                i = 1;
            }
            if (i2 != 0) {
                if (i == 0) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.mDevice = mainActivity.getActiveDevices();
                }
                MainActivity.this.setGeqViewEnabled();
                MainActivity.this.setActivePortNotification();
            }
        }
    };

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void profileSettingsChanged(int i) {
        FragMainContent fragMainContent;
        FragProfilePanel fragProfilePanel;
        if (getSupportFragmentManager().getBackStackEntryCount() != 0 || (fragMainContent = (FragMainContent) getSupportFragmentManager().findFragmentById(R.id.containerView)) == null) {
            return;
        }
        FragProfilePresets fragProfilePresets = (FragProfilePresets) fragMainContent.getChildFragmentManager().findFragmentById(R.id.fragProfilePanel);
        if (fragProfilePresets != null) {
            fragProfilePresets.updateProfileSettings(i);
        }
        if (!this.mTabletLayout || (fragProfilePanel = (FragProfilePanel) fragMainContent.getChildFragmentManager().findFragmentById(R.id.fragProfilePanelTablet)) == null) {
            return;
        }
        fragProfilePanel.updateProfilePanel(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getActiveDevices() {
        try {
            return ((Integer) methodGetDevicesForStream.invoke(mAudioManager, 3)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActivePortNotification() {
        int activePort = getActivePort();
        if (activePort == Constants.DEFAULT_SPEAKER_PORT) {
            this.mNotifBuilder.setSmallIcon(R.drawable.ic_speaker_statusbar);
            this.mNotificationManager.notify(1, this.mNotification);
        } else if (activePort == Constants.DEFAULT_HEADPHONE_PORT || activePort == Constants.DEFAULT_USB_PORT) {
            this.mNotifBuilder.setSmallIcon(R.drawable.ic_headphone_statusbar);
            this.mNotificationManager.notify(1, this.mNotification);
        } else if (activePort == Constants.DEFAULT_BLUETOOTH_PORT) {
            this.mNotifBuilder.setSmallIcon(R.drawable.ic_bluetooth_statusbar);
            this.mNotificationManager.notify(1, this.mNotification);
        } else {
            this.mNotificationManager.cancel(1);
        }
    }

    void setGeqViewEnabled() {
        FragMainContent fragMainContent;
        FragProfilePanel fragProfilePanel;
        DolbyAudioEffect dolbyAudioEffect;
        if (getSupportFragmentManager().getBackStackEntryCount() != 0 || (fragMainContent = (FragMainContent) getSupportFragmentManager().findFragmentById(R.id.containerView)) == null) {
            return;
        }
        FragProfilePresets fragProfilePresets = (FragProfilePresets) fragMainContent.getChildFragmentManager().findFragmentById(R.id.fragProfilePanel);
        if (fragProfilePresets != null && (dolbyAudioEffect = this.mDolbyAudio) != null) {
            fragProfilePresets.setGeqViewEnabled(dolbyAudioEffect.getProfile());
        }
        if (!this.mTabletLayout || (fragProfilePanel = (FragProfilePanel) fragMainContent.getChildFragmentManager().findFragmentById(R.id.fragProfilePanelTablet)) == null) {
            return;
        }
        fragProfilePanel.setGeqViewEnabled();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(DAXApplication.ConfigurationWrapper.wrapLocale(context));
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ActionBar supportActionBar;
        super.onCreate(bundle);
        this.mProductVersion = DAXApplication.getInstance().getProductVersion();
        String strSubstring = this.mProductVersion.substring(0, 3);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("dolby_dap_params_update");
        intentFilter.addAction("audio_server_restarted");
        registerReceiver(this.mDolbyIntentReceiver, intentFilter);
        try {
            this.mDolbyAudio = new DolbyAudioEffect(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Constructor<?>[] declaredConstructors = AudioManager.class.getDeclaredConstructors();
            int length = declaredConstructors.length;
            Constructor<?> constructor = null;
            for (int i = 0; i < length; i++) {
                constructor = declaredConstructors[i];
                if (constructor.getGenericParameterTypes().length == 0) {
                    break;
                }
            }
            constructor.setAccessible(true);
            mAudioManager = (AudioManager) constructor.newInstance(new Object[0]);
            methodGetDevicesForStream = mAudioManager.getClass().getDeclaredMethod("getDevicesForStream", Integer.TYPE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter2.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter2.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        if (28 <= Build.VERSION.SDK_INT) {
            intentFilter2.addAction("android.media.action.HDMI_AUDIO_PLUG");
        } else {
            try {
                Field field = Class.forName("android.view.WindowManagerPolicy").getField("ACTION_HDMI_PLUGGED");
                if (field.getType() == String.class) {
                    intentFilter2.addAction((String) field.get(null));
                }
            } catch (ClassNotFoundException e3) {
                throw new RuntimeException(e3);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        registerReceiver(this.mDeviceReceiver, intentFilter2);
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        if (26 <= Build.VERSION.SDK_INT) {
            NotificationChannel notificationChannel = new NotificationChannel("dolby_notification_channel", "dolby", 2);
            notificationChannel.setSound(null, null);
            notificationChannel.setVibrationPattern(null);
            notificationChannel.enableVibration(false);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
            this.mNotifBuilder = new Notification.Builder(this, "dolby_notification_channel");
            this.mNotifBuilder.setAutoCancel(true);
            this.mNotifBuilder.setSmallIcon(R.drawable.ic_speaker_statusbar);
            this.mNotification = this.mNotifBuilder.build();
        }
        Assets.init(this);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/SourceSansPro-Regular.otf");
        this.mTabletLayout = getResources().getBoolean(R.bool.tabletLayout);
        if (this.mTabletLayout) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() { // from class: com.dolby.daxappui.-$$Lambda$MainActivity$HSGO6IFSkcelc_691wXyeHK-KLk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$MainActivity(view);
            }
        });
        this.mDrawerLayout.addDrawerListener(this.mDrawerToggle);
        this.mDrawerToggle.syncState();
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.containerView, new FragMainContent());
        fragmentTransactionBeginTransaction.commit();
        this.mPowerOffLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.power_off, (ViewGroup) null).findViewById(R.id.poweroffLayout);
        this.mPowerOffLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (this.mDrawerLayout != null) {
            ViewGroup viewGroup3 = (ViewGroup) this.mPowerOffLayout.getParent();
            if (viewGroup3 != null) {
                viewGroup3.removeView(this.mPowerOffLayout);
            }
            this.mDrawerLayout.addView(this.mPowerOffLayout);
        }
        if (this.mTabletLayout && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#161819")));
        }
        TextView textView = (TextView) findViewById(R.id.powerOffText);
        if (strSubstring.equals("DS1")) {
            textView.setText(getResources().getString(R.string.ds1_power_off_text));
        } else {
            textView.setText(getResources().getString(R.string.power_off_text));
        }
        this.mHandler = new Handler();
        if (bundle != null && bundle.getInt("currentNavItem") == 1) {
            RelativeLayout relativeLayout = this.mPowerOffLayout;
            if (relativeLayout != null && (viewGroup2 = (ViewGroup) relativeLayout.getParent()) != null) {
                viewGroup2.removeView(this.mPowerOffLayout);
            }
            onNavigationItemSelected(navigationView.getMenu().getItem(bundle.getInt("currentNavItem")));
        }
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainLayout);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dolby.daxappui.MainActivity.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                TextView textView2 = (TextView) MainActivity.this.findViewById(R.id.dsLogoText);
                if (MainActivity.this.mProductVersion.substring(0, 3).equals("DS1")) {
                    navigationView.getMenu().findItem(R.id.nav_exploredolby).setVisible(false);
                    textView2.setText(MainActivity.this.getResources().getString(R.string.app_name_ds1));
                } else {
                    textView2.setText(MainActivity.this.getResources().getString(R.string.app_name));
                }
            }
        });
        this.mHdmiLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.hdmi_page, (ViewGroup) null).findViewById(R.id.hdmi_layout);
        this.mHdmiLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout relativeLayout2 = this.mHdmiLayout;
        if (relativeLayout2 != null && (viewGroup = (ViewGroup) relativeLayout2.getParent()) != null) {
            viewGroup.removeView(this.mHdmiLayout);
        }
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            try {
                if (dolbyAudioEffect.getTuningDevicesList(Constants.DEFAULT_HEADPHONE_PORT).length == 1) {
                    navigationView.getMenu().findItem(R.id.nav_headphonetuning).setVisible(false);
                }
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
            }
        }
    }

    public /* synthetic */ void lambda$onCreate$0$MainActivity(View view) {
        onBackPressed();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        Log.i("MainActivity", "onPause()");
        super.onPause();
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            dolbyAudioEffect.release();
            this.mDolbyAudio = null;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        Log.i("MainActivity", "onResume()");
        super.onResume();
        if (this.mDolbyAudio == null) {
            try {
                this.mDolbyAudio = new DolbyAudioEffect(0, 0);
                if (!this.mDolbyAudio.hasControl()) {
                    Log.w("MainActivity", "Dolby audio effect is out of control");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            setInitUIState();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.mDevice = getActiveDevices();
        setActivePortNotification();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        DolbyAudioEffect dolbyAudioEffect;
        Log.i("MainActivity", "onWindowFocusChanged(" + z + ")");
        super.onWindowFocusChanged(z);
        if (!z || this.mDolbyAudio != null) {
            if (z || (dolbyAudioEffect = this.mDolbyAudio) == null) {
                return;
            }
            dolbyAudioEffect.release();
            this.mDolbyAudio = null;
            return;
        }
        try {
            this.mDolbyAudio = new DolbyAudioEffect(0, 0);
            if (!this.mDolbyAudio.hasControl()) {
                Log.w("MainActivity", "Dolby audio effect is out of control");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            setInitUIState();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            dolbyAudioEffect.release();
            this.mDolbyAudio = null;
        }
        unregisterReceiver(this.mDolbyIntentReceiver);
        unregisterReceiver(this.mDeviceReceiver);
        this.mNotificationManager.cancel(1);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mDrawerLayout.closeDrawer(8388611);
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
            return;
        }
        supportFragmentManager.executePendingTransactions();
        supportFragmentManager.popBackStack();
        ((RelativeLayout) findViewById(R.id.dolbyToolbar)).setVisibility(0);
        TextView textView = (TextView) findViewById(R.id.dsLogoText);
        if (textView != null) {
            textView.setVisibility(0);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (this.mTabletLayout) {
                supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#161819")));
            } else {
                supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E2426")));
            }
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        supportFragmentManager.executePendingTransactions();
        if (supportFragmentManager.getBackStackEntryCount() == 0) {
            this.mDrawerToggle.setDrawerIndicatorEnabled(true);
            this.mDrawerLayout.setDrawerLockMode(0);
            setInitUIState();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Menu menu = ((NavigationView) findViewById(R.id.nav_view)).getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isChecked()) {
                bundle.putInt("currentNavItem", i);
            }
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_exploredolby) {
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.replace(R.id.containerView, new FragExploreDolbyAtmos());
            fragmentTransactionBeginTransaction.addToBackStack(null);
            fragmentTransactionBeginTransaction.commit();
        } else if (itemId == R.id.nav_tutorial) {
            startActivity(new Intent(this, (Class<?>) TutorialActivity.class));
        } else if (itemId == R.id.nav_reset) {
            FragmentTransaction fragmentTransactionBeginTransaction2 = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction2.replace(R.id.containerView, new FragReset());
            fragmentTransactionBeginTransaction2.addToBackStack(null);
            fragmentTransactionBeginTransaction2.commit();
        } else if (itemId == R.id.nav_headphonetuning) {
            FragmentTransaction fragmentTransactionBeginTransaction3 = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction3.replace(R.id.containerView, new FragHeadPhoneTuning());
            fragmentTransactionBeginTransaction3.addToBackStack(null);
            fragmentTransactionBeginTransaction3.commit();
        }
        if (itemId == R.id.nav_exploredolby || itemId == R.id.nav_reset || itemId == R.id.nav_headphonetuning) {
            ((RelativeLayout) findViewById(R.id.dolbyToolbar)).setVisibility(8);
            TextView textView = (TextView) findViewById(R.id.dsLogoText);
            if (textView != null) {
                textView.setVisibility(8);
            }
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorActionBar, getTheme())));
                supportActionBar.setDisplayShowTitleEnabled(true);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(menuItem.getTitle());
                spannableStringBuilder.setSpan(new TypefaceSpan("fonts/SourceSansPro-Regular.otf"), 0, spannableStringBuilder.length(), 33);
                supportActionBar.setTitle(spannableStringBuilder);
            }
            this.mDrawerToggle.setDrawerIndicatorEnabled(false);
            this.mDrawerLayout.setDrawerLockMode(1);
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.dolby.daxappui.MainActivity.4
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.mDrawerLayout.closeDrawer(8388611);
            }
        }, 250L);
        return true;
    }

    private void setInitUIState() {
        DolbyAudioEffect dolbyAudioEffect;
        if (getSupportFragmentManager().getBackStackEntryCount() != 0 || (dolbyAudioEffect = this.mDolbyAudio) == null) {
            return;
        }
        dsPowerChanged(dolbyAudioEffect.getDsOn());
        int numOfProfiles = this.mDolbyAudio.getNumOfProfiles();
        int profile = this.mDolbyAudio.getProfile();
        if (4 <= profile && profile < numOfProfiles) {
            profile = 0;
        }
        chooseProfile(profile);
        profileSettingsChanged(profile);
    }

    public void dsPowerChanged(boolean z) {
        FragMainContent fragMainContent;
        FragMainContent fragMainContent2;
        ViewGroup viewGroup;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.profiletable);
        ViewPager viewPager = (ViewPager) findViewById(R.id.profileViewpager);
        ListView listView = (ListView) findViewById(R.id.presetsListView);
        ImageView imageView = (ImageView) findViewById(R.id.powerButtonOn);
        if (z) {
            RelativeLayout relativeLayout = this.mPowerOffLayout;
            if (relativeLayout != null && (viewGroup = (ViewGroup) relativeLayout.getParent()) != null) {
                viewGroup.removeView(this.mPowerOffLayout);
            }
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_power_on_titlebar, getTheme()));
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                if (this.mTabletLayout) {
                    supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#161819")));
                } else {
                    supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E2426")));
                }
                supportActionBar.setDisplayShowHomeEnabled(true);
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setHomeButtonEnabled(true);
            }
            if (tabLayout != null) {
                LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
                tabLayout.setBackgroundColor(getResources().getColor(R.color.colorTitleBar, getTheme()));
                linearLayout.setEnabled(true);
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    linearLayout.getChildAt(i).setClickable(true);
                }
                tabLayout.setTabTextColors(getResources().getColor(R.color.colorProfileDesText, getTheme()), getResources().getColor(R.color.colorSelectedTabText, getTheme()));
                tabLayout.setSelectedTabIndicatorHeight((int) (getResources().getDisplayMetrics().density * 4.0f));
            }
            if (viewPager != null) {
                viewPager.setVisibility(0);
            }
            if (listView != null) {
                listView.setEnabled(true);
            }
            if (getSupportFragmentManager().getBackStackEntryCount() == 0 && (fragMainContent2 = (FragMainContent) getSupportFragmentManager().findFragmentById(R.id.containerView)) != null && fragMainContent2.getView() != null && this.mTabletLayout) {
                FragProfilePanel fragProfilePanel = (FragProfilePanel) fragMainContent2.getChildFragmentManager().findFragmentById(R.id.fragProfilePanelTablet);
                if (fragProfilePanel != null && fragProfilePanel.getView() != null) {
                    fragProfilePanel.getView().setVisibility(0);
                }
                ((RelativeLayout) fragMainContent2.getView().findViewById(R.id.titleBar)).setBackgroundColor(getResources().getColor(R.color.colorTitleBar, getTheme()));
            }
            this.mDrawerLayout.setDrawerLockMode(0);
            this.mDrawerToggle.setDrawerIndicatorEnabled(true);
            this.mDrawerToggle.syncState();
            return;
        }
        this.mDrawerLayout.setDrawerLockMode(1);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 && (fragMainContent = (FragMainContent) getSupportFragmentManager().findFragmentById(R.id.containerView)) != null && fragMainContent.getView() != null && this.mTabletLayout) {
            FragProfilePanel fragProfilePanel2 = (FragProfilePanel) fragMainContent.getChildFragmentManager().findFragmentById(R.id.fragProfilePanelTablet);
            if (fragProfilePanel2 != null && fragProfilePanel2.getView() != null) {
                fragProfilePanel2.getView().setVisibility(4);
            }
            ((RelativeLayout) fragMainContent.getView().findViewById(R.id.titleBar)).setBackground(new ColorDrawable(getResources().getColor(R.color.colorControlPanelBackground, getTheme())));
        }
        if (listView != null) {
            listView.setEnabled(false);
        }
        if (viewPager != null) {
            viewPager.setVisibility(4);
        }
        if (tabLayout != null) {
            tabLayout.setBackgroundColor(getResources().getColor(R.color.colorNavBackground, getTheme()));
            LinearLayout linearLayout2 = (LinearLayout) tabLayout.getChildAt(0);
            linearLayout2.setEnabled(false);
            for (int i2 = 0; i2 < linearLayout2.getChildCount(); i2++) {
                linearLayout2.getChildAt(i2).setClickable(false);
            }
            tabLayout.setTabTextColors(getResources().getColor(R.color.colorProfileTextOff, getTheme()), getResources().getColor(R.color.colorProfileTextOff, getTheme()));
            tabLayout.setSelectedTabIndicatorHeight(0);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(false);
            supportActionBar2.setDisplayShowHomeEnabled(true);
            supportActionBar2.setHomeButtonEnabled(false);
            this.mDrawerToggle.syncState();
            if (this.mTabletLayout) {
                supportActionBar2.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorControlPanelBackground, getTheme())));
            } else {
                supportActionBar2.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorNavBackground, getTheme())));
            }
        }
        if (this.mDrawerLayout != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.mPowerOffLayout.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.mPowerOffLayout);
            }
            this.mDrawerLayout.addView(this.mPowerOffLayout);
        }
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_power_off_titlebar, getTheme()));
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public DolbyAudioEffect getDolbyAudioEffect() {
        return this.mDolbyAudio;
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public void chooseProfile(int i) {
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null && dolbyAudioEffect.hasControl()) {
            if (this.mDolbyAudio.getProfile() != i) {
                this.mDolbyAudio.setProfile(i);
                return;
            }
            return;
        }
        Log.w("MainActivity", "Dolby audio effect is out of control in chooseProfile");
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public int getActivePort() {
        int i = this.mDevice;
        if (i == 2) {
            return Constants.DEFAULT_SPEAKER_PORT;
        }
        if (i == 4 || i == 8) {
            return Constants.DEFAULT_HEADPHONE_PORT;
        }
        if (i == 128) {
            return Constants.DEFAULT_BLUETOOTH_PORT;
        }
        if (i == 1024) {
            return Constants.DEFAULT_HDMI_PORT;
        }
        if (i == 16384) {
            return Constants.DEFAULT_USB_PORT;
        }
        if (i == 32768) {
            return Constants.DEFAULT_MIRACAST_PORT;
        }
        return Constants.DEFAULT_SPEAKER_PORT;
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public void resetProfile(int i) {
        profileSettingsChanged(i);
    }
}
