package com.learning.mylibrary;

import static com.learning.mylibrary.BookActivity.BOOK_ID_KEY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//first create recyclerView Activity layout page then list_item_book
//then define recyclerView on the PAge Activity Page
//Create RecyclerAdapter class
//create sub class ViewHolder extended by RecyclerView.View.holder
//define your CardView component in ViewHolder class
//EXtend your recViewAdapter class by RecyclerView.Adapter<'yourViewAdapter.ViewHolder'>
//override super class method
//create arraylist to retrieve data[implement set method to chaneg data]

public class BookRecViewAdpater extends RecyclerView.Adapter<BookRecViewAdpater.ViewHolder> {
    private static  final String TAG = "BookRecViewAdapter";

    private ArrayList<Books> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BookRecViewAdpater(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG,"onBindViewHolder : called");
        holder.txtName.setText(books.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, books.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,BookActivity.class);         //direct to BookActivity
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());       //putextra to get each books attributes which help getIntent in bookActivity class
                context.startActivity(intent);                                  //This is not activity so we cannot direct pass start activity method
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());



        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);    //use for animation expand process
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if(parentActivity.equals("allBooks")) {                     //If You are in all book recyclerView
                holder.btnDelete.setVisibility(View.GONE);              //you're not gonna see visible button
            }else if(parentActivity.equals("alreadyRead")){             //You can use switch case statement also
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);             //Create Alert Dialog before delete Yes/No
                        builder.setMessage("Are you sure want to delete " + books.get(position).getName() + " ? ");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {                    //Dialog Interface OnCLickListener
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utills.getInstance().removeFromAlreadyBook(books.get(position))){                        //call removeFromAlreadyBook method from utills class
                                    Toast.makeText(context, "Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {     //for negative you have to only create onclick method
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                     }
                });

            }else if(parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);             //Create Alert Dialog before delete Yes/No
                        builder.setMessage("Are you sure want to delete " + books.get(position).getName() + " ? ");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {                    //Dialog Interface OnCLickListener
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utills.getInstance().removeFromWantToRead(books.get(position))){                        //call removeFromWantToRead method from utills class
                                    Toast.makeText(context, "Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {     //for negative you have to only create onclick method
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                    }
                });

            }else if(parentActivity.equals("currentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);             //Create Alert Dialog before delete Yes/No
                        builder.setMessage("Are you sure want to delete " + books.get(position).getName() + " ? ");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {                    //Dialog Interface OnCLickListener
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utills.getInstance().removeFromCurrentlyRead(books.get(position))){                        //call removeFromCurrentlyRead method from utills class
                                    Toast.makeText(context, "Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {     //for negative you have to only create onclick method
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                    }
                });
            }else if(parentActivity.equals("favoriteBooks")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);             //Create Alert Dialog before delete Yes/No
                        builder.setMessage("Are you sure want to delete " + books.get(position).getName() + " ? ");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {                    //Dialog Interface OnCLickListener
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utills.getInstance().removeFromFavorites(books.get(position))){                        //call removeFromFavorites method from utills class
                                    Toast.makeText(context, "Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {     //for negative you have to only create onclick method
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.create().show();

                    }
                });
            }

        }else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Books> books) {
        this.books=books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;

        private RelativeLayout expandedRelLayout;
        private ImageView upArrow,downArrow;
        private TextView txtAuthor,txtDescription;

        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);

            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            txtAuthor = itemView.findViewById(R.id.author);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);

            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {       //for Expand cardView
                @Override
                public void onClick(View view) {
                    Books book = books.get(getAbsoluteAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAbsoluteAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Books book = books.get(getAbsoluteAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAbsoluteAdapterPosition());
                }
            });
        }


    }
}
