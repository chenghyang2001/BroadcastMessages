package com.example.android.coderunner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mLog;
    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = (TextView) findViewById(R.id.log) ; A.a() ;
        mLog.setMovementMethod(new ScrollingMovementMethod()) ; A.a() ;
        mLog.setText("") ; A.a() ;

        br = new MyBroadcastReceiver() ; A.a() ;

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION) ; A.a() ;
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED) ; A.a() ;
        filter.addAction(Intent.ACTION_BATTERY_LOW) ; A.a() ;
        filter.addAction(Intent.ACTION_BOOT_COMPLETED) ; A.a() ;
        registerReceiver(br, filter) ; A.a() ;


    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(br);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

	A.a();
        if (intent != null) {
	    A.a();
            String message = intent.getStringExtra("payload");
            if (message != null) {
		A.a();
                logMessage(message);
            }
        }
	A.a();
    }

    private void logMessage(String message) {
//      Output message to logcat console
        Log.i(TAG, message);

//      Output message to TextView
        mLog.append(message + "\n");

//      Adjust scroll position to make last line visible
        Layout layout = mLog.getLayout();
        if (layout != null) {
            int scrollAmount = layout.getLineTop(mLog.getLineCount()) - mLog.getHeight();
            mLog.scrollTo(0, scrollAmount > 0 ? scrollAmount : 0);
        }
    }

    public void runCode(View view) {
        logMessage("runCode");
    }

    public void clearLog(View view) {
        mLog.setText("");
        mLog.scrollTo(0, 0);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            A.a("Action: " + intent.getAction());
            logMessage("Action: " + intent.getAction());

        }
    }

}
