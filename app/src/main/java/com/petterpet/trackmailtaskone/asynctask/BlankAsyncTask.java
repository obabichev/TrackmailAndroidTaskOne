package com.petterpet.trackmailtaskone.asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.petterpet.trackmailtaskone.activity.CounterActivity;

import static com.petterpet.trackmailtaskone.utils.ThreadUtils.sleep;

/**
 * Created by obabichev on 11/10/16.
 */

public class BlankAsyncTask extends AsyncTask<Void, Void, Void> {

    private final String TAG = getClass().getSimpleName();

    private final long TIMEOUT = 2000L;

    private Context context;

    public BlankAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d(TAG, "doInBackground");
        Log.d(TAG, "Start waiting");
        sleep(TIMEOUT);
        Log.d(TAG, "Stop waiting");
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (!isCancelled()) {
            Log.d(TAG, "onPostExecute");
            Intent intent = new Intent(context, CounterActivity.class);
            context.startActivity(intent);
        }
    }
}
