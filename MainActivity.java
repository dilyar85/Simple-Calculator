package com.example.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

import org.w3c.dom.Text;

import java.util.zip.DeflaterOutputStream;

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

    boolean hasNegative = false;
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
                output.append("0");
                break;

            case R.id.parenthesisButton:
                if (!inParenthesis) {
                    if (output.length() == 0 || output.getText().toString().substring(output.length() - 1).matches("[+−×÷]")) {
                        if (operators.size() > 0) {
                            beforeParenthesisOperators.add(operators.get(operators.size() - 1));
                        }
                        output.append("(");
                        location = output.length();
                        inParenthesis = true;

                    }
                }
                //When close the parenthesis.
                else {
                    //Close ) only a digit before it.
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
                            location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 1)) + 1;
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
                            location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 1)) + 1;
                        } else {
                            product = 1;
                            location = 0;
                        }
                        operators.remove(operators.size() - 1);
                        numbers.remove(numbers.size() - 1);
                    }
                }

                break;

            case R.id.dotButton:

                if (output.length() == 0) {
                    output.append("0.");
                } else {
                    String lastChr = output.getText().toString().substring(output.length() - 1);

                    if (Character.isDigit(output.getText().charAt(output.length() - 1)) && !hasDot) {
                        output.append(".");
                    } else if (output.getText().toString().substring(output.length() - 1).equals("-")) {
                        output.append("0.");
                    } else if (lastChr.equals("+") || lastChr.equals("\u2212") || lastChr.equals("\u00D7") || lastChr.equals("\u00F7")) {
                        output.append("0.");
                    }
                }
                hasDot = true;

                break;

            case R.id.negativeButton:
                if (operators.size() == 0) {

                    if (!hasNegative) {
                        output.setText("-" + output.getText());
                        hasNegative = true;
                    } else {
                        output.setText(output.getText().toString().replace("-", ""));
                        hasNegative = false;
                    }
                } else {
                    if (output.getText().length() == location) {
                        output.append("-");
                    } else {
                        String lastInput = output.getText().toString().substring(location);
                        if (lastInput.equals("-")) {
                            output.setText(output.getText().toString().substring(0, output.length() - 1));
                        } else if (Character.isDigit(lastInput.charAt(0))) {
                            output.setText(output.getText().toString().substring(0, location) + "-" + lastInput);
                        } else if (lastInput.substring(0, 1).equals("-")) {
                            String negativeRemoved = output.getText().toString().substring(0, location) + output.getText().toString().substring(location + 1);
                            output.setText(negativeRemoved);
                        } else {
                            output.append("-");
                        }
                    }
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
                        hasNegative = false;
                        hasDot = false;
                    } else {
                        //Determine the character right before the operator.

                        //When it is digit.
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
                            hasNegative = false;
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
                        hasNegative = false;
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
                        hasNegative = false;
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
                        hasNegative = false;
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
                            output.append("+");
                            location = output.length();
                            operators.add("+");
                            hasNegative = false;
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
                            output.append("+");
                            location = output.length();
                            operators.add("+");
                            hasNegative = false;
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
                        hasNegative = false;
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
                        hasNegative = false;
                        hasDot = false;
                    }
                }
                break;

            case R.id.timeButton:
                if (!inParenthesis) {
                    if (output.length() == 0) {
                        product = 0;
                        numbers.add(0.0);
                        output.append("0×");
                        location = output.length();
                        operators.add("*");
                        hasNegative = false;
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
                            hasNegative = false;
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
                            hasNegative = false;
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
                        hasNegative = false;
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
                            hasNegative = false;
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
                        hasNegative = false;
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
                            hasNegative = false;
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
                            hasNegative = false;
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
                        hasNegative = false;
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
                            hasNegative = false;
                            hasDot = false;
                        }
                    }
                }
                break;

            case R.id.cancelButton:
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
                hasNegative = false;
                hasDot = false;
                output.setText("");
                result.setText("");

                break;

            case R.id.equalButton:
                if (output.length() == 0) {
                    result.setText("= 0");
                } else {
                    if (Character.isDigit(output.getText().charAt(output.length() - 1))) {
                        String lastString = output.getText().toString().substring(location);
                        numbers.add(Double.parseDouble(lastString));
                        if (operators.size() == 0) {
                            result.setText("= " + lastString);
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
                                result.setText("= " + (int) num);
                            } else {

                                result.setText("= " + (float) num);
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
                            result.setText("= " + (int) num);
                        } else {

                            result.setText("= " + (float) num);
                        }
                    }
                }
                break;
        }
    }
}
