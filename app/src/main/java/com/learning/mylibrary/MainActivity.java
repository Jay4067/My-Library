package com.learning.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBook,btnCurrentlyReading,btnAlreadyread,btnFavourites,btnWishlist,btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);     //use intent to naviagte to the next activity
                startActivity(intent);                                                               //TO start activity
            }
        });

        btnAlreadyread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CurrentlyReadigBookActivity.class);
                startActivity(intent);
            }
        });

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WantToReadActivity.class);
                startActivity(intent);
            }
        });

        btnFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FavoriteBookActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Designed and Developed with love by Jay Patoliya See more at my Github account \n"+
                        "\n check my Github Account for more : ");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO : add github linkup
                        Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });

        Utills.getInstance();
    }

    private void initViews(){
        btnAllBook =findViewById(R.id.btnAllBook);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReadHome);
        btnAlreadyread = findViewById(R.id.btnAlreadyRead);
        btnFavourites = findViewById(R.id.btnFavourites);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnAbout = findViewById(R.id.btnAbout);
    }
}