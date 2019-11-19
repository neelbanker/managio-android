package com.vora.darsh.managio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    Runnable r;
    String methodName;

    public BackgroundTask(Context ctx, String methodName) {
        this.ctx = ctx;
        this.methodName = methodName;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {

                try {
                    Socket sock = new Socket();
                    sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                    sock.close();
                } catch (IOException e) {
                    return "iProblem";
                }

        if(methodName.equals("login"))
        {
            String login_url = "https://managioportal.000webhostapp.com/managio/android/user.php";
            String login_name = params[1];
            String login_pass = params[2];

            try {
                URL url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("uPassword","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line;

                while ((line = bufferedReader.readLine())!= null)
                {
                    response+= line;
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
        }else if(methodName.equals("qrscan")){
            String login_url = "https://managioportal.000webhostapp.com/managio/android/attendance.php";

            try {
                URL url = new URL(login_url);
                String[] param = params[0].split(",");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("firstName","UTF-8")+"="+URLEncoder.encode(ctx.getSharedPreferences("login_data",Context.MODE_PRIVATE).getString("NAME",null),"UTF-8")+"&"+
                        URLEncoder.encode("subject_name","UTF-8")+"="+URLEncoder.encode(param[0],"UTF-8")+"&"+
                        URLEncoder.encode("faculty_name","UTF-8")+"="+URLEncoder.encode(param[1],"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line;

                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
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
        }else if(methodName.equals("task")){
            String task_url = "https://managioportal.000webhostapp.com/managio/android/task.php";

            try {
                URL url = new URL(task_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(ctx.getSharedPreferences("login_data",Context.MODE_PRIVATE).getString("ID",null),"UTF-8");

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
        }else if(methodName.equals("event")){
            String task_url = "https://managioportal.000webhostapp.com/managio/android/event.php";

            try {
                URL url = new URL(task_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

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
        }else if(methodName.equals("leave")){
            String task_url = "https://managioportal.000webhostapp.com/managio/android/leave.php";

            try {
                URL url = new URL(task_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(ctx.getSharedPreferences("login_data",Context.MODE_PRIVATE).getString("ID",null),"UTF-8");

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
        }else if(methodName.equals("nav_header_image")){
            String task_url = "https://managioportal.000webhostapp.com/managio/android/profile.php";

            try {
                URL url = new URL(task_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(ctx.getSharedPreferences("login_data",Context.MODE_PRIVATE).getString("ID",null),"UTF-8");

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
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("iProblem")){
            Toast.makeText(ctx,"Can't connect to internet,Try again",Toast.LENGTH_LONG).show();
        }
        if(methodName.equals("login")){
            if(!result.equals(""))
            {
                MainActivity.isLoggedIn = true;
                Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ctx,MainActivity.class);
                String[] data = result.split(",");
                SharedPreferences preferences = ctx.getSharedPreferences("login_data",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("ID",data[0]);
                editor.putString("EMAIL",data[1]);
                editor.putString("NAME",data[2]);
                editor.apply();

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }else {
                ShowAlert("Invalid username or password");
            }
        }else if(methodName.equals("qrscan")){
            if(result.equals("success")){
                Toast.makeText(ctx,"Attendance successful",Toast.LENGTH_LONG).show();
            }else {
                ShowAlert("Can't add attendance,try again");
            }
        }else if(methodName.equals("task")){
            if(result.equals("")){
                ShowAlert("No task assigned");
            }else {
                Intent intent = new Intent(ctx,TaskActivity.class);
                intent.putExtra("tasks",result);
                ctx.startActivity(intent);
            }
        }else if(methodName.equals("event")){
            if(result.equals("")){
                ShowAlert("No event available");
            }else {
                Intent intent = new Intent(ctx,EventActivity.class);
                intent.putExtra("events",result);
                ctx.startActivity(intent);
            }

        }else if(methodName.equals("leave")){
            if(result.equals("")){
                ShowAlert("No leaves generated");
            }
            else {
                Intent intent = new Intent(ctx,LeaveActivity.class);
                intent.putExtra("leaves",result);
                ctx.startActivity(intent);
            }
        }else if(methodName.equals("nav_header_image")){
            SharedPreferences preferences = ctx.getSharedPreferences("login_data",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("PROFILE",result);
            editor.apply();
        }
    }

    public void ShowAlert(String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
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