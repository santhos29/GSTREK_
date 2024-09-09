package com.santhos.gstrek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText etOriginalPrice, etGSTRate;
    private Button btnCalculateGST;
    private TextView tvGSTAmount, tvFinalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator); // Assuming this is your layout file

        // Initialize the views
        etOriginalPrice = findViewById(R.id.etOriginalPrice);
        etGSTRate = findViewById(R.id.etGSTRate);
        btnCalculateGST = findViewById(R.id.btnCalculateGST);
        tvGSTAmount = findViewById(R.id.tvGSTAmount);
        tvFinalPrice = findViewById(R.id.tvFinalPrice);

        // Set an OnClickListener for the calculate button
        btnCalculateGST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGST();
            }
        });
    }

    private void calculateGST() {
        String originalPriceStr = etOriginalPrice.getText().toString();
        String gstRateStr = etGSTRate.getText().toString();

        // Validate input
        if (originalPriceStr.isEmpty() || gstRateStr.isEmpty()) {
            Toast.makeText(this, "Please enter both Original Price and GST Rate", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse the input values
            double originalPrice = Double.parseDouble(originalPriceStr);
            double gstRate = Double.parseDouble(gstRateStr);

            // Calculate GST amount and final price
            double gstAmount = (originalPrice * gstRate) / 100;
            double finalPrice = originalPrice + gstAmount;

            // Display the results
            tvGSTAmount.setText("GST Amount: ₹" + String.format("%.2f", gstAmount));
            tvFinalPrice.setText("Final Price: ₹" + String.format("%.2f", finalPrice));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }
}