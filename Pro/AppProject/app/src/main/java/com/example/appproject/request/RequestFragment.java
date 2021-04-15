package com.example.appproject.request;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.appproject.Laptop;
import com.example.appproject.R;
import com.example.appproject.RequestSong;
import com.example.appproject.Stay;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RequestFragment extends Fragment {

    ConstraintLayout btnLaptop, btnSong, btnStay;

    DatabaseReference LaptopRef, StayRef, myRef;
    FirebaseDatabase database;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String id = user.getEmail().substring(0,6);
    ArrayList<String> laptopList = new ArrayList<>();
    ArrayList<String> stayList = new ArrayList<>();
    ArrayList<String> laptopID = new ArrayList<>();
    ArrayList<String> stayID = new ArrayList<>();

    String studentID, studentName;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int date = calendar.get(Calendar.DATE);
    String dateID = ""+year+month+date;
    String week = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.WEEK_OF_YEAR);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_request, container, false);

        btnLaptop = root.findViewById(R.id.btn_laptop);
        btnSong = root.findViewById(R.id.btn_song);
        btnStay = root.findViewById(R.id.btn_stay);

        database= FirebaseDatabase.getInstance();
        LaptopRef = database.getReference("RequestLaptop").child(dateID);
        StayRef = database.getReference("RequestStay").child(week);
        myRef = database.getReference("student").child(id).child("studentData");

        LaptopRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Laptop laptop = snapshot.getValue(Laptop.class);
                if(laptop != null){
                    laptopList = laptop.getLaptopList();
                    laptopID = laptop.getLaptopID();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        StayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Stay data = snapshot.getValue(Stay.class);
                if(data != null){
                    stayList = data.getStayList();
                    stayID = data.getStayID();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentID = snapshot.child("ID").getValue(String.class);
                studentName = snapshot.child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(laptopList.size() <= 10) {
                    if (!laptopList.contains(studentName) && !laptopID.contains(studentID)) {
                        laptopList.add(studentName);
                        laptopID.add(studentID);
                        Laptop laptop = new Laptop(laptopList, laptopID);
                        LaptopRef.setValue(laptop);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "이미 신청하였습니다", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "자리가 부족합니다", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RequestSong.class);
                startActivity(intent);
            }
        });

        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!stayList.contains(studentName) && !stayID.contains(studentID)){
                    stayList.add(studentName);
                    stayID.add(studentID);
                    Stay stay = new Stay(stayList, stayID);
                    StayRef.setValue(stay);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "이미 잔류 신청을 하셨습니다",Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }
}