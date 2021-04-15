package com.example.pra04;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pra04.DTO.ChatModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    SharedPreferences preferences;

    Context context;
    ArrayList<ChatModel> arrayList;

    public ChatAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(viewType ==1){
            view = LayoutInflater.from(context).inflate(R.layout.my_chat_row, parent,false);
            return new Holder(view);
        }
        else{
            view = LayoutInflater.from(context).inflate(R.layout.your_chat_row,parent,false);
            return new Holder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Holder){
            ((Holder) holder).chatText.setText(arrayList.get(position).getScript());
            ((Holder) holder).chatTime.setText(arrayList.get(position).getDate_time());
        }
        else if(holder instanceof  Holder2){
            ((Holder2) holder).chatYouImage.setImageResource(R.mipmap.ic_launcher);
            ((Holder2) holder).chatYouName.setText(arrayList.get(position).getName());
            ((Holder2) holder).chatText.setText(arrayList.get(position).getScript());
            ((Holder2) holder).chatTime.setText(arrayList.get(position).getDate_time());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(ChatModel item){
        if(arrayList != null){
            arrayList.add(item);
        }
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView chatText, chatTime;
        public Holder(@NonNull View itemView) {
            super(itemView);

            chatText = itemView.findViewById(R.id.chat_Text);
            chatTime = itemView.findViewById(R.id.chat_Time);
        }
    }

    public class Holder2 extends RecyclerView.ViewHolder{
        ImageView chatYouImage;
        TextView chatYouName;
        TextView chatText;
        TextView chatTime;

        public Holder2(@NonNull View itemView) {
            super(itemView);

            chatYouImage = itemView.findViewById(R.id.chat_You_Image);
            chatYouName = itemView.findViewById(R.id.chat_You_Name);
            chatText = itemView.findViewById(R.id.chat_Text);
            chatTime = itemView.findViewById(R.id.chat_Time);
        }
    }

    @Override
    public int getItemViewType(int position) {
        preferences = context.getSharedPreferences("USERSIGN", Context.MODE_PRIVATE);

        if (arrayList.get(position).getName() == preferences.getString("name","")){
            return 1;
        } else{
            return 2;
        }
    }
}
