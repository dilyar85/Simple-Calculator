package com.example.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {
    double num = 0;
    int index = 0;
    double product = 1;
    int factor = 1;
    String operator = "";
    boolean negative = false;
    double previousNum = 0;

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

        Button negative = (Button) findViewById(R.id.negativeButton);
        negative.setOnClickListener(this);

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

            case R.id.negativeButton:
                if (operator.equals("")) {

                    if (!negative) {
                        output.setText("-" + output.getText());
                        negative = true;
                    } else {
                        output.setText(output.getText().toString().replace("-", ""));
                        negative = false;
                    }
                } else {
                    if (output.getText().length() == index) {
                        output.append("-");
                    } else {
                        String lastInput = output.getText().toString().substring(index);
                        if (lastInput.equals("-")) {
                            output.setText(output.getText().toString().substring(0, output.length() - 1));
                        } else if (Character.isDigit(lastInput.charAt(0))) {
                            output.setText(output.getText().toString().substring(0, index) + "-" + lastInput);
                        } else if (lastInput.substring(0, 1).equals("-")) {
                            String removeNegative = output.getText().toString().substring(0, index) + output.getText().toString().substring(index + 1);
                            output.setText(removeNegative);
                        } else {
                            output.append("-");
                        }
                    }
                }

                break;

            case R.id.plusButton:
                negative = false;
                if (output.length() == 0) {
                    output.append("0 + ");
                    index = output.length();
                    operator = "+";

                } else {
                    output.append(" + ");

                    if (operator.equals("+") || operator.equals("")) {
                        String currentOutput = output.getText().toString();
                        String add = currentOutput.substring(index, currentOutput.length() - 3);
                        previousNum = Double.parseDouble(add);
                        num += previousNum;
                        index = currentOutput.length();
                        operator = "+";
                    }
                    if (operator.equals("-")) {
                        String currentOutput = output.getText().toString();
                        String add = currentOutput.substring(index, currentOutput.length() - 3);
                        num -= Double.parseDouble(add);
                        index = currentOutput.length();
                        operator = "+";
                    }
                    if (operator.equals("*")) {
                        String currentOutput = output.getText().toString();
                        product *= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        num = num + factor * product;
                        index = currentOutput.length();
                        operator = "+";
                    }
                    if (operator.equals("/")) {
                        String currentOutput = output.getText().toString();
                        product /= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        num = num + factor * product;
                        index = currentOutput.length();
                        operator = "+";
                    }
                }
                break;

            case R.id.minusButton:
                negative = false;
                if (output.length() == 0) {
                    output.append("0 \u2212 ");
                    index = output.length();
                    operator = "-";

                } else {
                    output.append(" \u2212 ");

                    if (operator.equals("-")) {
                        String currentOutput = output.getText().toString();
                        String add = currentOutput.substring(index, currentOutput.length() - 3);
                        num -= Double.parseDouble(add);
                        index = currentOutput.length();
                        operator = "-";
                    }
                    if (operator.equals("") || operator.equals("+")) {
                        String currentOutput = output.getText().toString();
                        String add = currentOutput.substring(index, currentOutput.length() - 3);
                        num += Double.parseDouble(add);
                        index = currentOutput.length();
                        operator = "-";
                    }
                    if (operator.equals("*")) {
                        String currentOutput = output.getText().toString();
                        product *= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        num = num + factor * product;
                        index = currentOutput.length();
                        operator = "-";
                    }
                    if (operator.equals("/")) {
                        String currentOutput = output.getText().toString();
                        product /= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        num = num + factor * product;
                        index = currentOutput.length();
                        operator = "-";
                    }
                }
                break;

            case R.id.timeButton:
                negative = false;
                if (output.length() == 0) {
                    output.append("0 × ");
                    index = output.length();
                    factor = 0;
                    operator = "*";
                } else {

                    output.append(" × ");

                    if (operator.equals("*") || operator.equals("")) {
                        String currentOutput = output.getText().toString();
                        product *= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "*";
                    }
                    if (operator.equals("/")) {
                        String currentOutput = output.getText().toString();
                        product /= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "*";
                    }
                    if (operator.equals("+")) {
                        String currentOutput = output.getText().toString();
                        product = Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "*";
                        factor = 1;
                    }
                    if (operator.equals("-")) {
                        String currentOutput = output.getText().toString();
                        product = Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "*";
                        factor = -1;
                    }
                }

                break;

            case R.id.divideButton:
                negative = false;
                if (output.length() == 0) {
                    output.append("0 / ");
                    index = output.length();
                    operator = "/";
                    factor = 0;
                } else {
                    output.append(" / ");

                    if (index == 0) {
                        String currentOutput = output.getText().toString();
                        product = Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "/";
                    } else if (operator.equals("/")) {
                        String currentOutput = output.getText().toString();
                        product /= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "/";
                    } else if (operator.equals("*")) {
                        String currentOutput = output.getText().toString();
                        product *= Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "/";
                    }
                    if (operator.equals("+")) {
                        String currentOutput = output.getText().toString();
                        product = Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "*";
                        factor = 1;
                    }
                    if (operator.equals("-")) {
                        String currentOutput = output.getText().toString();
                        product = Double.parseDouble(currentOutput.substring(index, currentOutput.length() - 3));
                        index = currentOutput.length();
                        operator = "*";
                        factor = -1;
                    }
                }

                break;

            case R.id.cancelButton:
                output.setText("");
                result.setText("");
                num = 0;
                product = 1;
                index = 0;
                operator = "";
                factor = 1;
                negative = false;
                break;

            case R.id.equalButton:
                if (operator.equals("+")) {
                    String currentOutput = output.getText().toString();
                    String add = currentOutput.substring(index);
                    num += Double.parseDouble(add);
                }
                if (operator.equals("-")) {
                    String currentOutput = output.getText().toString();
                    String add = currentOutput.substring(index);
                    num -= Double.parseDouble(add);
                }
                if (operator.equals("*")) {
                    String currentOutput = output.getText().toString();
                    product *= Double.parseDouble(currentOutput.substring(index));
                    num = num + factor * product;
                }
                if (operator.equals("/")) {
                    String currentOutput = output.getText().toString();
                    product /= Double.parseDouble(currentOutput.substring(index));
                    num = num + factor * product;
                }
                result.setText("= " + (int) num);
                break;

            default:
                break;
        }
    }
}


