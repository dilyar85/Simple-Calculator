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
    int location = 0;
    double product = 1;

    ArrayList<Double> numbers = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<String>();


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
                        } else {
                            num = 0;
                        }
                        operators.remove(operators.size() - 1);
                        numbers.remove(numbers.size() - 1);
                        location = output.getText().toString().lastIndexOf(operators.get(operators.size() - 1)) + 1;
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
                        } else {
                            product = 1;
                        }
                        operators.remove(operators.size() - 1);
                        numbers.remove(numbers.size() - 1);
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
                    numbers.add(0.0);
                } else {
                    output.append("+");

                    String lastString = output.getText().toString().substring(location, output.length() - 1);
                    numbers.add(Double.parseDouble(lastString));
                    //to avoid operators.size() - 1 < 0, here uses if & else if statement.
                    if (operators.size() == 0) {
                        num += numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        num += numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        num -= numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= numbers.get(numbers.size() - 1);
                        num += product;
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= numbers.get(numbers.size() - 1);
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
                    numbers.add(0.0);
                } else {
                    output.append("\u2212");

                    String lastString = output.getText().toString().substring(location, output.length() - 1);
                    numbers.add(Double.parseDouble(lastString));
                    if (operators.size() == 0) {
                        num += numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        num += numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        num -= numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= numbers.get(numbers.size() - 1);
                        num += product;
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= numbers.get(numbers.size() - 1);
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

                    String lastString = output.getText().toString().substring(location, output.length() - 1);
                    numbers.add(Double.parseDouble(lastString));
                    if (operators.size() == 0) {
                        product *= numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        product = numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        product = -1 * numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= numbers.get(numbers.size() - 1);
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

                    String lastString = output.getText().toString().substring(location, output.length() - 1);
                    numbers.add(Double.parseDouble(lastString));
                    if (operators.size() == 0) {
                        product *= numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("+")) {
                        product = numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("-")) {
                        product = -1 * numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("*")) {
                        product *= numbers.get(numbers.size() - 1);
                    } else if (operators.get(operators.size() - 1).equals("/")) {
                        product /= numbers.get(numbers.size() - 1);
                    }
                }
                operators.add("/");
                location = output.length();
                negative = false;
                hasDot = false;

                break;

            case R.id.cancelButton:
                operators.clear();
                numbers.clear();
                num = 0;
                product = 1;
                location = 0;
                negative = false;
                hasDot = false;
                output.setText("");
                result.setText("");

                break;

            case R.id.equalButton:
                if (output.length() == 0) {
                    result.setText("= 0");
                } else {
                    String lastString = output.getText().toString().substring(location);
                    numbers.add(Double.parseDouble(lastString));
                    if (operators.size() == 0) {
                        result.setText("= " + lastString);
                    } else {
                        if (operators.get(operators.size() - 1).equals("+")) {
                            num += numbers.get(numbers.size() - 1);
                        } else if (operators.get(operators.size() - 1).equals("-")) {
                            num -= numbers.get(numbers.size() - 1);
                        } else if (operators.get(operators.size() - 1).equals("*")) {
                            product *= numbers.get(numbers.size() - 1);
                            num += product;
                        } else if (operators.get(operators.size() - 1).equals("/")) {
                            product /= numbers.get(numbers.size() - 1);
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