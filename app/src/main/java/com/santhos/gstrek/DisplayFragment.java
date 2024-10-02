package com.santhos.gstrek;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context thiscontext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(String param1, String param2) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private TableLayout tableLayout;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        thiscontext = container.getContext();

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Clients");

        // Table layout where data will be displayed
        tableLayout = view.findViewById(R.id.detailsTable1);

        // Fetch data from Firebase
        fetchClientData();
        return inflater.inflate(R.layout.fragment_display, container, false);
    }
    private void fetchClientData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear previous table rows
                tableLayout.removeAllViews();

                // Loop through Firebase data and add rows to the table
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClientData clientData = snapshot.getValue(ClientData.class);
                    if (clientData != null) {
                        addTableRow(clientData);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                Toast.makeText(thiscontext, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTableRow(ClientData clientData) {
        // Create a new table row
        TableRow tableRow = new TableRow(thiscontext);

        // Create TextViews for each data field
        TextView businessName = new TextView(thiscontext);
        businessName.setText(clientData.getBusinessName());

        TextView gstin = new TextView(thiscontext);
        gstin.setText(clientData.getGstin());

        TextView gstUsername = new TextView(thiscontext);
        gstUsername.setText(clientData.getGstUsername());

        TextView gstPassword = new TextView(thiscontext);
        gstPassword.setText(clientData.getGstPassword());

        TextView clientName = new TextView(thiscontext);
        clientName.setText(clientData.getClientName());

        TextView clientEmail = new TextView(thiscontext);
        clientEmail.setText(clientData.getClientEmail());

        TextView clientMobile = new TextView(thiscontext);
        clientMobile.setText(clientData.getClientMobile());

        TextView filingPrice = new TextView(thiscontext);
        filingPrice.setText(clientData.getFilingPrice());

        // Add the TextViews to the TableRow
        tableRow.addView(businessName);
        tableRow.addView(gstin);
        tableRow.addView(gstUsername);
        tableRow.addView(gstPassword);
        tableRow.addView(clientName);
        tableRow.addView(clientEmail);
        tableRow.addView(clientMobile);
        tableRow.addView(filingPrice);

        Log.d("rer","rer");

        // Add the TableRow to the TableLayout
        tableLayout.addView(tableRow);
    }

}