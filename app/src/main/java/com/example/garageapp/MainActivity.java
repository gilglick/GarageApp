package com.example.garageapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MainActivity extends AppCompatActivity {

    private TextView txt_title;
    private TextView txt_Last_Session;
    private TextView txt_total_time;
    private TextView txt_Garage_Name;
    private TextView txt_Garage_Address;
    private TextView txt_Cars;
    private TextView txt_Garage_open;
    private ConstraintLayout main_MainLay;
    private Repository repository;
    private Session currentSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        repository = new Repository(getApplication());
        setupViews();
        fetchData();
    }

    private void setupViews() {
        txt_title = findViewById(R.id.welcomePage_textView_title);
        txt_Last_Session = findViewById(R.id.TXT_Last_Session);
        txt_total_time = findViewById(R.id.TXT_total_time);
        txt_Garage_Name = findViewById(R.id.TXT_Garage_Name);
        txt_Garage_open = findViewById(R.id.TXT_Garage_open);
        txt_Garage_Address = findViewById(R.id.TXT_Garage_Address);
        txt_Cars = findViewById(R.id.TXT_Cars);
        main_MainLay = findViewById(R.id.main_layout);
    }
    public void fetchData() {
        RetrofitBuilder.getGarageService().loadGarage().enqueue(new Callback<Garage>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Garage> call, Response<Garage> response) {
                Garage garage = response.body();
                if (garage != null) {
                    runOnUiThread(() -> {
                        txt_Garage_Name.setText("Garage Name: " + garage.getName());
                        txt_Cars.setText(garage.stringifyArray());
                        txt_Garage_open.setText("Status: " +(garage.isOpen() ? "Open" : "Closed"));
                        txt_Garage_Address.setText("Address: " + garage.getAddress());
                    });
                }
            }

            @Override
            public void onFailure(Call<Garage> call, Throwable t) {}
        });
    }

    protected void setBackgroundColor(int color) {
        main_MainLay.setBackgroundColor(color);
    }

    protected void setAppName(String name) {
        txt_title.setText(name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentSession = new Session(System.currentTimeMillis());
        repository.getLastSession(session -> {
            final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.US);
            String msg = session == null ? "Your first session" :  format.format(session.getEndTime());
            runOnUiThread(() -> txt_Last_Session.setText(msg));
        });
        repository.getTotalTime(time ->
                runOnUiThread(() ->
                        txt_total_time.setText(Utility.parseTime(time.intValue()))));
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentSession.setEndTime(System.currentTimeMillis());
        currentSession.setTotalTime(currentSession.getEndTime() - currentSession.getStartTime());
        repository.insert(currentSession);
        currentSession = null;
    }

}