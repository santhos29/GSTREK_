package com.santhos.gstrek;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("GSTDetails");

// Reference to TableLayout in your XML
        TableLayout tableLayout = findViewById(R.id.detailsTable3);

// Function to add headers


// Listen for changes or fetch data once
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear table except header
                //tableLayout.removeViews(1, tableLayout.getChildCount() - 1);

                // Add headers to the table (only once)
                addTableHeaders();

                // Iterate through all GST entries in Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, String> gstDetails = (Map<String, String>) snapshot.getValue();

                    // Create a new TableRow for each entry
                    TableRow tableRow = new TableRow(getApplicationContext());

                    // Set LayoutParams for each row
                    tableRow.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    // Add each detail as a TextView in the TableRow
                    TextView businessName = new TextView(getApplicationContext());
                    businessName.setText(gstDetails.get("businessName"));
                    businessName.setPadding(8, 8, 8, 8);
                    tableRow.addView(businessName);

                    TextView gstin = new TextView(getApplicationContext());
                    gstin.setText(gstDetails.get("gstin"));
                    gstin.setPadding(8, 8, 8, 8);
                    tableRow.addView(gstin);

                    TextView gstUsername = new TextView(getApplicationContext());
                    gstUsername.setText(gstDetails.get("gstUsername"));
                    gstUsername.setPadding(8, 8, 8, 8);
                    tableRow.addView(gstUsername);

                    TextView gstPassword = new TextView(getApplicationContext());
                    gstPassword.setText(gstDetails.get("gstPassword"));
                    gstPassword.setPadding(8, 8, 8, 8);
                    tableRow.addView(gstPassword);

                    TextView clientName = new TextView(getApplicationContext());
                    clientName.setText(gstDetails.get("clientName"));
                    clientName.setPadding(8, 8, 8, 8);
                    tableRow.addView(clientName);

                    TextView clientEmail = new TextView(getApplicationContext());
                    clientEmail.setText(gstDetails.get("clientEmail"));
                    clientEmail.setPadding(8, 8, 8, 8);
                    tableRow.addView(clientEmail);

                    TextView clientMobile = new TextView(getApplicationContext());
                    clientMobile.setText(gstDetails.get("clientMobile"));
                    clientMobile.setPadding(8, 8, 8, 8);
                    tableRow.addView(clientMobile);

                    TextView filingPrice = new TextView(getApplicationContext());
                    filingPrice.setText(gstDetails.get("filingPrice"));
                    filingPrice.setPadding(8, 8, 8, 8);
                    tableRow.addView(filingPrice);

                    // Add action spinner (optional, depending on your need)
                    Spinner actionSpinner = new Spinner(getApplicationContext());
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                            getApplicationContext(), R.array.action_array, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    actionSpinner.setAdapter(adapter);
                    tableRow.addView(actionSpinner);

                    // Finally, add the TableRow to the TableLayout
                    tableLayout.addView(tableRow);
                }
            }
            private void addTableHeaders() {
                // Create a new TableRow for the header
                TableRow headerRow = new TableRow(getApplicationContext());

                // Set LayoutParams for the header row
                headerRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                // Add each header title as a TextView
                String[] headers = {
                        "Business Name", "GSTIN", "GST Username", "GST Password", "Client's Name",
                        "Client Email", "Client Mob.no", "Filing Price", "Action"
                };

                // Loop to add TextViews for each header in the row
                for (String headerTitle : headers) {
                    TextView headerTextView = new TextView(getApplicationContext());
                    headerTextView.setText(headerTitle);
                    headerTextView.setPadding(8, 8, 8, 8);
                    headerTextView.setGravity(Gravity.CENTER);
                    headerTextView.setBackgroundColor(Color.parseColor("#b2ebf2"));
                    // headerTextView.setTextStyle(Typeface.BOLD);
                    headerRow.addView(headerTextView);
                }

                // Finally, add the header row to the TableLayout
                tableLayout.addView(headerRow);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

    }
}