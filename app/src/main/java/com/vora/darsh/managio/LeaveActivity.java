package com.vora.darsh.managio;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaveActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Leave_raw> leaveList = null;
    private LeaveAdapter leaveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String tasks_raw = getIntent().getStringExtra("leaves");
        String[] leaves = tasks_raw.split(";;");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_leave);

        if(leaveList == null){
            leaveList = new ArrayList<>();

            for (int i = 0;i <leaves.length;i++){
                String[] leave = leaves[i].split(";");

                String status = leave[0];

                String title = leave[1];

                String message = leave[2];

                String leaveType = leave[3];

                String submissionDate = leave[4];

                String startDate = leave[5];

                String endDate = leave[6];

                String link = leave[7];

                String leaveID = leave[8];

                leaveList.add(new Leave_raw(title,message,leaveType,submissionDate,startDate,endDate,link,status,leaveID));

            }
        }


        leaveAdapter = new LeaveAdapter(this, leaveList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(leaveAdapter);
    }
}
