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

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.MyViewHolder> {
    private Context mContext;
    private List<Leave_raw> leaveList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, message,leaveType,submissionDate,startDate,endDate,status,link;
        public Button leaveButton;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleLeave);
            leaveType = view.findViewById(R.id.leaveTypeLeave);
            submissionDate = view.findViewById(R.id.submissionDateLeave);
            message = view.findViewById(R.id.messageLeave);
            startDate = view.findViewById(R.id.startDateLeave);
            endDate = view.findViewById(R.id.endDateLeave);
            leaveButton = view.findViewById(R.id.leaveButton);
            status = view.findViewById(R.id.statusLeave);
            link = view.findViewById(R.id.downloadLeave);
        }
    }

    public LeaveAdapter(Context mContext, List<Leave_raw> leaveList) {
        this.mContext = mContext;
        this.leaveList = leaveList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.leave_raw, null);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        final Leave_raw leave = leaveList.get(i);

        holder.title.setText(leave.getTitle());
        holder.message.setText(holder.message.getText() + leave.getMessage());
        holder.leaveType.setText(holder.leaveType.getText() + leave.getLeaveType());
        holder.submissionDate.setText(holder.submissionDate.getText() + leave.getSubmissionDate());
        holder.startDate.setText(holder.startDate.getText() + leave.getStartDate());
        holder.endDate.setText(holder.endDate.getText() + leave.getEndDate());
        holder.status.setText(leave.getStatus());

        if(leave.getStatus().equals("Reject")){
            holder.leaveButton.setEnabled(false);
            holder.status.setBackgroundColor(Color.rgb(220,20,60));
        }else if(leave.getStatus().equals("Approve")){
            holder.leaveButton.setEnabled(false);
            holder.status.setBackgroundColor(Color.rgb(0,204,0));
        }else {
            holder.status.setBackgroundColor(Color.rgb(69,165,237));
        }

        holder.leaveButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                holder.leaveButton.setEnabled(false);
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... voids) {
                        String task_url = "https://managioportal.000webhostapp.com/managio/android/deleteleave.php";

                        try {
                            URL url = new URL(task_url);
                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();

                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                            String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(leaveList.get(holder.getAdapterPosition()).leaveID);

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
                        if(result.equals("success")){
                            leaveList.remove(holder.getAdapterPosition());
                            update();
                        }else {
                            holder.leaveButton.setEnabled(true);
                            ShowAlert("Can't update leave status,Try again later");
                        }
                    }
                }.execute();
            }
        });

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Downloading Attachment"+ holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(leave.getLink())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return leaveList.size();
    }

    public void update(){
        notifyDataSetChanged();
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
}
