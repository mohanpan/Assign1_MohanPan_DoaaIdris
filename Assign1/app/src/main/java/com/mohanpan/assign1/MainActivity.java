package com.mohanpan.assign1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.mariuszgromada.math.mxparser.*;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    Switch swtch;

    TextView hist;
    ArrayList<String> history = new ArrayList<>();

    ConstraintLayout bg;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        hist = findViewById(R.id.historyTxt);

        bg = findViewById(R.id.background);

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
                    bg.setBackgroundColor(Color.GRAY);

                    for (int i = 0; i <= 5; i++){
                        display.setTextColor(Color.WHITE);
                        swtch.setTextColor(Color.WHITE);
                        swtch.setText("Dark Mode");
                        hist.setTextColor(Color.WHITE);
                    }

                }else {
                    buttonView.setBackgroundColor(Color.WHITE);
                    bg.setBackgroundColor(Color.WHITE);
                    for (int i = 0; i <= 5; i++) {
                        display.setTextColor(Color.BLACK);
                        swtch.setTextColor(Color.BLACK);
                        swtch.setText("Light Mode");
                        hist.setTextColor(Color.GRAY);
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
        int textLength = display.getText().length();
        int cursorPos = display.getSelectionStart();
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

        String last = getLastDigit(display.getText().toString().replace("(", "").replace(")", ""));
        //System.out.println(last);


        if (display.getText().toString().endsWith(last)){
            display.setText(display.getText().toString().replace(last, "(-" + last + ")"));
        }
        
        else if (display.getText().toString().endsWith("-"+ last +")")){
            display.setText(display.getText().toString().replace("(-" , "" ).replace(")", ""));
        }


        display.setSelection(display.getText().length());

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
        String signal = display.getText().toString();

        //so that program can recognize and calculate the equation that user entered.
        signal = signal.replaceAll("÷", "/");
        signal = signal.replaceAll("×", "*");

        Expression exp = new Expression(signal);
        String answer = String.valueOf(exp.calculate());

        history.add(display.getText().toString() + "=" + answer);

        display.setText(answer);
        //set cursorPos
        display.setSelection(answer.length());


        hist.setText("History: \n" + Arrays.toString(history.toArray()).replace("[", "").replace
                ("]", "").replace(", ", "\n"));

        if(history.size() > 4){
            history.remove(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("calcText", display.toString());
        outState.putStringArrayList("historyText", history);

        int cursorPos = display.getSelectionStart();
        outState.putInt("cursorPosition", cursorPos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        history = savedInstanceState.getStringArrayList("historyText");
        hist.setText("History: \n" + Arrays.toString(history.toArray()).replace("[", "").replace
                ("]", "").replace(", ", "\n"));

        int cursorPos = savedInstanceState.getInt("cursorPosition");
        display.setSelection(cursorPos);

    }

    public String getLastDigit(String expression) {
        StringBuffer result = new StringBuffer();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);

            if (c == '=') {
                break;
            }

            if (c == '+') {
                break;
            }

            if (c == '-') {
                break;
            }

            if (c == '÷') {
                break;
            }

            if (c == '×') {
                break;
            }

            if (c == '(') {
                result.append(c);
            }

            if (c == ')') {
                result.append(c);
            }


            else {
                result.append(c);
            }
        }

        return result.reverse()
                .toString()
                .trim();
    }
}