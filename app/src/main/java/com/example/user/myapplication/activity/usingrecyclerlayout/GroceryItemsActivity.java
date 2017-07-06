package com.example.user.myapplication.activity.usingrecyclerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adaptors.recycler.RecyclerViewAdaptor;
import com.example.user.myapplication.data.GroceryStoreItem;

import java.util.ArrayList;

/**
 * Created by User on 2017-06-16.
 */

public class GroceryItemsActivity extends AppCompatActivity {

    RecyclerView rvList;
    ArrayList<GroceryStoreItem> lstGroceryData=new ArrayList<GroceryStoreItem>();
    RecyclerViewAdaptor adaptor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recycler_view);

        initializeMyComponent();

    }

    private void initializeMyComponent(){
        rvList= (RecyclerView) findViewById(R.id.rv_List);
        addDataToGroceryList();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adaptor = new RecyclerViewAdaptor(lstGroceryData,this);
        rvList.setAdapter(adaptor);

    }

    private void addDataToGroceryList(){
        lstGroceryData.add(new GroceryStoreItem("Rice",(float)50,50,"Garden Vally sona masoori"));
        lstGroceryData.add(new GroceryStoreItem("Moog Dal",(float)17,30,"garden Vally"));
        lstGroceryData.add(new GroceryStoreItem("Masor Dal",(float)10,25,"garden Vally"));
        lstGroceryData.add(new GroceryStoreItem("Rice",(float)250,50,"India gate Basmathi"));
        lstGroceryData.add(new GroceryStoreItem("Water",(float)50,50,"masafi"));
        lstGroceryData.add(new GroceryStoreItem("Seeds",(float)3.5,50,"Bird food"));
        lstGroceryData.add(new GroceryStoreItem("Chocolates",(float)50,50,"Cadbury"));
        lstGroceryData.add(new GroceryStoreItem("Flowers",(float)3.5,10,"Daisies"));
        lstGroceryData.add(new GroceryStoreItem("Ice Cream",(float)50,18,"Galaxy cone"));
        lstGroceryData.add(new GroceryStoreItem("Ice Candy",(float)15,50,"Quality"));
        lstGroceryData.add(new GroceryStoreItem("Apples",(float)50,50,"ooty apples"));
        lstGroceryData.add(new GroceryStoreItem("Banana",(float)45.5,17,"Rasa Kadali"));
        lstGroceryData.add(new GroceryStoreItem("Garlic",(float)3.5,16,"Indian"));
        lstGroceryData.add(new GroceryStoreItem("Cheese",(float)17,57,"Gowda with garlic"));

    }
}
