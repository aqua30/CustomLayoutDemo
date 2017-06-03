package com.aqua.demoproject.ui.views.font;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.aqua.demoproject.model.util.FontTypeface;
import com.aqua.demoproject.model.util.Fonts;

/**
 * Created by Saurabh(aqua) on 28-05-2017.
 */

public class IconTextView extends TextView {

    private Context context;

    public IconTextView(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView(){
        setGravity(Gravity.CENTER);
        setTypeface(FontTypeface.get(Fonts.Font_FontAwesome, context));
    }
}
