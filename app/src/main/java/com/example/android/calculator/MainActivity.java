package com.example.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

import org.w3c.dom.Text;

import java.util.zip.DeflaterOutputStream;

import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener {
    double num = 0;
    double insideNum = 0;
    double product = 1;
    double insideProduct = 0;
    int location = 0;

    ArrayList<Double> numbers = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<String>();
    ArrayList<Double> insideNumbers = new ArrayList<>();
    ArrayList<String> insideOperators = new ArrayList<>();
    ArrayList<String> beforeParenthesisOperators = new ArrayList<>();

    boolean hasDot = false;
    boolean inParenthesis = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = (Button) findViewById(R.id.oneButton);
        one.setOnClickListener(this);

        Button two = (Button) findViewById(R.id.twoButton);
        two.setOnClickListener(this);

        Button three = (Button) findViewById(R.id.threeButton);
        three.setOnClickListener(this);

        Button four = (Button) findViewById(R.id.fourButton);
        four.setOnClickListener(this);

        Button five = (Button) findViewById(R.id.fiveButton);
        five.setOnClickListener(this);

        Button six = (Button) findViewById(R.id.sixButton);
        six.setOnClickListener(this);

        Button seven = (Button) findViewById(R.id.sevenButton);
        seven.setOnClickListener(this);

        Button eight = (Button) findViewById(R.id.eightButton);
        eight.setOnClickListener(this);

        Button nine = (Button) findViewById(R.id.nineButton);
        nine.setOnClickListener(this);

        Button zero = (Button) findViewById(R.id.zeroButton);
        zero.setOnClickListener(this);

        Button plus = (Button) findViewById(R.id.plusButton);
        plus.setOnClickListener(this);

        Button minus = (Button) findViewById(R.id.minusButton);
        minus.setOnClickListener(this);

        Button time = (Button) findViewById(R.id.timeButton);
        time.setOnClickListener(this);

        Button divide = (Button) findViewById(R.id.divideButton);
        divide.setOnClickListener(this);

        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);

        Button equal = (Button) findViewById(R.id.equalButton);
        equal.setOnClickListener(this);

        Button hasNegative = (Button) findViewById(R.id.negativeButton);
        hasNegative.setOnClickListener(this);

        Button dot = (Button) findViewById(R.id.dotButton);
        dot.setOnClickListener(this);

        Button delete = (Button) findViewById(R.id.deleteButton);
        delete.setOnClickListener(this);

        Button parenthesis = (Button) findViewById(R.id.parenthesisButton);
        parenthesis.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        TextView output = (TextView) findViewById(R.id.outputTextView);
        TextView result = (TextView) findViewById(R.id.resultTextView);
        LinearLayout outputLinearLayout = (LinearLayout) findViewById(R.id.outputLinearLayout);
        LinearLayout resultLinearLayout = (LinearLayout) findViewById(R.id.resultLinearLayout);

        if (output.length() > 25){
            output.setTextSize(33);
            output.setText("Maximum input reached. Please press C to restart!");
            if (v.getId() == R.id.cancelButton) {
                output.setText("");
                LinearLayout.LayoutParams normal = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.5f);
                outputLinearLayout.setLayoutParams(normal);
                resultLinearLayout.setLayoutParams(normal);
                output.setTextSize(60);
                result.setTextSize(68);
            }
        }
        if (result.length() > 0) {
            LinearLayout.LayoutParams normal = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.5f);
            outputLinearLayout.setLayoutParams(normal);
            resultLinearLayout.setLayoutParams(normal);
            output.setTextSize(60);
            result.setTextSize(68);
            operators.clear();
            insideOperators.clear();
            numbers.clear();
            insideNumbers.clear();
            beforeParenthesisOperators.clear();
            num = 0;
            insideNum = 0;
            product = 1;
            insideProduct = 1;
            location = 0;
            inParenthesis = false;
            hasDot = false;
            output.setText("");
            result.setText("");
        }
        if (output.length() <= 25) {

            if (output.length() > 9) {
                output.setTextSize(47);
                output.setPadding(5, 5, 5, 5);

                LinearLayout.LayoutParams enlarge = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.8f);
                outputLinearLayout.setLayoutParams(enlarge);

                LinearLayout.LayoutParams reduce = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.2f);
                resultLinearLayout.setLayoutParams(reduce);
            }

            switch (v.getId()) {

                case R.id.oneButton:
                    output.append("1");
                    break;

                case R.id.twoButton:
                    output.append("2");
                    break;

                case R.id.threeButton:
                    output.append("3");
                    break;

                case R.id.fourButton:
                    output.append("4");
                    break;

                case R.id.fiveButton:
                    output.append("5");
                    break;

                case R.id.sixButton:
                    output.append("6");
                    break;

                case R.id.sevenButton:
                    output.append("7");
                    break;

                case R.id.eightButton:
                    output.append("8");
                    break;

                case R.id.nineButton:
                    output.append("9");
                    break;

                case R.id.zeroButton:
                    if (!output.getText().toString().substring(location).equals("0")) {
                        output.append("0");
                    }
                    break;

                case R.id.parenthesisButton:
                    if (!inParenthesis) {
                        //Open a parenthesis after the operator.
                        if (output.length() == 0 || output.getText().toString().substring(output.length() - 1).matches("[+−×÷]")) {
                            //Records the operator just before the parenthesis.
                            if (operators.size() > 0) {
                                beforeParenthesisOperators.add(operators.get(operators.size() - 1));
                            }
                            output.append("(");
                            location = output.length();
                            inParenthesis = true;

                        }
                        //Open a parenthesis after the digit and add "×" before automatically.
                        if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                            onClick(findViewById(R.id.timeButton));
                            beforeParenthesisOperators.add("*");
                            output.append("(");
                            location = output.length();
                            inParenthesis = true;
                        }
                    }
                    //When close the parenthesis.
                    else {
                        //Close ) only a digit is ahead of it.
                        if (Character.isDigit(output.getText().toString().charAt(output.length() - 1))) {
                            String lastString = output.getText().toString().substring(location);
                            insideNumbers.add(Double.parseDouble(lastString));
                            if (insideOperators.size() == 0) {
                                insideNum = insideNumbers.get(insideNumbers.size() - 1);
                            } else {
                                switch (insideOperators.get(insideOperators.size() - 1)) {
                                    case "+":
                                        insideNum += insideNumbers.get(insideNumbers.size() - 1);
                                        break;
                                    case "-":
                                        insideNum -= insideNumbers.get(insideNumbers.size() - 1);
                                        break;
                                    case "*":
                                        insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                        insideNum += insideProduct;
                                        break;
                                    case "/":
                                        insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                        insideNum += insideProduct;
                                        break;
                                }
                            }
                            output.append(")");
                            location = output.length();
                            inParenthesis = false;
                        }
                    }
                    break;

                case R.id.deleteButton:

                    if (output.length() > 0) {

                        String lastChr = output.getText().toString().substring(output.length() - 1);
                        output.setText(output.getText().toString().substring(0, output.length() - 1));
                        if (lastChr.equals(".")) {
                            hasDot = false;
                        }

                        //Outside parenthesis
                        if (!inParenthesis) {
                            if (lastChr.equals("+") || lastChr.equals("\u2212")) {
                                if (operators.size() > 1) {

                                    switch (operators.get(operators.size() - 2)) {
                                        case "+":
                                            num -= numbers.get(numbers.size() - 1);
                                            break;
                                        case "-":
                                            num += numbers.get(numbers.size() - 1);
                                            break;
                                        case "*":
                                            num -= product;
                                            product /= numbers.get(numbers.size() - 1);
                                            break;
                                        case "/":
                                            num -= product;
                                            product *= numbers.get(numbers.size() - 1);
                                            break;
                                    }
                                    location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 2)) + 1;
                                } else {
                                    num = 0;
                                    location = 0;
                                }
                                operators.remove(operators.size() - 1);
                                numbers.remove(numbers.size() - 1);
                            }
                            if (lastChr.equals("×") || lastChr.equals("÷")) {
                                if (operators.size() > 1) {
                                    switch (operators.get(operators.size() - 2)) {
                                        case "+":
                                            product = 1;
                                            break;
                                        case "-":
                                            product = -1;
                                            break;
                                        case "*":
                                            product /= numbers.get(numbers.size() - 1);
                                            break;
                                        case "/":
                                            product *= numbers.get(numbers.size() - 1);
                                            break;
                                    }
                                    location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 2)) + 1;
                                } else {
                                    product = 1;
                                    location = 0;
                                }
                                operators.remove(operators.size() - 1);
                                numbers.remove(numbers.size() - 1);
                            }

                            if (lastChr.equals(")")) {
                                if (insideOperators.size() > 0) {
                                    switch (insideOperators.get(insideOperators.size() - 1)) {
                                        case "+":
                                            insideNum -= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "-":
                                            insideNum += insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "*":
                                            insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "/":
                                            insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                    }
                                    insideNumbers.remove(insideNumbers.size() - 1);
                                    location = output.getText().toString().lastIndexOf(insideOperators.get(insideOperators.size() - 1)) + 1;
                                } else {
                                    insideNum = 0;
                                    location = output.getText().toString().lastIndexOf("(") + 1;
                                }
                                inParenthesis = true;
                            }
                        }

                        if (inParenthesis) {
                            if (lastChr.equals("+") || lastChr.equals("\u2212")) {
                                if (insideOperators.size() > 1) {

                                    switch (insideOperators.get(insideOperators.size() - 2)) {
                                        case "+":
                                            insideNum -= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "-":
                                            insideNum += insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "*":
                                            insideNum -= insideProduct;
                                            insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "/":
                                            insideNum -= insideProduct;
                                            insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                    }
                                    location = output.getText().toString().lastIndexOf(insideOperators.get(insideOperators.size() - 2)) + 1;
                                } else {
                                    insideNum = 0;
                                    location = 0;
                                }
                                insideOperators.remove(insideOperators.size() - 1);
                                insideNumbers.remove(insideNumbers.size() - 1);
                            }
                            if (lastChr.equals("×") || lastChr.equals("÷")) {
                                if (insideOperators.size() > 1) {
                                    switch (insideOperators.get(insideOperators.size() - 2)) {
                                        case "+":
                                            insideProduct = 1;
                                            break;
                                        case "-":
                                            insideProduct = -1;
                                            break;
                                        case "*":
                                            insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "/":
                                            insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                    }
                                    location = output.getText().toString().lastIndexOf(insideOperators.get(insideOperators.size() - 2)) + 1;
                                } else {
                                    insideProduct = 1;
                                    location = 0;
                                }
                                insideOperators.remove(insideOperators.size() - 1);
                                insideNumbers.remove(insideNumbers.size() - 1);
                            }

                            if (lastChr.equals("(")) {
                                if (beforeParenthesisOperators.size() > 0) {
                                    beforeParenthesisOperators.remove(beforeParenthesisOperators.size() - 1);
                                }
                                inParenthesis = false;
                                location--;

                            }
                        }

                    }

                    break;

                case R.id.dotButton:
                    if (!hasDot) {

                        if (output.length() == 0) {
                            output.append("0.");
                        } else {
                            String lastChr = output.getText().toString().substring(output.length() - 1);
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                output.append(".");
                            }
                            if (lastChr.matches("[+−×÷-]") || lastChr.equals("(")) {
                                output.append("0.");
                            }
                        }
                        hasDot = true;
                    }

                    break;

                case R.id.negativeButton:

                    //No input at all or no input after operator.
                    if (location == output.length()) {
                        output.append("-");
                    }
                    //There is only negative sign there.
                    else if (output.getText().toString().substring(location).equals("-")) {
                        output.setText(output.getText().toString().substring(0, location));
                    }
                    //There is only number after operator.
                    else if (Character.isDigit(output.getText().toString().charAt(location))) {
                        output.setText(output.getText().toString().substring(0, location) + "-" + output.getText().toString().substring(location));
                    }
                    //There is number AND negative sign after operator.
                    else if (output.getText().toString().substring(location, location + 1).equals("-")) {
                        output.setText(output.getText().toString().substring(0, location) + output.getText().toString().substring(location + 1));
                    }

                    break;

                case R.id.plusButton:
                    //When it is not inside the parenthesis.

                    if (!inParenthesis) {
                        if (output.length() == 0) {
                            output.append("0+");
                            location = output.length();
                            numbers.add(0.0);
                            operators.add("+");
                            hasDot = false;
                        } else {
                            //Determine the character right before the operator.

                            //When click plus button right after a digit
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                String lastString = output.getText().toString().substring(location);
                                numbers.add(Double.parseDouble(lastString));
                                if (operators.size() == 0) {
                                    num = numbers.get(numbers.size() - 1);
                                } else {
                                    switch (operators.get(operators.size() - 1)) {
                                        case "+":
                                            num += numbers.get(numbers.size() - 1);
                                            break;
                                        case "-":
                                            num -= numbers.get(numbers.size() - 1);
                                            break;
                                        case "*":
                                            product *= numbers.get(numbers.size() - 1);
                                            num += product;
                                            break;
                                        case "/":
                                            product /= numbers.get(numbers.size() - 1);
                                            num += product;
                                            break;
                                    }
                                }
                                output.append("+");
                                location = output.length();
                                operators.add("+");
                            }
                            //When it is parenthesis ")".
                            if (output.getText().toString().substring(output.length() - 1).equals(")")) {
                                if (beforeParenthesisOperators.size() == 0) {
                                    num = insideNum;
                                } else {
                                    switch (beforeParenthesisOperators.get(beforeParenthesisOperators.size() - 1)) {
                                        case "+":
                                            num += insideNum;
                                            break;
                                        case "-":
                                            num -= insideNum;
                                            break;
                                        case "*":
                                            product *= insideNum;
                                            num += product;
                                            break;
                                        case "/":
                                            product /= insideNum;
                                            num += product;
                                            break;
                                    }
                                }
                                output.append("+");
                                location = output.length();
                                operators.add("+");
                                hasDot = false;
                            }
                        }
                    }

                    //When it is inside the parenthesis.
                    if (inParenthesis) {
                        if (output.length() == 1) {
                            insideNumbers.add(0.0);
                            output.append("0+");
                            location = output.length();
                            insideOperators.add("+");
                            hasDot = false;

                        } else if (Character.isDigit(output.getText().toString().charAt(output.length() - 1))) {
                            String lastString = output.getText().toString().substring(location);
                            insideNumbers.add(Double.parseDouble(lastString));
                            if (insideOperators.size() == 0) {
                                insideNum = insideNumbers.get(insideNumbers.size() - 1);
                            } else {
                                switch (insideOperators.get(insideOperators.size() - 1)) {
                                    case "+":
                                        insideNum += insideNumbers.get(insideNumbers.size() - 1);
                                        break;
                                    case "-":
                                        insideNum -= insideNumbers.get(insideNumbers.size() - 1);
                                        break;
                                    case "*":
                                        insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                        insideNum += insideProduct;
                                        break;
                                    case "/":
                                        insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                        insideNum += insideProduct;
                                }
                            }
                            output.append("+");
                            location = output.length();
                            insideOperators.add("+");
                            hasDot = false;
                        }
                    }
                    break;

                case R.id.minusButton:
                    if (!inParenthesis) {
                        if (output.length() == 0) {
                            numbers.add(0.0);
                            output.append("0−");
                            location = output.length();
                            operators.add("-");
                            hasDot = false;
                        } else {
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                String lastString = output.getText().toString().substring(location);
                                numbers.add(Double.parseDouble(lastString));
                                if (operators.size() == 0) {
                                    num = numbers.get(numbers.size() - 1);
                                } else {
                                    switch (operators.get(operators.size() - 1)) {

                                        case "+":
                                            num += numbers.get(numbers.size() - 1);
                                            break;
                                        case "-":
                                            num -= numbers.get(numbers.size() - 1);
                                            break;
                                        case "*":
                                            product *= numbers.get(numbers.size() - 1);
                                            num += product;
                                            break;
                                        case "/":
                                            product /= numbers.get(numbers.size() - 1);
                                            num += product;
                                            break;
                                    }
                                }
                                output.append("−");
                                location = output.length();
                                operators.add("-");
                                hasDot = false;
                            }

                            if (output.getText().toString().substring(output.length() - 1).equals(")")) {
                                if (beforeParenthesisOperators.size() == 0) {
                                    num = insideNum;
                                } else {
                                    switch (beforeParenthesisOperators.get(beforeParenthesisOperators.size() - 1)) {
                                        case "+":
                                            num += insideNum;
                                            break;
                                        case "-":
                                            num -= insideNum;
                                            break;
                                        case "*":
                                            product *= insideNum;
                                            num += product;
                                            break;
                                        case "/":
                                            product /= insideNum;
                                            num += product;
                                            break;
                                    }
                                }
                                output.append("\u2212");
                                location = output.length();
                                operators.add("-");
                                hasDot = false;
                            }
                        }
                    }

                    if (inParenthesis) {
                        if (output.length() == 1) {
                            insideNumbers.add(0.0);
                            output.append("0−");
                            location = output.length();
                            insideOperators.add("+");
                            hasDot = false;
                        } else if (Character.isDigit(output.getText().toString().charAt(output.length() - 1))) {
                            String lastString = output.getText().toString().substring(location);
                            insideNumbers.add(Double.parseDouble(lastString));
                            if (insideOperators.size() == 0) {
                                insideNum = insideNumbers.get(insideNumbers.size() - 1);
                            } else {
                                switch (insideOperators.get(insideOperators.size() - 1)) {
                                    case "+":
                                        insideNum += insideNumbers.get(insideNumbers.size() - 1);
                                        break;
                                    case "-":
                                        insideNum -= insideNumbers.get(insideNumbers.size() - 1);
                                        break;
                                    case "*":
                                        insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                        insideNum += insideProduct;
                                        break;
                                    case "/":
                                        insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                        insideNum += insideProduct;
                                }
                            }
                            output.append("−");
                            location = output.length();
                            insideOperators.add("-");
                            hasDot = false;
                        }
                    }
                    break;

                case R.id.timeButton:
                    if (!inParenthesis) {
                        //First time input
                        if (output.length() == 0) {
                            product = 0;
                            numbers.add(0.0);
                            output.append("0×");
                            location = output.length();
                            operators.add("*");
                            hasDot = false;
                        } else {
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                String lastString = output.getText().toString().substring(location);
                                numbers.add(Double.parseDouble(lastString));
                                if (operators.size() == 0) {
                                    product *= numbers.get(numbers.size() - 1);
                                } else {
                                    switch (operators.get(operators.size() - 1)) {
                                        case "+":
                                            product = numbers.get(numbers.size() - 1);
                                            break;
                                        case "-":
                                            product = -1 * numbers.get(numbers.size() - 1);
                                            break;
                                        case "*":
                                            product *= numbers.get(numbers.size() - 1);
                                            break;
                                        case "/":
                                            product /= numbers.get(numbers.size() - 1);
                                            break;
                                    }
                                }
                                output.append("×");
                                location = output.length();
                                operators.add("*");
                                hasDot = false;
                            }
                            if (output.getText().toString().substring(output.length() - 1).equals(")")) {
                                if (beforeParenthesisOperators.size() == 0) {
                                    product = insideProduct;
                                } else {
                                    switch (beforeParenthesisOperators.get(beforeParenthesisOperators.size() - 1)) {
                                        case "+":
                                            product = insideNum;
                                            break;
                                        case "-":
                                            product = -1 * insideNum;
                                            break;
                                        case "*":
                                            product *= insideNum;
                                            break;
                                        case "/":
                                            product /= insideNum;
                                            break;
                                    }
                                }
                                output.append("×");
                                location = output.length();
                                operators.add("*");
                                hasDot = false;
                            }
                        }
                    }

                    if (inParenthesis) {
                        if (output.length() == 1) {
                            insideNumbers.add(0.0);
                            output.append("0×");
                            location = output.length();
                            insideOperators.add("*");
                            hasDot = false;
                        } else {
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                String lastString = output.getText().toString().substring(location);
                                insideNumbers.add(Double.parseDouble(lastString));
                                if (insideOperators.size() == 0) {
                                    insideProduct = insideNumbers.get(insideNumbers.size() - 1);
                                } else {
                                    switch (insideOperators.get(insideOperators.size() - 1)) {
                                        case "+":
                                            insideProduct = insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "-":
                                            insideProduct = -1 * insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "*":
                                            insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "/":
                                            insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                    }
                                }
                                output.append("×");
                                location = output.length();
                                insideOperators.add("*");
                                hasDot = false;
                            }
                        }
                    }
                    break;

                case R.id.divideButton:
                    if (!inParenthesis) {
                        if (output.length() == 0) {
                            product = 0;
                            numbers.add(0.0);
                            output.append("0÷");
                            location = output.length();
                            operators.add("/");
                            hasDot = false;
                        } else {
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                String lastString = output.getText().toString().substring(location);
                                numbers.add(Double.parseDouble(lastString));
                                if (operators.size() == 0) {
                                    product *= numbers.get(numbers.size() - 1);
                                } else {
                                    switch (operators.get(operators.size() - 1)) {
                                        case "+":
                                            product = numbers.get(numbers.size() - 1);
                                            break;
                                        case "-":
                                            product = -1 * numbers.get(numbers.size() - 1);
                                            break;
                                        case "*":
                                            product *= numbers.get(numbers.size() - 1);
                                            break;
                                        case "/":
                                            product /= numbers.get(numbers.size() - 1);
                                            break;
                                    }
                                }
                                output.append("÷");
                                location = output.length();
                                operators.add("/");
                                hasDot = false;
                            }
                            if (output.getText().toString().substring(output.length() - 1).equals(")")) {
                                if (beforeParenthesisOperators.size() == 0) {
                                    product = insideNum;
                                } else {
                                    switch (beforeParenthesisOperators.get(beforeParenthesisOperators.size() - 1)) {
                                        case "+":
                                            product = insideNum;
                                            break;
                                        case "-":
                                            product = -1 * insideNum;
                                            break;
                                        case "*":
                                            product *= insideNum;
                                            break;
                                        case "/":
                                            product /= insideNum;
                                            break;
                                    }
                                }
                                output.append("÷");
                                location = output.length();
                                operators.add("/");
                                hasDot = false;
                            }
                        }
                    }

                    if (inParenthesis) {
                        if (output.length() == 1) {
                            insideNumbers.add(0.0);
                            output.append("0÷");
                            location = output.length();
                            insideOperators.add("/");
                            hasDot = false;
                        } else {
                            if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                                String lastString = output.getText().toString().substring(location);
                                insideNumbers.add(Double.parseDouble(lastString));
                                if (insideOperators.size() == 0) {
                                    insideProduct = insideNumbers.get(insideNumbers.size() - 1);
                                } else {
                                    switch (insideOperators.get(insideOperators.size() - 1)) {
                                        case "+":
                                            insideProduct = insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "-":
                                            insideProduct = -1 * insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "*":
                                            insideProduct *= insideNumbers.get(insideNumbers.size() - 1);
                                            break;
                                        case "/":
                                            insideProduct /= insideNumbers.get(insideNumbers.size() - 1);
                                    }
                                }
                                output.append("÷");
                                location = output.length();
                                insideOperators.add("/");
                                hasDot = false;
                            }
                        }
                    }
                    break;

                case R.id.cancelButton:
                    LinearLayout.LayoutParams normal = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.5f);
                    outputLinearLayout.setLayoutParams(normal);
                    resultLinearLayout.setLayoutParams(normal);
                    output.setTextSize(60);
                    result.setTextSize(68);
                    operators.clear();
                    insideOperators.clear();
                    numbers.clear();
                    insideNumbers.clear();
                    beforeParenthesisOperators.clear();
                    num = 0;
                    insideNum = 0;
                    product = 1;
                    insideProduct = 1;
                    location = 0;
                    inParenthesis = false;
                    hasDot = false;
                    output.setText("");
                    result.setText("");

                    break;

                case R.id.equalButton:
                    if (inParenthesis) {
                        onClick(findViewById(R.id.parenthesisButton));
                    }
                    if (output.length() == 0) {
                        result.setText("= 0");
                    } else {
                        //If the last input is number:
                        if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                            String lastString = output.getText().toString().substring(location);
                            numbers.add(Double.parseDouble(lastString));
                            if (operators.size() == 0) {
                                if (lastString.length() <= 8) {
                                    result.setText("= " + lastString);
                                } else {
                                    result.setText("= " + lastString.substring(0, 8));
                                }
                            } else {
                                switch (operators.get(operators.size() - 1)) {
                                    case "+":
                                        num += numbers.get(numbers.size() - 1);
                                        break;
                                    case "-":
                                        num -= numbers.get(numbers.size() - 1);
                                        break;
                                    case "*":
                                        product *= numbers.get(numbers.size() - 1);
                                        num += product;
                                        break;
                                    case "/":
                                        product /= numbers.get(numbers.size() - 1);
                                        num += product;
                                        break;
                                }
                                if (num % 1 == 0) {
                                    String finalOutput = "= " + (int) num;
                                    if (finalOutput.length() >= 10) {
                                        result.setText(finalOutput.substring(0, 10));
                                    } else {
                                        result.setText(finalOutput);
                                    }
                                } else {
                                    String finalOutput = "= " + (float) num;
                                    if (finalOutput.length() >= 10) {
                                        result.setText(finalOutput.substring(0, 10));
                                    } else {
                                        result.setText(finalOutput);
                                    }
                                }
                            }
                        }

                        if (output.getText().toString().substring(output.length() - 1).equals(")")) {
                            if (beforeParenthesisOperators.size() == 0) {
                                num = insideNum;

                            } else {
                                switch (beforeParenthesisOperators.get(beforeParenthesisOperators.size() - 1)) {
                                    case "+":
                                        num += insideNum;
                                        break;
                                    case "-":
                                        num -= insideNum;
                                        break;
                                    case "*":
                                        product *= insideNum;
                                        num += product;
                                        break;
                                    case "/":
                                        product /= insideNum;
                                        num += product;
                                        break;
                                }
                            }
                            if (num % 1 == 0) {

                                String finalOutput = "= " + (int) num;
                                if (finalOutput.length() >= 10) {
                                    result.setText(finalOutput.substring(0, 10));
                                } else {
                                    result.setText(finalOutput);
                                }
                            } else {
                                String finalOutput = "= " + (float) num;
                                if (finalOutput.length() >= 10) {
                                    result.setText(finalOutput.substring(0, 10));
                                } else {
                                    result.setText(finalOutput);
                                }
                            }
                        }
                    }
                    break;
            }
        }

    }
}

