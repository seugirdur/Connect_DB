package com.example.androidsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import com.mysql.protocol.cj.Resultset;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Button bt_1;
    TextView tv_1, tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_1 = findViewById(R.id.bt_1);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }



    class Task extends AsyncTask<Void, Void, Void>{
        String records = "", error="";

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                //Class.forName("org.postgresql.Driver");
                Class.forName("com.mysql.jdbc.Driver");
                //Connection connection = DriverManager.getConnection("jdbc:postgresql:postgres://eanhrepj:aXOoO0LEXJPkQoexanTF-ZbUCxo1tMt_@kesavan.db.elephantsql.com/eanhrepj","eanhrepj","aXOoO0LEXJPkQoexanTF-ZbUCxo1tMt_");

                //Connection connection = DriverManager.getConnection("jdbc:postgresql:postgres://kesavan.db.elephantsql.com/eanhrepj","eanhrepj","aXOoO0LEXJPkQoexanTF-ZbUCxo1tMt_");

                //Connection connection = DriverManager.getConnection("jdbc:mysql://b074b10df9459b:bbfa6545@us-cdbr-east-06.cleardb.net/heroku_e1cccceed05ce1f?reconnect=true");
                //Connection connection = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_e1cccceed05ce1f","b074b10df9459b","bbfa6545");

                Connection connection = DriverManager.getConnection("");
                //Connection connection = DriverManager.getConnection("jdbc:mysql://172.105.106.22/test","pcdobot","%$FrAustria2013%$");



                //Connection connection = DriverManager.getConnection("jdbc:postgresql://tesaoeanhrepj:aXOoO0LEXJPkQoexanTF-ZbUCxo1tMt_@kesavan.db.elephantsql.com/eanhrepj","eanhrepj","aXOoO0LEXJPkQoexanTF-ZbUCxo1tMt_");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT FirstName FROM Persons");

                while(resultSet.next()) {
                    records += resultSet.getString(1);
                }

            } catch(Exception e) {
                error = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            tv_1.setText(records);
            if (error != "") {
                tv_2.setText(error);
            }

            super.onPostExecute(unused);
        }
    }

}