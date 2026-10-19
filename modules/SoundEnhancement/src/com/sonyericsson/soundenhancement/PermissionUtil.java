package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.sonyericsson.soundenhancement.ahc.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionUtil {
    private static final boolean DBG = false;
    public static final int REQUEST_MULTIPLE_PERMISSIONS = 20;
    public static final int REQUEST_PERMISSION = 10;
    private static final String TAG = "PermissionUtil";
    private static final String[] MICROPHONE_PERMISSIONS = {"android.permission.RECORD_AUDIO"};
    public static final HashMap<String, String[]> ALL_PERMISSION_GROUP_MAP = new HashMap<>();

    static {
        ALL_PERMISSION_GROUP_MAP.put("android.permission-group.MICROPHONE", MICROPHONE_PERMISSIONS);
    }

    public static String getPermissionGroupDisplayLabel(Context context, String permissionGrpName) {
        if (TextUtils.isEmpty(permissionGrpName)) {
            return "";
        }
        try {
            PermissionGroupInfo groupInfo = context.getPackageManager().getPermissionGroupInfo(permissionGrpName, 128);
            if (groupInfo == null) {
                return "";
            }
            CharSequence label = groupInfo.loadLabel(context.getPackageManager());
            if (TextUtils.isEmpty(label)) {
                return "";
            }
            String permissionGrpLabel = label.toString();
            return permissionGrpLabel;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.LOGD("permissionGroupInfo label :" + permissionGrpName);
            return "";
        }
    }

    public static String getRationaleTextMessage(Activity activity, String[] needPermissions) {
        Set<String> needPermissionGroupNames = null;
        if (needPermissions == null || needPermissions.length <= 0) {
            return "";
        }
        for (int i = 0; i < needPermissions.length; i++) {
            if (activity.shouldShowRequestPermissionRationale(needPermissions[i])) {
                Logger.LOGD("shouldShowRequestPermissionRationale is true for " + needPermissions[i]);
                String permissionGrpName = getPermissionGroupName(activity, needPermissions[i]);
                if (!TextUtils.isEmpty(permissionGrpName)) {
                    if (needPermissionGroupNames == null) {
                        needPermissionGroupNames = new HashSet<>();
                    }
                    needPermissionGroupNames.add(permissionGrpName);
                    Logger.LOGD("PermissionGroupNames : " + permissionGrpName + " : " + needPermissions[i]);
                }
            }
        }
        if (needPermissionGroupNames == null || needPermissionGroupNames.size() <= 0) {
            return "";
        }
        String rationaleMessage = createRationalemessage(activity, needPermissionGroupNames);
        return rationaleMessage;
    }

    public static String createRationalemessage(Context context, Set<String> permissionGrps) {
        StringBuffer stringBuffer = new StringBuffer();
        if (permissionGrps != null && permissionGrps.size() > 0) {
            for (String element : permissionGrps) {
                String label = "";
                String text = "";
                Logger.LOGD("createRationalemessage for :" + element);
                if (element.equals("android.permission-group.MICROPHONE")) {
                    label = getPermissionGroupDisplayLabel(context, "android.permission-group.MICROPHONE");
                    text = context.getString(R.string.sound_enhance_strings_permission_dialog_message_microphone_sub_txt);
                }
                stringBuffer.append(label);
                stringBuffer.append(" :\n");
                stringBuffer.append(text);
                stringBuffer.append("\n\n");
            }
        }
        String message = stringBuffer.toString();
        if (!TextUtils.isEmpty(message)) {
            Pattern pattern = Pattern.compile("\n+$");
            Matcher matcher = pattern.matcher(message);
            return matcher.replaceAll("");
        }
        return message;
    }

    public static String[] getAllDeniedPermissionsFromGrps(Activity activity, String[] permissionGrpLists) {
        ArrayList<String> list = new ArrayList<>();
        for (String permissionGrp : permissionGrpLists) {
            String[] eachPermisison = getAllDeniedPermissions(activity, ALL_PERMISSION_GROUP_MAP.get(permissionGrp));
            if (eachPermisison != null && eachPermisison.length > 0) {
                list.addAll(Arrays.asList(eachPermisison));
            }
        }
        String[] needAllPermissions = (String[]) list.toArray(new String[list.size()]);
        return needAllPermissions;
    }

    public static String[] getAllDeniedPermissions(Activity activity, String[] permissions) {
        if (permissions == null || permissions.length <= 0) {
            return null;
        }
        Set<String> permissionsToRequest = null;
        for (String permission : permissions) {
            boolean permissionDenied = isPermissionDenied(activity, permission);
            if (permissionDenied) {
                if (permissionsToRequest == null) {
                    permissionsToRequest = new HashSet<>();
                }
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest == null || permissionsToRequest.size() <= 0) {
            return null;
        }
        Logger.LOGD("requestPermissions:" + activity.getPackageName() + " permissionsToRequest:" + permissionsToRequest);
        String[] needPermissions = new String[permissionsToRequest.size()];
        permissionsToRequest.toArray(needPermissions);
        return needPermissions;
    }

    public static boolean isPermissionDenied(Context context, String permission) {
        if (context != null && !TextUtils.isEmpty(permission)) {
            int selfPermission = context.checkSelfPermission(permission);
            if (selfPermission == -1) {
                Logger.LOGD("permissionDenied:" + permission);
                return true;
            }
            return false;
        }
        return false;
    }

    public static Intent createSettingAppInfoViewIntent(String packagename) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + packagename));
        intent.setFlags(268468224);
        return intent;
    }

    public static String getPermissionGroupName(Context context, String permission) {
        try {
            Logger.LOGD("try to getPermissionGroupName:" + permission);
            PermissionInfo info = context.getPackageManager().getPermissionInfo(permission, 128);
            String groupName = info.group;
            return groupName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.LOGD("NameNotFoundException occured for " + permission);
            return "";
        }
    }
}
