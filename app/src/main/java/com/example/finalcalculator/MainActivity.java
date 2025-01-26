package com.example.finalcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText input1, input2, trigInput;
    private LinearLayout arithmeticLayout, trigonometricLayout;
    private Switch switchDegreesRadians, switchArithmeticTrigonometric;
    private TextView arithmeticResult, trigonometricResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        trigInput = findViewById(R.id.trigInput);
        arithmeticLayout = findViewById(R.id.arithmeticLayout);
        trigonometricLayout = findViewById(R.id.trigonometricLayout);
        switchDegreesRadians = findViewById(R.id.switchDegreesRadians);
        switchArithmeticTrigonometric = findViewById(R.id.switchArithmeticTrigonometric);
        arithmeticResult = findViewById(R.id.arithmeticResult);
        trigonometricResult = findViewById(R.id.trigonometricResult);

        // Initial visibility
        trigonometricLayout.setVisibility(View.GONE);

        switchArithmeticTrigonometric.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show Trigonometric Layout
                arithmeticLayout.setVisibility(View.GONE);
                trigonometricLayout.setVisibility(View.VISIBLE);
            } else {
                // Show Arithmetic Layout
                arithmeticLayout.setVisibility(View.VISIBLE);
                trigonometricLayout.setVisibility(View.GONE);
            }
        });

        switchDegreesRadians.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Radians
            } else {
                // Degrees
            }
        });

        setupArithmeticButtons();
        setupTrigonometricButtons();
    }

    private void setupArithmeticButtons() {
        findViewById(R.id.btnAdd).setOnClickListener(v -> performArithmeticOperation("add"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> performArithmeticOperation("subtract"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> performArithmeticOperation("multiply"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> performArithmeticOperation("divide"));
        findViewById(R.id.btnModulus).setOnClickListener(v -> performArithmeticOperation("modulus"));
    }

    private void performArithmeticOperation(String operation) {
        try {
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = Double.parseDouble(input2.getText().toString());
            double result = 0;

            switch (operation) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 == 0) {
                        showErrorDialog("Division by zero is not allowed.");
                        return;
                    }
                    result = num1 / num2;
                    break;
                case "modulus":
                    result = num1 % num2;
                    break;
            }
            arithmeticResult.setText("Result: " + result);
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid input. Please enter valid numbers.");
        }
    }

    private void setupTrigonometricButtons() {
        findViewById(R.id.btnSine).setOnClickListener(v -> performTrigonometricOperation("sine"));
        findViewById(R.id.btnCosine).setOnClickListener(v -> performTrigonometricOperation("cosine"));
        findViewById(R.id.btnTangent).setOnClickListener(v -> performTrigonometricOperation("tangent"));
        findViewById(R.id.btnInverseSine).setOnClickListener(v -> performTrigonometricOperation("inverseSine"));
        findViewById(R.id.btnInverseCosine).setOnClickListener(v -> performTrigonometricOperation("inverseCosine"));
        findViewById(R.id.btnInverseTangent).setOnClickListener(v -> performTrigonometricOperation("inverseTangent"));
        findViewById(R.id.btnReciprocalSine).setOnClickListener(v -> performTrigonometricOperation("reciprocalSine"));
        findViewById(R.id.btnReciprocalCosine).setOnClickListener(v -> performTrigonometricOperation("reciprocalCosine"));
        findViewById(R.id.btnReciprocalTangent).setOnClickListener(v -> performTrigonometricOperation("reciprocalTangent"));

        // Inverse Reciprocal Functions
        findViewById(R.id.btnInverseReciprocalSine).setOnClickListener(v -> performTrigonometricOperation("inverseReciprocalSine"));
        findViewById(R.id.btnInverseReciprocalCosine).setOnClickListener(v -> performTrigonometricOperation("inverseReciprocalCosine"));
        findViewById(R.id.btnInverseReciprocalTangent).setOnClickListener(v -> performTrigonometricOperation("inverseReciprocalTangent"));
    }

    private void performTrigonometricOperation(String operation) {
        try {
            double angle = Double.parseDouble(trigInput.getText().toString());
            boolean isRadians = switchDegreesRadians.isChecked();
            if (!isRadians) {
                angle = Math.toRadians(angle);
            }
            double result = 0;

            switch (operation) {
                case "sine":
                    result = Math.sin(angle);
                    break;
                case "cosine":
                    result = Math.cos(angle);
                    break;
                case "tangent":
                    result = Math.tan(angle);
                    break;
                case "inverseSine":
                    result = Math.asin(angle);
                    break;
                case "inverseCosine":
                    result = Math.acos(angle);
                    break;
                case "inverseTangent":
                    result = Math.atan(angle);
                    break;
                case "reciprocalSine":
                    if (Math.sin(angle) == 0) {
                        showErrorDialog("Reciprocal sine is undefined.");
                        return;
                    }
                    result = 1 / Math.sin(angle);
                    break;
                case "reciprocalCosine":
                    if (Math.cos(angle) == 0) {
                        showErrorDialog("Reciprocal cosine is undefined.");
                        return;
                    }
                    result = 1 / Math.cos(angle);
                    break;
                case "reciprocalTangent":
                    if (Math.tan(angle) == 0) {
                        showErrorDialog("Reciprocal tangent is undefined.");
                        return;
                    }
                    result = 1 / Math.tan(angle);
                    break;
                case "inverseReciprocalSine":
                    if (Math.sin(angle) == 0) {
                        showErrorDialog("Inverse reciprocal sine is undefined.");
                        return;
                    }
                    result = Math.asin(1 / Math.sin(angle));
                    break;
                case "inverseReciprocalCosine":
                    if (Math.cos(angle) == 0) {
                        showErrorDialog("Inverse reciprocal cosine is undefined.");
                        return;
                    }
                    result = Math.acos(1 / Math.cos(angle));
                    break;
                case "inverseReciprocalTangent":
                    if (Math.tan(angle) == 0) {
                        showErrorDialog("Inverse reciprocal tangent is undefined.");
                        return;
                    }
                    result = Math.atan(1 / Math.tan(angle));
                    break;
            }
            trigonometricResult.setText("Result: " + result);
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid input. Please enter a valid angle.");
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
