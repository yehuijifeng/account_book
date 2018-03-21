package com.lh.accountbook.appliaction;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuHao on 2017/3/15.
 * 判断权限
 */

public class PermissionsChecker {


    // 判断权限集合
    public String[] lacksPermissions(Context context, String[] permissions) {
        List<String> permissionsList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (lacksPermission(context, permissions[i])) {
                permissionsList.add(permissions[i]);
            }
        }
        if (permissionsList.size() > 0) {
            String[] strings = new String[permissionsList.size()];
            for (int i = 0, j = permissionsList.size(); i < j; i++) {
                strings[i] = permissionsList.get(i);
            }
            return strings;
        }
        return null;
    }

    // 判断是否缺少权限
    public static boolean lacksPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

    // 判断是否缺少权限
    public static boolean lacksPermission(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return true;
            }
        }
        return false;
    }
}
