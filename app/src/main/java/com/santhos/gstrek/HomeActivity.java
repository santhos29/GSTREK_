package com.santhos.gstrek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import  android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView buttonHamburgerToggle;
    NavigationView navigationView;

    CardView calculatorCard;
    public ImageView logout;

    CardView logoutCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonHamburgerToggle = findViewById(R.id.HamburgerToggle);
        navigationView = findViewById(R.id.navigationView);
        calculatorCard = findViewById(R.id.calculatorCard);


        buttonHamburgerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        View headerView = navigationView.getHeaderView(0);
        ImageView userImage = headerView.findViewById(R.id.userImage);
        TextView texUsername = headerView.findViewById(R.id.textUsername);

        logout = (ImageView) findViewById(R.id.cardImage4);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this , texUsername.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if(itemId == R.id.navMenu){
                    Toast.makeText(HomeActivity.this,"Menu Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navDashboard){
                    Toast.makeText(HomeActivity.this,"Dashboard Clicked" ,Toast.LENGTH_SHORT).show();
                    Toast.makeText(HomeActivity.this,"Sign In Clicked" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
                    startActivity(intent);

                }
                if(itemId == R.id.navBank){
                    Toast.makeText(HomeActivity.this,"Bank Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navSignIn){
                    Toast.makeText(HomeActivity.this,"Sign In Clicked" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                if(itemId == R.id.navHistory){
                    Toast.makeText(HomeActivity.this,"History Clicked" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, CalculatorActivity.class);
                    startActivity(intent);


                }
                if(itemId == R.id.navProfile){
                    Toast.makeText(HomeActivity.this,"Profile Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navGst){
                    Toast.makeText(HomeActivity.this,"File GST Returns Clicked" ,Toast.LENGTH_SHORT).show();
                    Toast.makeText(HomeActivity.this,"Logout Clicked" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, FileReturnActivity.class);
                    startActivity(intent);


                }
                if(itemId == R.id.navPaymentDash){
                    Toast.makeText(HomeActivity.this,"Payment Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navContact){
                    Toast.makeText(HomeActivity.this,"Contact Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navAbout){
                    Toast.makeText(HomeActivity.this,"About Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navAbout){
                    Toast.makeText(HomeActivity.this,"About Clicked" ,Toast.LENGTH_SHORT).show();

                }
                if(itemId == R.id.navLogout){
                    Toast.makeText(HomeActivity.this,"Logout Clicked" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, SplashScreenActivity.class);
                    startActivity(intent);

                }
                drawerLayout.close();



                return false;
            }
        });

        calculatorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });









    }


}
