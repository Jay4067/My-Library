package com.learning.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.gson.TypeAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.xml.validation.TypeInfoProvider;

//Create singleton pattern to store temporary data using static method

public class Utills {

    private static Utills instance;

    private static ArrayList<Books> allBooks;
    private static ArrayList<Books> alreadyReadBooks;
    private static ArrayList<Books> wantToReadBooks;
    private static ArrayList<Books> currentlyReadingBooks;
    private static ArrayList<Books> favouriteBooks;

    private Utills() {

        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if(null == favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }

    }

    private void initData(){

        //TODO :TO add Initial Data


        allBooks.add(new Books(1,"The Power Of the Positive Thinking","Norman Vincent Peale","https://www.whatyouwilllearn.com/wp-content/uploads/2020/07/power-of-positive-thinking.jpg",300,"This Book could change your life","Long Description"));
        allBooks.add(new Books(2,"Rich Dad Poor Dad","Robert Kiyoski","https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1448383923l/27917357._SY475_.jpg",285,"Rich Dad Poor Dad is a 1997 book written by Robert T. Kiyosaki and Sharon Lechter. ","Long Description"));


    }

    public static  Utills getInstance(){
        if(null != instance){
            return instance;
        }
        else{
            instance = new Utills();
            return instance;
        }
    }

    public  ArrayList<Books> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Books> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Books> getWantToReadBooks() {
        return wantToReadBooks;
    }




    public static ArrayList<Books> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Books> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Books getBookByid(int id){                                                               //got error in this currently reading method for giving same id to two nutton one in home screen name same as this so get null pointer exception for that, change it within the current file for both buttons
        for(Books b : allBooks){
            if(b.getId() == id){
                return b;
            }

        }
        return null;
    }

    public boolean addToAlreadyReadBook(Books book){
        return alreadyReadBooks.add(book);      //Use Return type boolean because we want to clarify that book is added or not for more details click ctrl+.add
    }

    public boolean addToWantToRead(Books book){
        return wantToReadBooks.add(book);      //Use Return type boolean because we want to clarify that book is added or not for more details click ctrl+.add
    }

    public boolean addToCurrentlyRead(Books book){
        return currentlyReadingBooks.add(book);
    }

    public boolean addToFavoriteBooks(Books book){
        return favouriteBooks.add(book);      //Use Return type boolean because we want to clarify that book is added or not for more details click ctrl+.add
    }

    public boolean removeFromAlreadyBook(Books book){
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToRead(Books book){
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyRead(Books book){
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromFavorites(Books book){
        return favouriteBooks.remove(book);
    }
}
