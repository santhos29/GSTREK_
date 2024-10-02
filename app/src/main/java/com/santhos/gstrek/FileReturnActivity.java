package com.santhos.gstrek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReturnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_return);

        // Firebase Database reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("GSTDetails");

        // Get references to the input fields
        EditText businessNameField = findViewById(R.id.businessName);
        EditText gstinField = findViewById(R.id.gstin);
        EditText gstUsernameField = findViewById(R.id.gstUsername);
        EditText gstPasswordField = findViewById(R.id.gstPassword);
        EditText clientNameField = findViewById(R.id.clientName);
        EditText clientEmailField = findViewById(R.id.clientEmail);
        EditText clientMobileField = findViewById(R.id.clientMobile);
        EditText filingDateField = findViewById(R.id.filingPrice);  // Date field

        // Set up DatePicker for the filingDateField
        filingDateField.setOnClickListener(v -> {
            // Get the current date
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    FileReturnActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        // Format the selected date and set it to the EditText
                        String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        filingDateField.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        // On click listener for the proceed button
        Button proceedButton = findViewById(R.id.proceedButton);
        proceedButton.setOnClickListener(v -> {

            // Get values from input fields
            String businessName = businessNameField.getText().toString();
            String gstin = gstinField.getText().toString();
            String gstUsername = gstUsernameField.getText().toString();
            String gstPassword = gstPasswordField.getText().toString();
            String clientName = clientNameField.getText().toString();
            String clientEmail = clientEmailField.getText().toString();
            String clientMobile = clientMobileField.getText().toString();
            String filingDate = filingDateField.getText().toString();

            // Validate GSTIN
            if (!isValidGSTIN(gstin)) {
                Toast.makeText(this, "Invalid GSTIN Format", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate password
            if (!isValidPassword(gstPassword)) {
                Toast.makeText(this, "Enter 4-digit Password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate mobile number
            if (!isValidMobile(clientMobile)) {
                Toast.makeText(this, "Mobile number must be 10 digits", Toast.LENGTH_LONG).show();
                return;
            }

            // Validate that no field is empty
            if (businessName.isEmpty() || gstin.isEmpty() || gstUsername.isEmpty() || gstPassword.isEmpty() ||
                    clientName.isEmpty() || clientEmail.isEmpty() || clientMobile.isEmpty() || filingDate.isEmpty()) {
                // Show a toast message if any field is empty
                Toast.makeText(this, "Fill all details to proceed", Toast.LENGTH_SHORT).show();
                return; // Stop further execution if validation fails
            }

            // Save the data if all fields are filled
            Map<String, String> gstDetails = new HashMap<>();
            gstDetails.put("businessName", businessName);
            gstDetails.put("gstin", gstin);
            gstDetails.put("gstUsername", gstUsername);
            gstDetails.put("gstPassword", gstPassword);
            gstDetails.put("clientName", clientName);
            gstDetails.put("clientEmail", clientEmail);
            gstDetails.put("clientMobile", clientMobile);
            gstDetails.put("filingDate", filingDate);

            // Save the data to Firebase
            String clientId = databaseReference.push().getKey(); // Generate unique ID for each client
            assert clientId != null;
            // Push data to Firebase
            databaseReference.push().setValue(gstDetails).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FileReturnActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // GSTIN validation
    private boolean isValidGSTIN(String gstin) {
        String gstinPattern = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{3}$";
        Pattern pattern = Pattern.compile(gstinPattern);
        Matcher matcher = pattern.matcher(gstin);
        return matcher.matches();
    }

    // Password validation (4 digits)
    private boolean isValidPassword(String gstPassword) {
        return gstPassword.matches("\\d{4}");
    }

    // Mobile number validation (10 digits)
    private boolean isValidMobile(String clientMobile) {
        return clientMobile.matches("\\d{10}");
    }
}