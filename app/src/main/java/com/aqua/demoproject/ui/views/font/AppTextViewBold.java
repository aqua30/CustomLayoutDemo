package com.aqua.demoproject.ui.views.font;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aqua.demoproject.model.util.FontTypeface;
import com.aqua.demoproject.model.util.Fonts;

/**
 * Created by Saurabh(aqua) on 28-05-2017.
 */

public class AppTextViewBold extends TextView {

    private Context context;

    public AppTextViewBold(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public AppTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView(){
        setTypeface(FontTypeface.get(Fonts.Font_Bold, context));
    }
}
