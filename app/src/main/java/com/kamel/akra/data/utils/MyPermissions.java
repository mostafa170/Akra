package com.kamel.akra.data.utils;


import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.app.Activity;
import android.content.Context;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MyPermissions {

    /**
     * <h2>isPermissionGranted</h2>
     * <p>checks whether a specific permission is granted or not</p>
     *
     * @param context the context you're checking the permission from
     * @param permission the string permission you're checking {@link android.Manifest.permission}
     * @return boolean true if the permission is granted, false otherwise
     * @see Context
     * @see PermissionChecker
     * */
    public static boolean isPermissionGranted(Context context, String permission){
        return checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
    }

    public static void requestPermissionFragment(Fragment fragment, String[] permissions, int PERMISSION_REQUEST_CODE){
        List<String> strings = new ArrayList<>();
        for (String permission : permissions) {
            if (!isPermissionGranted(fragment.getContext(), permission))
                strings.add(permission);
        }
        if(strings.size() > 0)
            fragment.requestPermissions(strings.toArray(new String[0]), PERMISSION_REQUEST_CODE);
    }

    public static boolean isPermissionsGranted(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (!isPermissionGranted(context, permission))
                return false;
        }
        return true;
    }

    public static void requestPermissionActivity(Activity activity, String[] permissions, int PERMISSION_REQUEST_CODE){
        List<String> strings = new ArrayList<>();
        for (String permission : permissions) {
            if (!isPermissionGranted(activity, permission))
                strings.add(permission);
        }
        if(strings.size() > 0)
            activity.requestPermissions(strings.toArray(new String[0]), PERMISSION_REQUEST_CODE);
    }
}
