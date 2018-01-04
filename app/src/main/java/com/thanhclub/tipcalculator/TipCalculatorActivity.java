package com.thanhclub.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener {
    private EditText mEdtBillAmount;
    private Button mBtnPercentUp;
    private Button mBtnPercentDown;
    private TextView mTxtPercent;
    private TextView mTxtTip;
    private TextView mTxtTotal;
    private int mPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        initView();
        initAction();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_percent_up:
                mPercent += 1;
                calculateAndDisplay();
                break;
            case R.id.btn_percent_down:
                mPercent -= 1;
                calculateAndDisplay();
                break;
        }
    }

    private void initView() {
        mEdtBillAmount = (EditText) findViewById(R.id.edt_bill_amount);
        mBtnPercentUp = (Button) findViewById(R.id.btn_percent_up);
        mBtnPercentDown = (Button) findViewById(R.id.btn_percent_down);
        mTxtPercent = (TextView) findViewById(R.id.txt_percent);
        mTxtTip = (TextView) findViewById(R.id.txt_tip);
        mTxtTotal = (TextView) findViewById(R.id.txt_total);
    }

    private void initAction() {
        mPercent = 5;
        mEdtBillAmount.setOnEditorActionListener(this);
        mBtnPercentUp.setOnClickListener(this);
        mBtnPercentDown.setOnClickListener(this);
    }

    private void calculateAndDisplay() {
        if (mEdtBillAmount.getText().toString().equals("")) return;
        float billAmount = Float.parseFloat(mEdtBillAmount.getText().toString());
        float tip = mPercent * billAmount / 100;
        float total = billAmount - tip;
        mTxtPercent.setText(mPercent + "%");
        mTxtTip.setText("$" + tip);
        mTxtTotal.setText("$" + total);
    }
}
