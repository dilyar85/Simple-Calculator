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
    double lastNum = 0;
    int location = 0;
    double product = 1;

    ArrayList<String> operators = new ArrayList<String>();

    String lastString = "";
    boolean negative = false;
    boolean hasDot = false;

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

        Button dot = (Button) findViewById(R.id.dotButton);
        dot.setOnClickListener(this);

        Button delete = (Button) findViewById(R.id.deleteButton);
        delete.setOnClickListener(this);

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

            case R.id.deleteButton:
                if (output.length() > 0) {
                    String lastChr = output.getText().toString().substring(output.length() - 1);
                    output.setText(output.getText().toString().substring(0, output.length() - 1));

                    if (lastChr.equals(".")) {
                        hasDot = false;
                    }
                    if (lastChr.equals("+") || lastChr.equals("\u2212")) {

                        switch (operators.get(operators.size() - 2)) {
                            case "+":
                                num -= lastNum;
                                break;
                            case "-":
                                num += lastNum;
                                break;
                            case "*":
                                num -= product;
                                product /= lastNum;
                                break;
                            case "/":
                                num -= product;
                                product *= lastNum;
                                break;
                        }
                        operators.remove(operators.size() - 1);
                        location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 1)) + 1;
                    }
                    if (lastChr.equals("×") || lastChr.equals("÷")) {
                        switch (operators.get(operators.size() - 2)) {
                            case "+":
                                product = 1;
                                break;
                            case "-":
                                product = -1;
                                break;
                            case "*":
                                product /= lastNum;
                                break;
                            case "/":
                                product *= lastNum;
                                break;
                        }
                        operators.remove(operators.size() - 1);
                        location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 1)) + 1;
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

                    if (!negative) {
                        output.setText("-" + output.getText());
                        negative = true;
                    } else {
                        output.setText(output.getText().toString().replace("-", ""));
                        negative = false;
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
                            String removeNegative = output.getText().toString().substring(0, location) + output.getText().toString().substring(location + 1);
                            output.setText(removeNegative);
                        } else {
                            output.append("-");
                        }
                    }
                }

                break;

            case R.id.plusButton:
                if (output.length() == 0) {
                    output.append("0+");
                } else {
                    output.append("+");

                    lastString = output.getText().toString().substring(location, output.length() - 1);
                    lastNum = Double.parseDouble(lastString);
                    //to avoid operators.size() - 1 < 0, here uses if & else if statement.
                    if (operators.size() == 0) {
                        num += lastNum;
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        num += lastNum;
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        num -= lastNum;
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= lastNum;
                        num += product;
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= lastNum;
                        num += product;
                    }
                }
                operators.add("+");
                location = output.length();
                negative = false;
                hasDot = false;

                break;

            case R.id.minusButton:

                if (output.length() == 0) {
                    output.append("0\u2212");
                } else {
                    output.append("\u2212");

                    lastString = output.getText().toString().substring(location, output.length() - 1);
                    lastNum = Double.parseDouble(lastString);
                    if (operators.size() == 0) {
                        num += lastNum;
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        num += lastNum;
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        num -= lastNum;
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= lastNum;
                        num += product;
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= lastNum;
                        num += product;
                    }
                }
                operators.add("-");
                location = output.length();
                negative = false;
                hasDot = false;

                break;

            case R.id.timeButton:

                if (output.length() == 0) {
                    output.append("0×");
                    product = 0;
                } else {
                    output.append("×");

                    lastString = output.getText().toString().substring(location, output.length() - 1);
                    lastNum = Double.parseDouble(lastString);
                    if (operators.size() == 0) {
                        product *= lastNum;
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        product = lastNum;
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        product = -1 * lastNum;
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= lastNum;
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= lastNum;
                    }

                }
                operators.add("*");
                location = output.length();
                negative = false;
                hasDot = false;

                break;

            case R.id.divideButton:

                if (output.length() == 0) {
                    output.append("0\u00F7");
                    product = 0;
                } else {
                    output.append("\u00F7");

                    lastString = output.getText().toString().substring(location, output.length() - 1);
                    lastNum = Double.parseDouble(lastString);
                    if (operators.size() == 0) {
                        product *= lastNum;
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        product = lastNum;
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        product = -1 * lastNum;
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= lastNum;
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= lastNum;
                    }
                }
                operators.add("/");
                location = output.length();
                negative = false;
                hasDot = false;

                break;

            case R.id.cancelButton:
                operators.clear();
                num = 0;
                product = 1;
                location = 0;
                lastNum = 0;
                lastString = "";
                negative = false;
                hasDot = false;
                output.setText("");
                result.setText("");

                break;

            case R.id.equalButton:
                if (output.length() == 0) {
                    result.setText("= 0");
                } else {
                    lastString = output.getText().toString().substring(location);
                    lastNum = Double.parseDouble(lastString);
                    if (operators.size() == 0) {
                        result.setText("= " + lastString);
                    } else {
                        if (operators.get(operators.size() - 1).equals("+")) {
                            num += lastNum;
                        } else if (operators.get(operators.size() - 1).equals("-")) {
                            num -= lastNum;
                        } else if (operators.get(operators.size() - 1).equals("*")) {
                            product *= lastNum;
                            num += product;
                        } else if (operators.get(operators.size() - 1).equals("/")) {
                            product /= lastNum;
                            num += product;
                        }

                        if (num % 1 == 0) {
                            result.setText("= " + (int) num);
                        } else {

                            result.setText("= " + (float) num);
                        }
                    }
                }
                
                break;

            default:
                break;
        }
    }
}