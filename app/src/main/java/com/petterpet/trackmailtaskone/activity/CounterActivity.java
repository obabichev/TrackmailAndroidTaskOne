package com.petterpet.trackmailtaskone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.petterpet.trackmailtaskone.R;
import com.petterpet.trackmailtaskone.asynctask.CounterAsyncTask;

import static com.petterpet.trackmailtaskone.utils.NumbersUtil.convertNumberToWords;

/**
 * Created by obabichev on 11/10/16.
 */

public class CounterActivity extends AppCompatActivity {

    private final String CURRENT_VALUE_KEY = "CURRENT_VALUE_KEY";
    private final String CONTINUE_COUNTING_KEY = "CONTINUE_COUNTING_KEY";

    private int currentValue = 0;

    private CounterAsyncTask counterAsyncTask;
    private boolean continueCounting = false;

    private TextView currentValueTextView;
    private Button startButton;
    private Button stopButton;

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


        startButton = (Button) findViewById(R.id.start_button);
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCounting();
            }
        });

        stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState(false);
                stopCounting();
                continueCounting = false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentValueTextView.setText(convertNumberToWords(currentValue));
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
        startButton.setVisibility(isCounting ? View.GONE : View.VISIBLE);
        stopButton.setVisibility(isCounting ? View.VISIBLE : View.GONE);
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
