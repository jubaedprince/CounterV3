package com.example.administrator.counterv3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    TextView outputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputView = (TextView) findViewById(R.id.outputView);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String v= sharedPreferences.getString("value", "0");
        //output is previous value or 0
        outputView.setText(v);


    }

    public void pressMeButtonPressed(View view){
        //show dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Counter");
        builder.setMessage("What do you want to do?");
        builder.setPositiveButton("Increment", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String v = Integer.parseInt(outputView.getText().toString()) + 1 + "";
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("value", v);
                editor.commit();
                outputView.setText(v);
            }
        });

        builder.setNegativeButton("Decrement", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int t =  Integer.parseInt(outputView.getText().toString()) - 1;
                if(t<0){
                    Toast.makeText(MainActivity.this, "Can't be decreased, already the minimum", Toast.LENGTH_LONG).show();
                }else{
                    String v = t + "";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("value", v);
                    editor.commit();
                    outputView.setText(v);
                }
            }
        });

        builder.create().show();
    }
}
