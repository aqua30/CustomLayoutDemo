package com.aqua.demoproject.ui.views.font;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.aqua.demoproject.model.util.FontTypeface;
import com.aqua.demoproject.model.util.Fonts;

/**
 * Created by Saurabh(aqua) on 28-05-2017.
 */

public class AppEditText extends EditText {

    private Context context;

    public AppEditText(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public AppEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView(){
        setTypeface(FontTypeface.get(Fonts.Font_Regular, context));
    }
}
