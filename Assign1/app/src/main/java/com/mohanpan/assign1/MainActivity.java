package com.mohanpan.assign1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    Switch swtch;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.Display).equals(display.getText().toString())){
                    display.setText("");
                    display.setTextColor(Color.BLACK);
                }
                display.setTextColor(Color.BLACK);
            }
        });

        swtch = findViewById(R.id.DarkMode);

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    buttonView.setBackgroundColor(Color.GRAY);

                    for (int i = 0; i <= 5; i++){
                        //accessing each element of array
                        display.setTextColor(Color.WHITE);
                    }

                }else {
                    buttonView.setBackgroundColor(Color.WHITE);
                    for (int i = 0; i <= 5; i++) {
                        display.setTextColor(Color.BLACK);
                    }
                }
            }
        });
    }

    //Split string we made and do function as we go
    private void updateInput(String newStrInput) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.Display).equals(display.getText().toString())){
            display.setText(newStrInput);

            display.setSelection(cursorPos + 1);

        } else {
            display.setText(String.format("%s%s%s", leftStr, newStrInput, rightStr));

            display.setSelection(cursorPos + 1);
        }

    }

    public void oneBtn (View view) {
        updateInput("1");
    }

    public void twoBtn (View view) {
        updateInput("2");
    }

    public void threeBtn (View view) {
        updateInput("3");
    }

    public void fourBtn (View view) {
        updateInput("4");
    }

    public void fiveBtn (View view) {
        updateInput("5");
    }

    public void sixBtn (View view) {
        updateInput("6");
    }

    public void sevenBtn (View view) {
        updateInput("7");
    }

    public void eightBtn (View view) {
        updateInput("8");
    }

    public void nineBtn (View view) {
        updateInput("9");
    }

    public void zeroBtn (View view) {
        updateInput("0");
    }




    public void ACBtn (View view) {
        display.setText("");
    }

    public void DELBtn (View view) {
        int cursorPos = display.getSelectionStart();
        int textLength = display.getText().length();

        if (cursorPos != 0 && textLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void bracketsBtn (View view) {
        int cursorPos = display.getSelectionStart();
        int openBrackets = 0;
        int closeBrackets = 0;
        int textLength = display.getText().length();

        //check the whole string if there is a bracket
        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openBrackets += 1;
            } else if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closeBrackets += 1;
            }
        }

        if (openBrackets == closeBrackets || display.getText().toString().substring(textLength - 1, textLength).equals("(")){
            updateInput("(");

        } else if (closeBrackets < openBrackets && !display.getText().toString().substring(textLength - 1, textLength).equals("(")){
            updateInput(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void pointBtn (View view) {
        updateInput(".");
    }

    public void plusMinusBtn (View view) {
        updateInput("−");
    }



    public void divideBtn (View view) {
        updateInput("÷");
    }

    public void timesBtn (View view) {
        updateInput("×");
    }

    public void plusBtn (View view) {
        updateInput("+");
    }

    public void minusBtn (View view) {
        updateInput("-");
    }

    public void equalBtn (View view) {

    }
}