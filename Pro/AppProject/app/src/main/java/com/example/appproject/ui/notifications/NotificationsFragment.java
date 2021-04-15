package com.example.appproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appproject.ItemNoti;
import com.example.appproject.Notification;
import com.example.appproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    //region 변수 선언
    ListView listView;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();

    ArrayList<Notification> dataList = new ArrayList<>();

    ItemNoti notification;

    DatabaseReference myRef;
    FirebaseDatabase database;
    //endregion

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //region 공지사항 데이터베이스 연결
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notification");
        listView = root.findViewById(R.id.listView);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                notification = snapshot.getValue(ItemNoti.class);
                if(notification != null) {
                    title = notification.getTitleStr();
                    contents = notification.getContentStr();
                    for (int i = 0 ; i < title.size(); i++) {
                        dataList.add(new Notification(title.get(i), contents.get(i)));
                    }
                }
                makeAdapter(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion

        return root;
    }

    //region 어댑터 연결
    public void makeAdapter(ArrayList<Notification> dataList){

        if(dataList.size() == 0){
            dataList.add(new Notification("공지가 아직 없습니다", "공지가 아직 없습니다"));
        }
        NotiAdapter adapter = new NotiAdapter(getActivity().getApplicationContext(), R.layout.row_notifications, dataList);

        listView.setAdapter(adapter);
    }
    //endregion

}