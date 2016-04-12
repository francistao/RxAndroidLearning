package com.geniusvjr.rxandroidexample;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExampleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_list);
        setupActionBar();
        setupExampleList();
    }

    private void setupExampleList() {
        RecyclerView exampleList = (RecyclerView) findViewById(R.id.example_list);
        exampleList.setHasFixedSize(true);
        exampleList.setLayoutManager(new LinearLayoutManager(this));
        exampleList.setAdapter(new ExampleAdapter(this, getExamples()));
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setTitle("Example List");
        }
    }

    private static List<ExampleActivityAndName> getExamples()
    {
        List<ExampleActivityAndName> exampleActivityAndNames = new ArrayList<>();
        exampleActivityAndNames.add(new ExampleActivityAndName(
                Example1Activity.class,
                "Example 1: Simple Color List"
        ));
        return exampleActivityAndNames;
    }
}
