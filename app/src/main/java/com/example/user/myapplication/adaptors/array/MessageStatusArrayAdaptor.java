package com.example.user.myapplication.adaptors.array;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.user.myapplication.R;
import com.example.user.myapplication.data.MessageStatus;

import java.util.List;

/**
 * Created by User on 2017-06-19.
 */

public class MessageStatusArrayAdaptor extends ArrayAdapter<com.example.user.myapplication.data.MessageStatus> {

    Context context;
    List<MessageStatus> lstMessages;

    LayoutInflater layoutInflater;

    TextView tvTitle;
    ToggleButton tbStatus;

    boolean isRead;
    boolean isUnread;

  public MessageStatusArrayAdaptor(Context context,List<MessageStatus> lstMessages){
        super(context, R.layout.layout_user_interface_message_item,lstMessages);

      this.context=context;
      this.lstMessages=lstMessages;

      layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       MessageStatus status= lstMessages.get(position);


        if (convertView ==null){
            convertView = layoutInflater.inflate(R.layout.layout_user_interface_message_item,parent,false);
        }
        tvTitle = (TextView) convertView.findViewById(R.id.tv_message_title);
        tbStatus =(ToggleButton) convertView.findViewById(R.id.tb_status);

        tvTitle.setText(status.getTitle());
        tbStatus.setChecked(status.isRead());

        return convertView;
    }


}
