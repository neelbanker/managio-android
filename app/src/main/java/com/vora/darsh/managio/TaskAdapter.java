package com.vora.darsh.managio;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context mContext;
    private List<Task_raw> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description,startDate,endDate,status,link;
        public Button taskButton;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleTask);

            description = view.findViewById(R.id.descriptionTask);
            startDate = view.findViewById(R.id.startDateTask);
            endDate = view.findViewById(R.id.endDateTask);
            taskButton = view.findViewById(R.id.taskButton);
            status = view.findViewById(R.id.statusTask);
            link = view.findViewById(R.id.downloadTask);
        }
    }

    public TaskAdapter(Context mContext, List<Task_raw> taskList) {
        this.mContext = mContext;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.task_raw, null);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder,int i) {
        final Task_raw task = taskList.get(i);

        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDetails());
        holder.startDate.setText(holder.startDate.getText() + task.getStartDate());
        holder.endDate.setText(holder.endDate.getText() + task.getEndDate());
        holder.status.setText(task.getStatus());

        if(task.getStatus().equals("Pending") || task.getStatus().equals("Stop")){
            holder.taskButton.setText("Start");
            holder.status.setBackgroundColor(Color.rgb(220,20,60));
        }else {
            holder.taskButton.setText("Stop");
            holder.status.setBackgroundColor(Color.rgb(0,204,0));
        }

        holder.taskButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... voids) {
                        String task_url = "https://managioportal.000webhostapp.com/managio/android/taskprogress.php";

                        try {
                            URL url = new URL(task_url);
                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();

                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                            String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(taskList.get(holder.getAdapterPosition()).taskId)+"&"+
                                    URLEncoder.encode("status","UTF-8")+"="+URLEncoder.encode(taskList.get(holder.getAdapterPosition()).status,"UTF-8");

                            bufferedWriter.write(data);

                            bufferedWriter.flush();
                            bufferedWriter.close();

                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                            String response = "";
                            String line;

                            while ((line = bufferedReader.readLine())!=null)
                            {
                                response += line;
                            }

                            bufferedReader.close();
                            inputStream.close();
                            httpURLConnection.disconnect();
                            return response;
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        holder.taskButton.setEnabled(true);

                        if(result == null || result.equals("") || result.equals("error")){
                            ShowAlert("Can't update status,Try again later");

                        }else {
                            taskList.get(holder.getAdapterPosition()).status = result;
                            update();
                        }
                    }
                }.execute();
            }
        });

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Downloading Attachment"+ holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(task.getLink())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void ShowAlert(String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void update(){
        notifyDataSetChanged();
    }

}
