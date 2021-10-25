package com.example.cse226ca1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class P1RecyclerViewHomeBudgetMngmtSystem extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<P01Item> al;
    P01customadapter md;
    EditText etName,etReg;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_recycler_view_home_budget_mngmt_system);

        etName = (EditText) findViewById(R.id.etnameid);
        etReg = (EditText) findViewById(R.id.etregid);
        b = findViewById(R.id.btnid);
        rv = (RecyclerView) findViewById(R.id.rvid);
        al = new ArrayList<>();
        md = new P01customadapter(this,al);

        rv.setAdapter(md);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String reg = etReg.getText().toString();
                int image = R.drawable.homeicon;

                P01Item p = new P01Item(name,reg,image);
                al.add(p);
                etName.setText("");
                etReg.setText("");
                md.notifyDataSetChanged();
            }
        });
    }
}

