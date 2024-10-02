package com.santhos.gstrek;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import  android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.santhos.gstrek.databinding.ActivityHomeBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    DrawerLayout drawerLayout;
    ImageView buttonHamburgerToggle;
    NavigationView navigationView;

    BottomNavigationView nav;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri photoUri;


    CardView calculatorCard;
    public ImageView logout;

    CardView logoutCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        //buttonHamburgerToggle = findViewById(R.id.HamburgerToggle);
        navigationView = findViewById(R.id.navigationView);
        //calculatorCard = findViewById(R.id.calculatorCard);

        nav = findViewById(R.id.bottomNavView);
        replacefragment(new HomeFragment());

        nav.setOnItemSelectedListener(item -> {

           int name = item.getItemId();
            if(name == R.id.cameraNav) {

                openCamera();

            } else if (name == R.id.logoutNav) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (name == R.id.dashboardNav) {
               //replacefragment(new DisplayFragment());
                Intent intent = new Intent(HomeActivity.this, DisplayActivity.class);
                startActivity(intent);

            } else if (name == R.id.homeNav) {
                replacefragment(new HomeFragment());
            }

            return true;
        });

       /* buttonHamburgerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });*/

        View headerView = navigationView.getHeaderView(0);




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



    }
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Error creating image file", Toast.LENGTH_SHORT).show();
            }
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, "com.santhos.gstrek.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Nullable
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // The image is captured and saved, now process it for uploading
            prepareImageForUpload(photoUri);
        }
    }

    private void prepareImageForUpload(Uri imageUri) {
        try {
            // Convert the captured image to a file or byte array
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            File imageFile = new File(getCacheDir(), "final_image.jpg");

            FileOutputStream outStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

            // You can now upload the image file to your API
            uploadImageToApi(imageFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadImageToApi(File imageFile) {
        // Implement your logic to upload the image to your API
        Toast.makeText(this, "Image ready for upload: " + imageFile.getPath(), Toast.LENGTH_SHORT).show();
    }

    private File createImageFile() throws IOException {
        // Create a file for the captured image
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        return image;
    }

    private void replacefragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameview,fragment);
        fragmentTransaction.commit();
    }

}
