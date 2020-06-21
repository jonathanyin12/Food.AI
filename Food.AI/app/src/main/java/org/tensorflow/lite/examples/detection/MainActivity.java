package org.tensorflow.lite.examples.detection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class MainActivity extends Activity {
    public static MainActivity mainActivity;
    private static final String calorieInfoFile = "calorie_info.txt";
    private static HashMap<String, Integer> calorieInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        try {
            calorieInfo = loadCalorieInfo(calorieInfoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetectorActivity.class));
            }
        });
    }

    public static MainActivity getInstance() {
        return mainActivity;
    }

    public void addFood(String food) {
        int calories = getCalorie(food);
        TextView total = (TextView) findViewById(R.id.total);
        TextView progressBar_total = (TextView) findViewById(R.id.progressBar_total);
        int cur = Integer.parseInt(total.getText().toString());
        cur += calories;
        total.setText(cur + "");
        progressBar_total.setText("Current calories: " + cur + "");
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setProgress(cur);
        TableLayout t1 = (TableLayout) findViewById(R.id.tablelayout);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        TextView date = new TextView(this);
        TextView foodItem = new TextView(this);
        TextView cals = new TextView(this);

        long tsLong = (long) (System.currentTimeMillis() / 1000);
        java.util.Date d = new java.util.Date(tsLong * 1000L);
        String ts = new SimpleDateFormat("h:mm a").format(d);
        date.setText(ts);
        foodItem.setText(food);
        cals.setText(calories + "");
        date.setGravity(Gravity.CENTER);
        foodItem.setGravity(Gravity.CENTER);
        cals.setGravity(Gravity.CENTER);

        date.setLayoutParams(new TableRow.LayoutParams(0));
        foodItem.setLayoutParams(new TableRow.LayoutParams(1));
        cals.setLayoutParams(new TableRow.LayoutParams(2));
        date.getLayoutParams().width = 0;
        foodItem.getLayoutParams().width = 0;
        cals.getLayoutParams().width = 0;
        tr.addView(date);
        tr.addView(foodItem);
        tr.addView(cals);
        t1.addView(tr);
    }

    public int getCalorie(String food) {
        return calorieInfo.get(food);
    }

    private HashMap loadCalorieInfo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
        HashMap<String, Integer> calCounts = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {
            calCounts.put(line.split(": ")[0], Integer.parseInt(line.split(": ")[1]));
        }
        return calCounts;
    }
}