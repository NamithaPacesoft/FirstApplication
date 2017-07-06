package com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.user.myapplication.R;
import com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.singleton.VolleyImageHelper;

/**
 * Created by User on 2017-06-28.
 */


public class ImageRequestActivity extends AppCompatActivity {

    ImageLoader imageLoader;
    NetworkImageView image;
    EditText etUrl;
    Button btnShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_network_imageview_example);

        initializeMyComponents();
        addActionListener();
    }

    public void initializeMyComponents(){
        image = (NetworkImageView) findViewById(R.id.image_dispaly);
        etUrl = (EditText) findViewById(R.id.et_image_url);
        btnShow =(Button) findViewById(R.id.btn_image_display);
    }

    public void addActionListener(){
        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
    }

    public void downloadImage(){
        imageLoader = VolleyImageHelper.getInstance(this).getImageLoader();
        imageLoader.get(etUrl.getText().toString(),ImageLoader.getImageListener(image,R.drawable.star,R.drawable.ic_launcher));

        //imageLoader.get(etUrl.getText().toString(),this);
        image.setImageUrl(etUrl.getText().toString(),imageLoader);
    }

    /*
    @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            image.setImageBitmap(response.getBitmap());
        }

    @Override
    public void onErrorResponse(VolleyError error) {

        image.setImageResource(R.drawable.ic_launcher);
    }
    */
}
