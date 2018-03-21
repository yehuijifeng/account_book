package com.lh.accountbook.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lh.accountbook.R;


/**
 * Created by LuHao on 2017/3/13.
 * 吐司提示
 */

public enum ToastUtils {
    INSTANCE;// 实现单例
    Toast toastCustom;
    Toast toast;
    View root;
    TextView text_toast;
    ImageView img_toast;

    public void showShortToast(Context context, String str) {
        if (toast == null)
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setText(str);
        toast.show();
    }

    public void showLongToast(Context context, String str) {
        if (toast == null)
            toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.setText(str);
        toast.show();
    }


    public void showSuccessToast(Context context, String str) {
        if (toastCustom == null) {
            toastCustom = new Toast(context);
            root = LayoutInflater.from(context).inflate(R.layout.layout_default_toast, null);
            text_toast = (TextView) root.findViewById(R.id.text_toast);
            img_toast = (ImageView) root.findViewById(R.id.img_toast);
        } else if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.layout_default_toast, null);
            text_toast = (TextView) root.findViewById(R.id.text_toast);
            img_toast = (ImageView) root.findViewById(R.id.img_toast);
        }
        text_toast.setText(str);
        toastCustom.setDuration(Toast.LENGTH_LONG);
        toastCustom.setView(root);
        toastCustom.setGravity(Gravity.CENTER, 0, 0);
        toastCustom.show();
    }

    public void showErrorToast(Context context, String str) {
        if (toastCustom == null) {
            toastCustom = new Toast(context);
            root = LayoutInflater.from(context).inflate(R.layout.layout_default_toast, null);
            text_toast = (TextView) root.findViewById(R.id.text_toast);
            img_toast = (ImageView) root.findViewById(R.id.img_toast);
        } else if (root == null) {
            root = LayoutInflater.from(context).inflate(R.layout.layout_default_toast, null);
            text_toast = (TextView) root.findViewById(R.id.text_toast);
            img_toast = (ImageView) root.findViewById(R.id.img_toast);
        }
        text_toast.setText(str);
        img_toast.setImageResource(R.drawable.ic_toast_no);
        toastCustom.setDuration(Toast.LENGTH_LONG);
        toastCustom.setView(root);
        toastCustom.setGravity(Gravity.CENTER, 0, 0);
        toastCustom.show();
    }

    public void cancelToast() {
        if (toast != null)
            toast.cancel();
        if (toastCustom != null)
            toastCustom.cancel();
        img_toast = null;
        text_toast = null;
        root = null;
        toast = null;
        toastCustom = null;
    }

}
