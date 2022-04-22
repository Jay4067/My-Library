package com.learning.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        booksRecView = findViewById(R.id.booksRecView);
        adapter= new BookRecViewAdpater(this,"allBooks");

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));


//        ArrayList<Books> books = new ArrayList<>();
//        books.add(new Books(1,"The Power Of the Positive Thinking","Norman Vincent Peale","https://www.whatyouwilllearn.com/wp-content/uploads/2020/07/power-of-positive-thinking.jpg",300,"This Book could change your life","Long Description"));
//        books.add(new Books(2,"Rich Dad Poor Dad","Robert Kiyoski","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1448383923l/27917357._SY475_.jpg",285,"Rich Dad Poor Dad is a 1997 book written by Robert T. Kiyosaki and Sharon Lechter. ","Long Description"));
          adapter.setBooks(Utills.getInstance().getAllBooks());

//        adapter.setBooks(books);



    }
}