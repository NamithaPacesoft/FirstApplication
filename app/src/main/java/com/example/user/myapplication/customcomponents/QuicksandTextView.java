package com.example.user.myapplication.customcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.user.myapplication.R;

/**
 * Created by User on 2017-06-22.
 */

public class QuicksandTextView extends AppCompatTextView {

    private Typeface tfBold;
    private Typeface tfLight;
    private Typeface tfMedium;
    private Typeface tfRegular;


    public QuicksandTextView(Context context) {
        super(context);
        initialiseFontTypeFace();
    }

    public QuicksandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialiseFontTypeFace();
        setFonts(context,attrs);
    }

    public QuicksandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialiseFontTypeFace();
        setFonts(context,attrs);
    }

    private void setFonts(Context context,AttributeSet attrs){
        TypedArray  array =getContext().obtainStyledAttributes(attrs, R.styleable.QuickSandTextView);
        int fontType = array.getInt(R.styleable.QuickSandTextView_font_style,1);
        setFontTypeFace(fontType);

        int fontSize = array.getInt(R.styleable.QuickSandTextView_font_size,10);
        setTextSize(fontSize);
    }

    private void initialiseFontTypeFace(){

        tfBold = Typeface.createFromAsset(getContext().getAssets(),"font/Quicksand-Bold.ttf");
        tfLight = Typeface.createFromAsset(getContext().getAssets(),"font/Quicksand-Light.ttf");
        tfMedium = Typeface.createFromAsset(getContext().getAssets(),"font/Quicksand-Medium.ttf");
        tfRegular = Typeface.createFromAsset(getContext().getAssets(),"font/Quicksand-Regular.ttf");
    }

    private void setFontTypeFace(int fontType){
        switch (fontType){
            case 1: setTypeface(tfBold);
                break;
            case 2: setTypeface(tfMedium);
                break;
            case 3: setTypeface(tfLight);
                break;
            case 4: setTypeface(tfRegular);
                break;
        }
    }

}
