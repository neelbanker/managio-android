package com.vora.darsh.managio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder>{

    private Context mContext;
    private List<Event_raw> eventList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, eventDate,location,createdBy;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleEvent);
            description = view.findViewById(R.id.descriptionEvent);
            eventDate = view.findViewById(R.id.eventDateEvent);
            location = view.findViewById(R.id.locationEvent);
            createdBy = view.findViewById(R.id.createdByEvent);
        }
    }

    public EventAdapter(Context mContext, List<Event_raw> eventList) {
        this.mContext = mContext;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.event_raw, null);

        return new EventAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final Event_raw event = eventList.get(i);

        holder.title.setText(event.title);
        holder.description.setText(event.details);
        holder.location.setText(holder.location.getText() +  event.location);
        holder.createdBy.setText(holder.createdBy.getText() + event.createdBy);
        holder.eventDate.setText(holder.eventDate.getText() +event.eventDate);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
