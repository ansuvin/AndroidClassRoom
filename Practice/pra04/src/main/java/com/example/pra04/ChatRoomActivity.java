package com.example.pra04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pra04.DTO.ChatModel;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoomActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText editText;
    Button btnSend;

    boolean hasConnection = false;
    Thread thread2 = null;
    boolean startTyping = false;
    int time = 2;

    // Scoket
    Socket mSocket;
    String URL = "http://249c03cd4958.ngrok.io";

    {
        try {
            mSocket = IO.socket(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    ArrayList<ChatModel> arrayList = new ArrayList<ChatModel>();
    ChatAdapter mAdapter = new ChatAdapter(this, arrayList);

    RecyclerView chatRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE);

        btnSend = findViewById(R.id.btnSend);
        editText = findViewById(R.id.editText);

        chatRecyclerview = findViewById(R.id.recycler);
        chatRecyclerview.setAdapter(mAdapter);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        chatRecyclerview.setLayoutManager(lm);
        chatRecyclerview.setHasFixedSize(true);

        if(savedInstanceState != null){
            hasConnection = savedInstanceState.getBoolean("hasConnection");
        }

        if(hasConnection){

        }else{
            Log.d("TAG_AN",hasConnection+"hasco");

            mSocket.connect();

            // mSocket.on() 메시지 수신
            mSocket.on("connect user", onNewUser);
            mSocket.on("chat message", onNewMessage);

            JSONObject userId = new JSONObject();
            try {
                userId.put("username", preferences.getString("name","")+" Connected");
                userId.put("roomName","room_example");
                Log.d("TAG_AN", "getString(name, )"+preferences.getString("name","")+" Connected");

                // mSocket.emit() 메시지 전송
                mSocket.emit("connect user", userId);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        hasConnection = true;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    Emitter.Listener onNewUser = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
                Log.d("TAG_AN", "누군가 들어옴");
                int length = args.length;
                Log.d("TAG_AN", "누군가 들어옴: "+args[0].toString());

                if(length ==0){
                    return;
                }

                String username = args[0].toString();
                try {
                    JSONObject object = new JSONObject(username);
                    username = object.getString("username");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

    };

    Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String name;
                    String script;
                    String profile_image;
                    String date_time;

                    try{
                        Log.d("TAG_AN", "누군가 채팅침: "+data.getString("script"));

                        Log.d("TAG_AN", "data.toString(): "+data.toString());
                        name = data.getString("name");
                        script = data.getString("script");
                        profile_image = data.getString("profile_image");
                        date_time = data.getString("date_time");

                        ChatModel format = new ChatModel(name, script, profile_image, date_time);
                        mAdapter.addItem(format);
                        mAdapter.notifyDataSetChanged();
                        Log.d("TAG_AN","name: "+ name);
                    } catch (JSONException e) {
                        return;
                    }
                }
            });

        }
    };

    private void sendMessage() {
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String getTime = sdf.format(date);

        String message = editText.getText().toString().trim();
        if(TextUtils.isEmpty(message)){
            return;
        }
        //editText.setText("");
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", preferences.getString("name",""));
            jsonObject.put("script", message);
            jsonObject.put("profile_image", "example");
            jsonObject.put("date_time", getTime);
            jsonObject.put("roomName", "room_example");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ChatModel item = new ChatModel(preferences.getString("name",""), editText.getText().toString(),"exapmple",getTime);

        mAdapter.addItem(item);
        mAdapter.notifyDataSetChanged();

        Log.d("TAG_AN", "sendMessage:1 "+mSocket.emit("chat message", jsonObject));
        Log.d("TAG_An", "preferences.getString(\"name\",\"\")"+preferences.getString("name",""));

        editText.setText("");
    }
}