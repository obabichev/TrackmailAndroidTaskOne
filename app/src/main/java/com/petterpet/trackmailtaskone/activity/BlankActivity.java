package com.petterpet.trackmailtaskone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.petterpet.trackmailtaskone.asynctask.BlankAsyncTask;
import com.petterpet.trackmailtaskone.R;

public class BlankActivity extends AppCompatActivity {

    private BlankAsyncTask blankAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
    }

    @Override
    protected void onResume() {
        super.onResume();
        blankAsyncTask = new BlankAsyncTask(this);
        blankAsyncTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        blankAsyncTask.cancel(true);
    }
}
