package com.aqua.demoproject.ui.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;


/**
 * Created by Saurabh(aqua) on 02-11-2016.
 */

public class ViewHelper {

    public static void setViewVisibility(View v, boolean isVisible){
        v.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}
