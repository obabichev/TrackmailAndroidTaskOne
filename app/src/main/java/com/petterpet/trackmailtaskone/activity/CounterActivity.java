package com.petterpet.trackmailtaskone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.petterpet.trackmailtaskone.R;
import com.petterpet.trackmailtaskone.asynctask.CounterAsyncTask;
import com.petterpet.trackmailtaskone.utils.NumbersUtil;


/**
 * Created by obabichev on 11/10/16.
 */

public class CounterActivity extends AppCompatActivity {

    private final String CURRENT_VALUE_KEY = "CURRENT_VALUE_KEY";
    private final String CONTINUE_COUNTING_KEY = "CONTINUE_COUNTING_KEY";

    private int currentValue = 0;

    private CounterAsyncTask counterAsyncTask;
    private boolean continueCounting = false;
    private boolean isCounting;

    private TextView currentValueTextView;
    private Button startStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        restoreState(savedInstanceState);
        init();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentValue = savedInstanceState.getInt(CURRENT_VALUE_KEY);
            continueCounting = savedInstanceState.getBoolean(CONTINUE_COUNTING_KEY);
        }
    }

    private void init() {
        currentValueTextView = (TextView) findViewById(R.id.current_value_text_view);

        startStopButton = (Button) findViewById(R.id.start_stop_button);
        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCounting) {
                    stopCounting();
                    continueCounting = false;
                } else {
                    startCounting();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentValueTextView.setText(new NumbersUtil(this).convertNumberToWords(currentValue));
        if (continueCounting) {
            startCounting();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (counterAsyncTask != null && !counterAsyncTask.isCancelled()) {
            continueCounting = true;
        }
        stopCounting();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(CURRENT_VALUE_KEY, currentValue);
        outState.putBoolean(CONTINUE_COUNTING_KEY, continueCounting);
    }

    public void switchState(boolean isCounting) {
        this.isCounting = isCounting;
        startStopButton.setText(isCounting ? getString(R.string.stop_button) : getString(R.string.start_button));
    }

    public void setText(String text) {
        currentValueTextView.setText(text);
    }

    private void startCounting() {
        switchState(true);
        counterAsyncTask = new CounterAsyncTask(this);
        counterAsyncTask.execute(currentValue);
    }

    private void stopCounting() {
        if (counterAsyncTask != null && !counterAsyncTask.isCancelled()) {
            counterAsyncTask.cancel(false);
        }
        switchState(false);
    }

    public void resetCounting() {
        switchState(false);
        setText("");
        currentValue = 0;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
