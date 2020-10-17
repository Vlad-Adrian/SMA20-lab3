package com.helloandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText eName;
    Button bClick;
    TextView tName;
    Spinner spinner;
    Button bShare, bSearch;

    Map<Integer, Integer> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map.put(1, R.color.Red);
        map.put(0, R.color.buttonColor);
        map.put(3, R.color.Yellow);
        map.put(2, R.color.Green);
        map.put(4, R.color.Purple);

        eName = findViewById(R.id.eName);
        bClick = findViewById(R.id.bClick);
        tName = findViewById(R.id.tName);
        spinner = findViewById(R.id.colorSpinner);
        bSearch = findViewById(R.id.bSearch);
        bShare = findViewById(R.id.bShare);

        spinnerSupply(spinner);
        spinner.setOnItemSelectedListener(this);


        bClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Hello, " + eName.getText().toString();
                tName.setText(name);
                Log.d("Nume", name);

                openDialog(name);

            }
        });

    }

    public void search(View view) {
        String input = eName.getText().toString(), query;
        if (input.equals("")) {
            Toast.makeText(this, "Nothing to search", Toast.LENGTH_SHORT).show();
        } else {
            try {
                query = URLEncoder.encode(input, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                query = input;
            }
            Uri uri = Uri.parse("http://www.google.com/search?q=" + query);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    public void share(View view) {
        String input = eName.getText().toString();
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "Nothing to share", Toast.LENGTH_SHORT).show();
        } else {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, input);
            sendIntent.setType("text/plain");

            String title = "Share this with";
            Intent chooser = Intent.createChooser(sendIntent, title);

            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            } else {
                Toast.makeText(MainActivity.this, "No apps to share with", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clicked(View view) {
        String name = "Hello, " + eName.getText().toString();
        tName.setText(name);
    }

    private void openDialog(String greet) {
        Bundle bundle = new Bundle();
        bundle.putString("greet", greet);

        PopUpDialogFragment popUpDialogFragment = new PopUpDialogFragment();
        popUpDialogFragment.setArguments(bundle);
        popUpDialogFragment.show(getSupportFragmentManager(), "Dialog Hello");
    }

    private void spinnerSupply(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            bClick.setTextColor(ContextCompat.getColor(getApplicationContext(), map.get(position)));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}