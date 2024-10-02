package com.santhos.gstrek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FileReturnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_return);

        // Firebase Database reference
        DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("GSTDetails");

// Get references to the input fields
        EditText businessNameField = findViewById(R.id.businessName);
        EditText gstinField = findViewById(R.id.gstin);
        EditText gstUsernameField = findViewById(R.id.gstUsername);
        EditText gstPasswordField = findViewById(R.id.gstPassword);
        EditText clientNameField = findViewById(R.id.clientName);
        EditText clientEmailField = findViewById(R.id.clientEmail);
        EditText clientMobileField = findViewById(R.id.clientMobile);
        EditText filingPriceField = findViewById(R.id.filingPrice);

// On click listener for the proceed button
        Button proceedButton = findViewById(R.id.proceedButton);
        proceedButton.setOnClickListener(v -> {
            // Get values from input fields
            Map<String, String> gstDetails = new HashMap<>();
            gstDetails.put("businessName",  businessNameField.getText().toString());
            gstDetails.put("gstin", gstinField.getText().toString());
            gstDetails.put("gstUsername", gstUsernameField.getText().toString());
            gstDetails.put("gstPassword", gstPasswordField.getText().toString());
            gstDetails.put("clientName", clientNameField.getText().toString());
            gstDetails.put("clientEmail", clientEmailField.getText().toString());
            gstDetails.put("clientMobile", clientMobileField.getText().toString());
            gstDetails.put("filingPrice", filingPriceField.getText().toString());



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
}