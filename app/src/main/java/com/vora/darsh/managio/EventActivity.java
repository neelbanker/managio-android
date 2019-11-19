package com.vora.darsh.managio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Event_raw> eventList;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        String event_raw = getIntent().getStringExtra("events");
        String[] tasks = event_raw.split(";;");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_event);

        eventList = new ArrayList<>();

        for (int i = 0;i <tasks.length;i++){
            String[] task = tasks[i].split(";");

            String title = task[0];

            String details = task[1];

            String eventDate = task[3];

            String location = task[2];

            String createdBy = task[4];

            eventList.add(new Event_raw(title,details,eventDate,location,createdBy));

        }
//        eventList.add(new Task_raw("task1","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task2","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task3","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task4","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task5","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task6","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task7","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task8","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task9","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
//        eventList.add(new Task_raw("task10","wdwfwefwfwefwfwff","31/01/1998","31/02/1998"));
        eventAdapter = new EventAdapter(this, eventList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventAdapter);
    }
}
