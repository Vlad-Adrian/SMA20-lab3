package com.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.R;

public class MainIntentActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_main_intent);
    }

    public void mainClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonA:
                Uri web = Uri.parse("http://www.google.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, web);
                startActivity(webIntent);
                break;
            case R.id.buttonB:
                Uri phone = Uri.parse("tel:00401213456");
                Intent newPhone = new Intent(Intent.ACTION_VIEW, phone);
                startActivity(newPhone);
                break;
            case R.id.buttonC:
                Uri web2 = Uri.parse("https://www.google.com");
                Intent webIntent2 = new Intent("MSA.LAUNCH", web2);
                startActivity(webIntent2);
                break;
            case R.id.buttonD:
                Uri phone2 = Uri.parse("tel:00401213456");
                Intent phoneIntent = new Intent("MSA.LAUNCH", phone2);
                startActivity(phoneIntent);
                break;
        }
    }
}
