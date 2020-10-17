package lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.helloandroid.R;

public class ActivityC extends AppCompatActivity {
    private static final String TAG = "ActivityC";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_activity);
        setTitle("B");
        Log.d(TAG, "onCreate C");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart C");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume C");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart C");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause C");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop C");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy C");
    }

    public void clicked(View view) {
        switch (view.getId()) {
            case R.id.buttonA:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.buttonB:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.buttonC:
                startActivity(new Intent(this, ActivityC.class));
                break;
        }
    }
}
