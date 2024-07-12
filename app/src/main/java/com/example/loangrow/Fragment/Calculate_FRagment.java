package com.example.loangrow.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.loangrow.R;

public class Calculate_FRagment extends Fragment implements View.OnClickListener {
    private EditText editTextResult;
    private String currentInput = "";
    private boolean lastNumeric = false;
    private boolean lastDot = false;
    private boolean lastOperator = false;
    Button button_backspace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculate__f_ragment, container, false);
        editTextResult = view.findViewById(R.id.editTextResult);

        button_backspace = view.findViewById(R.id.button_backspace);
        // Set click listeners for all buttons
        setButtonClickListeners(view);
        return view;
    }

    private void setButtonClickListeners(View view) {
        // Find buttons by ID and set click listeners
        int[] buttonIds = {R.id.button_CE, R.id.button_backspace, R.id.button_equle, R.id.button_point,
                R.id.button_plus, R.id.button_minus, R.id.button_multiple, R.id.button_divide, R.id.button_cause,
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
                R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};

        for (int buttonId : buttonIds) {
            Button button = view.findViewById(buttonId);
            button.setOnClickListener(this);
        }

        button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBackspace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "CE":
                currentInput = "";
                lastNumeric = false;
                lastDot = false;
                lastOperator = false;
                break;
            case "=":
                if (lastNumeric) {
                    currentInput = evaluateExpression(currentInput);
                }
                break;
            case ".":
                if (lastNumeric && !lastDot) {
                    currentInput += buttonText;
                    lastNumeric = false;
                    lastDot = true;
                    lastOperator = false;
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!lastOperator && lastNumeric) {
                    currentInput += buttonText;
                    lastNumeric = false;
                    lastDot = false;
                    lastOperator = true;
                }
                break;
            default:
                currentInput += buttonText;
                lastNumeric = true;
                lastOperator = false;
                break;
        }

        editTextResult.setText(currentInput);
    }

    private String evaluateExpression(String expression) {
        try {
            double result = eval(expression);
            if (result == (int) result) {
                return String.valueOf((int) result);
            } else {
                return String.valueOf(result);
            }
        } catch (Exception e) {
            return "Error";
        }
    }

    private double eval(final String str) {
        return new ExpressionEvaluator().parse(str);
    }

    private static class ExpressionEvaluator {
        private int pos = -1, ch;

        private void nextChar(String str) {
            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
        }

        private boolean eat(String str, int charToEat) {
            while (ch == ' ') nextChar(str);
            if (ch == charToEat) {
                nextChar(str);
                return true;
            }
            return false;
        }

        private double parse(String str) {
            nextChar(str);
            double x = parseExpression(str);
            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
            return x;
        }

        private double parseExpression(String str) {
            double x = parseTerm(str);
            for (;;) {
                if (eat(str, '+')) x += parseTerm(str); // addition
                else if (eat(str, '-')) x -= parseTerm(str); // subtraction
                else return x;
            }
        }

        private double parseTerm(String str) {
            double x = parseFactor(str);
            for (;;) {
                if (eat(str, '*')) x *= parseFactor(str); // multiplication
                else if (eat(str, '/')) x /= parseFactor(str); // division
                else return x;
            }
        }

        private double parseFactor(String str) {
            if (eat(str, '+')) return parseFactor(str); // unary plus
            if (eat(str, '-')) return -parseFactor(str); // unary minus
            double x;
            int startPos = this.pos;
            if (eat(str, '(')) { // parentheses
                x = parseExpression(str);
                eat(str, ')');
            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar(str);
                x = Double.parseDouble(str.substring(startPos, this.pos));
            } else {
                throw new RuntimeException("Unexpected: " + (char) ch);
            }
            return x;
        }
    }

    private void handleBackspace() {
        String currentText = editTextResult.getText().toString();
        if (!currentText.isEmpty() && currentText.length() > 1) {
            String newText = currentText.substring(0, currentText.length() - 1);
            currentInput = newText;
            lastNumeric = Character.isDigit(newText.charAt(newText.length() - 1));
            lastDot = newText.contains(".");
            lastOperator = !lastNumeric && !lastDot;
            editTextResult.setText(newText);
            editTextResult.setSelection(newText.length());
        } else {
            editTextResult.setText("");
            currentInput = "";
            lastNumeric = false;
            lastDot = false;
            lastOperator = false;
        }
    }
}
