package com.hell.firerecycle;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hell.firerecycle.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Model>list;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private FirebaseDatabase db= FirebaseDatabase.getInstance();
    final DatabaseReference root= FirebaseDatabase.getInstance().getReference().child("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
adapter= new MyAdapter(this,list);
recyclerView.setAdapter(adapter);

root.addValueEventListener(new ValueEventListener() {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {


        for(DataSnapshot dataSnapshot: snapshot.getChildren()){

            Model model= dataSnapshot.getValue(Model.class);
            list.clear();
            list.add(model);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});



    }
}
