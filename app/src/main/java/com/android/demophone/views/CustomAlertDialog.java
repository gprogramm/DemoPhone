package com.android.demophone.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.android.demophone.R;

/**
 * Custom alert dialog for showing warning message.
 */
public class CustomAlertDialog {

    public interface Callback {
        void onDismiss();
    }

    private Dialog dialog;
    private static CustomAlertDialog mInstance;

    public static CustomAlertDialog getInstance() {
        if (mInstance == null) {
            mInstance = new CustomAlertDialog();
        }
        return mInstance;
    }

    public void show(Context context, Callback callback) {
        try {
            if (dialog != null && dialog.isShowing()) {
                return;
            }

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_alert);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.setCancelable(false);
            //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            // -------------------- Components ----------------------//
            TextView txtCancel = (TextView) dialog.findViewById(R.id.txtCancel);

            // ---------------------- CANCEL ----------------------
            txtCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    callback.onDismiss();
                }
            });

            // Show the dialog
            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dismiss the dialog
     */
    public void dismiss() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
