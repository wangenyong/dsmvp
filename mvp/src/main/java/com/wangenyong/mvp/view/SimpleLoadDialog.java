package com.wangenyong.mvp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.wangenyong.mvp.R;
import com.wangenyong.mvp.http.ProgressCancelListener;

import java.lang.ref.WeakReference;

/**
 * @author wangenyong
 * @date 2017/5/23
 */

public class SimpleLoadDialog extends Handler {

    private AlertDialog load = null;

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private final WeakReference<Context> reference;

    public SimpleLoadDialog(Context context, ProgressCancelListener mProgressCancelListener,
                            boolean cancelable) {
        super();
        this.reference = new WeakReference<Context>(context);
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void create(String info) {
        if (load == null) {
            context = reference.get();
            load = new AlertDialog.Builder(context).create();
            load.setCanceledOnTouchOutside(false);
            load.setCancelable(cancelable);
            load.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (mProgressCancelListener != null) {
                        mProgressCancelListener.onCancelProgress();
                    }
                }
            });
            if (!load.isShowing() && context != null) {
                load.show();
            }
            Window dialogWindow = load.getWindow();
            dialogWindow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogWindow.setContentView(R.layout.dialog_load_layout);
            if (!TextUtils.isEmpty(info.trim())) {
                ((TextView) (dialogWindow.findViewById(R.id.content_text))).setText(info);
            }
        }
    }

    public void show(String info) {
        create(info);
    }


    public void dismiss() {
        context = reference.get();
        if (load != null && load.isShowing() && !((Activity) context).isFinishing()) {
            String name = Thread.currentThread().getName();
            load.dismiss();
            load = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                create("");
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismiss();
                break;
            default:
        }
    }
}

