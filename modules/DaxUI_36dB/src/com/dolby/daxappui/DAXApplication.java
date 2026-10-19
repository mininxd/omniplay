package com.dolby.daxappui;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public class DAXApplication extends Application {
    static int mBaseDensity;
    static DAXApplication mDAXApplication;
    static int mDefaultDensity;

    public DAXApplication() {
        mDAXApplication = this;
    }

    public static DAXApplication getInstance() {
        return mDAXApplication;
    }

    public String getProductVersion() {
        try {
            return getPackageManager().getPackageInfo("com.dolby.daxappui", 16384).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getProfileNames() {
        return new String[]{getString(R.string.dynamic), getString(R.string.movie), getString(R.string.music), getString(R.string.custom)};
    }

    public static class ConfigurationWrapper {
        private static Context wrapConfiguration(Context context, Configuration configuration) {
            return context.createConfigurationContext(configuration);
        }

        public static Context wrapLocale(Context context) {
            Configuration configuration = new Configuration();
            int iIntValue = configuration.densityDpi;
            Point point = new Point();
            try {
                Object objInvoke = Class.forName("android.view.WindowManagerGlobal").getMethod("getWindowManagerService", new Class[0]).invoke(null, new Object[0]);
                iIntValue = ((Integer) objInvoke.getClass().getMethod("getInitialDisplayDensity", Integer.TYPE).invoke(objInvoke, 0)).intValue();
                DAXApplication.mDefaultDensity = iIntValue;
                DAXApplication.mBaseDensity = ((Integer) objInvoke.getClass().getMethod("getBaseDisplayDensity", Integer.TYPE).invoke(objInvoke, 0)).intValue();
                objInvoke.getClass().getMethod("getInitialDisplaySize", Integer.TYPE, Point.class).invoke(objInvoke, 0, point);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            int iMin = (int) (Math.min(point.y, point.x) / (iIntValue / 160.0f));
            if (iMin >= 600) {
                configuration.fontScale = iMin / 800.0f;
            } else if (iMin >= 360) {
                configuration.fontScale = iMin / 360.0f;
            }
            configuration.densityDpi = iIntValue;
            configuration.smallestScreenWidthDp = iMin;
            configuration.screenWidthDp = iMin;
            return wrapConfiguration(context, configuration);
        }
    }

    public float getIeqViewWidth() {
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return (point.x - ((((getResources().getDimensionPixelOffset(R.dimen.ieq_margin_start) + getResources().getDimensionPixelOffset(R.dimen.ieq_margin_end)) + (getResources().getDimensionPixelOffset(R.dimen.ieq_horizontal_spacing_normal) * 2)) + (getResources().getDimensionPixelOffset(R.dimen.ieq_horizontal_spacing_off) * 3)) / (mBaseDensity / mDefaultDensity))) / 4.0f;
    }
}
