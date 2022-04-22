package com.learning.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static String BOOK_ID_KEY = "bookId";
    private TextView txtName,txtAuthor,txtDescription,txtPages;
    private Button btnAddToWantToRead,btnAddToAlreadyRead,btnAddToCurrentlyReading,btnAddToFavourites;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

//        // To get data from the recyclerview
//
//        String longDescription = "Peale begins by stating ten rules for “overcoming inadequacy attitudes and learning to practice faith”.[4] The rules include the following:\n" +
//                "\n" +
//                "Picture yourself succeeding.\n" +
//                "Think a positive thought to drown out a negative thought.\n" +
//                "Minimize obstacles.\n" +
//                "Do not attempt to copy others.\n" +
//                "Repeat “If God be for us, who can be against us?” ten times every day.\n" +
//                "Work with a counselor.\n" +
//                "Repeat “I can do all things through Christ which strengtheneth me” ten times every day.\n" +
//                "Develop a strong self-respect.\n" +
//                "Affirm that you are in God's hands.\n" +
//                "Believe that you receive power from God.";
//
//        Books book = new Books(1,"The Power Of the Positive Thinking","Norman Vincent Peale","https://www.whatyouwilllearn.com/wp-content/uploads/2020/07/power-of-positive-thinking.jpg",
//                300,"This Book could change your life",longDescription);

        Intent intent = getIntent();            //Intent.putEXtra  fetch by this method
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);  //THIS IS FOR SET DEFAULT VALUE OF BOOK_ID INCASE YOU FORGOT TO GIVE UNIQUE ID
            if(bookId != -1){
                Books incomingBook =Utills.getInstance().getBookByid(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyReadBook(incomingBook);                        //handleAlreadyReadBoooks Method for add book to already read books
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }

    }

    private void handleFavoriteBooks(final Books book){

        ArrayList<Books> favouriteBooks = Utills.getInstance().getFavouriteBooks();
        boolean existinFavoriteBooks = false;
        for(Books b : favouriteBooks){
            if(b.getId() == book.getId()){

                existinFavoriteBooks = true;
            }
        }
        if(existinFavoriteBooks){
            btnAddToFavourites.setEnabled(false);
        }else{
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utills.getInstance().addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity. this,FavoriteBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something Went Wrong , Try Another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void handleCurrentlyReadingBooks(final Books book){

        ArrayList<Books> currentlyReadingBooks = Utills.getInstance().getCurrentlyReadingBooks();    //create arraylist of utills stored already read books
        boolean existinCurrentlyReadingBooks = false;
        for(Books b : currentlyReadingBooks){                                                   //chek item is already exist in that arraylist or not
            if(b.getId() == book.getId()){
                existinCurrentlyReadingBooks = true;
            }
        }
        if(existinCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);                           //If book already available in already read book then button will be disable
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utills.getInstance().addToCurrentlyRead(book)){                     //If book is not available then add it through the addToAlreadyReadBook go back and check again button will be disable
                        Toast.makeText(BookActivity.this, "Book Added Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity. this,CurrentlyReadigBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something Went Wrong , Try Another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }


    private void handleWantToReadBooks(final Books book){

        ArrayList<Books> wantToReadBooks = Utills.getInstance().getWantToReadBooks();
        boolean existinWantToReadBooks = false;
        for(Books b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existinWantToReadBooks = true;
            }
        }
        if(existinWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utills.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity. this,WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something Went Wrong , Try Another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void handleAlreadyReadBook(final Books book){

        ArrayList<Books> alreadyReadBooks = Utills.getInstance().getAlreadyReadBooks();    //create arraylist of utills stored already read books
        boolean existInALreadyReadBooks = false;
        for(Books b : alreadyReadBooks){                                                   //chek item is already exist in that arraylist or not
            if(b.getId() == book.getId()){
                existInALreadyReadBooks = true;
            }
        }
        if(existInALreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);                           //If book already available in already read book then button will be disable
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utills.getInstance().addToAlreadyReadBook(book)){                     //If book is not available then add it through the addToAlreadyReadBook go back and check again button will be disable
                        Toast.makeText(BookActivity.this, "Book Added Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity. this,AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something Went Wrong , Try Another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }                                                                                                          /*-------After that i created AlreadyRead Activity ---------*/

    private void setData(Books book){
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getShortDesc());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }

    private void initViews(){

        txtName = findViewById(R.id.txtNameOfTheBook);
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtDescription = findViewById(R.id.longDescText);
        txtPages = findViewById(R.id.txtPageCount);

        btnAddToAlreadyRead = findViewById(R.id.btnAlreadyReadBook);
        btnAddToCurrentlyReading = findViewById(R.id.btnCurrentlyReadingBook);
        btnAddToWantToRead = findViewById(R.id.btnWantToRead);
        btnAddToFavourites = findViewById(R.id.btnAddFavourites);

        bookImage = findViewById(R.id.imageBook);

    }
}