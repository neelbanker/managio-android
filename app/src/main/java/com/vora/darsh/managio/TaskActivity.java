package com.vora.darsh.managio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Task_raw> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        String tasks_raw = getIntent().getStringExtra("tasks");
        String[] tasks = tasks_raw.split(";;");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_task);

        taskList = new ArrayList<>();

        for (int i = 0;i <tasks.length;i++){
            String[] task = tasks[i].split(";");

            String status = task[0];

            String title = task[1];

            String details = task[2];

            String startDate = task[3];

            String endDate = task[4];

            String link = task[5];

            String taskId = task[6];

            taskList.add(new Task_raw(title,details,startDate,endDate,link,status,taskId));

        }

        taskAdapter = new TaskAdapter(this, taskList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }
}
