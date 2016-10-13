package com.petterpet.trackmailtaskone.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.petterpet.trackmailtaskone.activity.CounterActivity;
import com.petterpet.trackmailtaskone.utils.NumbersUtil;

import static com.petterpet.trackmailtaskone.utils.ThreadsUtil.sleep;

/**
 * Created by obabichev on 11/10/16.
 */

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Void> {

    private final String TAG = CounterAsyncTask.class.getSimpleName();

    private final long PERIOD = 1000L;

    private CounterActivity activity;

    public CounterAsyncTask(CounterActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        Log.d(TAG, "doInBackgr:");
        if (params.length == 0) {
            return null;
        }

        int currentValue = params[0];
        while (currentValue <= 1000) {
            publishProgress(currentValue);
            sleep(PERIOD);
            currentValue++;
            if (isCancelled()) {
                return null;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (values.length > 0) {
            activity.setText(new NumbersUtil(activity).convertNumberToWords(values[0]));
            activity.setCurrentValue(values[0]);
        }
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
