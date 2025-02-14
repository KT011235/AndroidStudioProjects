package com.example.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private int var1;
    private int var2;
    private String operator = "";
    private Button buttonAdd;
    private Button buttonSub;
    private TextView calcDisplay;

    private boolean clearData() {
        var1 = 0;
        var2 = 0;
        operator = "";
        calcDisplay.setText(String.valueOf(var1));
        return true;
    }

    private void updateNumber(int digit) {
        if (operator.isEmpty()) {
            if (var1 < 0) {
                var1 = var1 * 10 - digit;
            } else {
                var1 = var1 * 10 + digit;
            }
            calcDisplay.setText(String.valueOf(var1));
        }
        else {
            if (var2 < 0) {
                var2 = var2 * 10 - digit;
            } else {
                var2 = var2 * 10 + digit;
            }
            calcDisplay.setText(String.valueOf(var2));
        }
    }

    private void updateOperator(String op) {

        if (operator.isEmpty()) {
            operator = op;
        } else {
            calculateAnswer();
            operator = op;
        }
        switch (operator) {
            case "+":
                buttonAdd.setBackgroundColor(Color.YELLOW);
                buttonSub.setBackgroundColor(getColor(R.color.grey));
                break;

            case "-":
                buttonSub.setBackgroundColor(Color.YELLOW);
                buttonAdd.setBackgroundColor(getColor(R.color.grey));
                break;
        }
    }

    private void calculateAnswer() {

        buttonAdd.setBackgroundColor(getColor(R.color.grey));
        buttonSub.setBackgroundColor(getColor(R.color.grey));

        switch (operator) {
            case "+":
                operator = "";
                var1 = var1 + var2;
                calcDisplay.setText(String.valueOf(var1));
                break;
            case "-":
                operator = "";
                var1 = var1 - var2;
                calcDisplay.setText(String.valueOf(var1));
                break;
        }
        var2 = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        calcDisplay = findViewById(R.id.display);

        //button components: numbers
        Button buttonOne = findViewById(R.id.one);
        Button buttonTwo = findViewById(R.id.two);
        Button buttonThree = findViewById(R.id.three);
        Button buttonFour = findViewById(R.id.four);
        Button buttonFive = findViewById(R.id.five);
        Button buttonSix = findViewById(R.id.six);
        Button buttonSeven = findViewById(R.id.seven);
        Button buttonEight = findViewById(R.id.eight);
        Button buttonNine = findViewById(R.id.nine);
        Button buttonZero = findViewById((R.id.zero));

        //button components: operation
        buttonAdd = findViewById(R.id.plus);
        buttonSub = findViewById(R.id.minus);
        Button buttonEqual = findViewById(R.id.equal);

        buttonOne.setOnClickListener(v -> updateNumber(1 ));
        buttonTwo.setOnClickListener(v -> updateNumber(2 ));
        buttonThree.setOnClickListener(v -> updateNumber(3 ));
        buttonFour.setOnClickListener(v -> updateNumber(4 ));
        buttonFive.setOnClickListener(v -> updateNumber(5 ));
        buttonSix.setOnClickListener(v -> updateNumber(6 ));
        buttonSeven.setOnClickListener(v -> updateNumber(7 ));
        buttonEight.setOnClickListener(v -> updateNumber(8 ));
        buttonNine.setOnClickListener(v -> updateNumber(9 ));
        buttonZero.setOnClickListener(v -> updateNumber(0 ));

        buttonAdd.setOnClickListener(v -> updateOperator("+"));
        buttonSub.setOnClickListener(v -> updateOperator("-"));

        buttonEqual.setOnClickListener(v -> calculateAnswer());

        buttonEqual.setOnLongClickListener(v -> clearData());
    }

    }
