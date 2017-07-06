package com.example.user.myapplication.customcomponents;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.myapplication.R;


import java.util.ArrayList;

/**
 * Created by User on 2017-06-22.
 */

public class Rater extends LinearLayout implements View.OnClickListener {

    private RaterEventListener listner;

   private final  int  MAX_RATINGS=5;
   private ArrayList<ImageView> arrayStars = new ArrayList<ImageView>();

    public Rater(Context context) {
        super(context);
        setLayoutAndComponents();
    }

    public Rater(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutAndComponents();
    }

    public Rater(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutAndComponents();
    }


    @RequiresApi(api= Build.VERSION_CODES.LOLLIPOP)
    public Rater(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLayoutAndComponents();
    }


    public void setListner(RaterEventListener listner){
        this.listner=listner;
    }

    private void setLayoutAndComponents(){
        setOrientation(HORIZONTAL);

        for (int i=0;i<MAX_RATINGS; i++){
            ImageView imageView= new ImageView(getContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.star);
            imageView.setOnClickListener(this);
            imageView.setId(i);
            imageView.setTag("false");
            arrayStars.add(imageView);
            addView(imageView);
        }

    }

    int currentRating =0;

    public int getCurrentRating(){
        return currentRating;
    }


    @Override
    public void onClick(View v) {
        int oldRating;
        try{
            int index = (Integer) v.getId();
            oldRating=currentRating;
            currentRating = index+1;

            if (arrayStars.get(index).getTag().equals("true")){
                for(int i=index+1;i<arrayStars.size();i++){
                    ImageView image= arrayStars.get(i);
                    image.setImageResource(R.drawable.star);
                    image.setTag("false");

                }
                //listner.unchecked(index+1);
            }else{

                for (int i=0;i<=index;i++){
                    ImageView image= arrayStars.get(i);
                    image.setImageResource(R.drawable.star_selected);
                    image.setTag("true");

                }
                //listner.checked(index+1);
            }
            listner.onRatingChanged(currentRating, oldRating,this);

        }catch(Exception ex){

        }
    }


    public interface RaterEventListener {

        public void onRatingChanged(int currentRating,int oldRating,Rater rater);
    }
}
