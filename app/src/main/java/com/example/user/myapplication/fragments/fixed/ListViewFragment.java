package com.example.user.myapplication.fragments.fixed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.myapplication.R;

/**
 * Created by User on 2017-06-16.
 */

public class ListViewFragment extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {


    View viewRoot;
    ListView lvMaster;
    String[] strValues =  new String[]{"Hello","How ","What"};
    FragmentClickable clickableActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewRoot=inflater.inflate(R.layout.layout_contact_list,container,false);
        lvMaster= (ListView) viewRoot.findViewById(R.id.lv_contacts);

        ArrayAdapter<String> adaptor= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strValues);
        lvMaster.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();

        clickableActivity = (FragmentClickable) getActivity();

        lvMaster.setOnItemClickListener(this);
        return viewRoot;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clickableActivity.selectedValue(strValues[position]);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        clickableActivity.selectedValue(strValues[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface FragmentClickable{
         void selectedValue(String str);
    }
}
