package com.petterpet.trackmailtaskone.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.petterpet.trackmailtaskone.activity.CounterActivity;

import static com.petterpet.trackmailtaskone.utils.NumbersUtil.convertNumberToWords;
import static com.petterpet.trackmailtaskone.utils.ThreadsUtil.sleep;

/**
 * Created by obabichev on 11/10/16.
 */

public class CounterAsyncTask extends AsyncTask<Void, Void, Void> {

    private final String TAG = CounterAsyncTask.class.getSimpleName();

    private final long PERIOD = 1000L;

    private int currentValue;
    private CounterActivity activity;

    public CounterAsyncTask(int currentValue, CounterActivity activity) {
        this.currentValue = currentValue;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d(TAG, "doInBackgr:" + currentValue);
        while (currentValue <= 1000) {
            publishProgress();
            sleep(PERIOD);
            currentValue++;
            if (isCancelled()) {
                return null;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        activity.setText(convertNumberToWords(currentValue));
    }

    public int getCurrentValue() {
        return currentValue;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d(TAG, "onCancelled");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute");
        activity.resetCounting();
    }
}
