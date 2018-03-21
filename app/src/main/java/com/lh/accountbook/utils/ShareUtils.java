package com.lh.accountbook.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lh.accountbook.R;

/**
 * Created by LuHao on 2018/3/21.
 * 分享
 */

public class ShareUtils {
    public static void shareApp(Context context) {
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.share_app_code);
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/png");
        imageIntent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(imageIntent, "给朋友分享一个很好用的记账app"));
    }
}
