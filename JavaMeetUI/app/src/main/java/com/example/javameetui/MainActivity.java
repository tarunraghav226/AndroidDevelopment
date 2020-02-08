package com.example.javameetui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int value=0;

    private Button btnAdd;
    private Button btnTake;
    private Button btnGrow;
    private Button btnShrink;
    private Button btnHide;
    private Button btnReset;
    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnTake = (Button) findViewById(R.id.btntake);
        btnGrow = (Button) findViewById(R.id.btnGrow);
        btnShrink = (Button) findViewById(R.id.btnShrink);
        btnHide = (Button) findViewById(R.id.btnHide);
        btnReset = (Button) findViewById(R.id.btnReset);
        txtValue = (TextView) findViewById(R.id.txtValue);

        btnAdd.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnGrow.setOnClickListener(this);
        btnShrink.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {


    }
}
