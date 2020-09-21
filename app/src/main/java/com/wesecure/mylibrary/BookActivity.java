package com.wesecure.mylibrary;

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

    public static final String BOOK_ID_KEY = "bookId";

    private TextView  txtBookName, txtAuthorName, txtPages, txtDescDetail, txtLongDesc;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews ();

//        String longDesc = "Please feel free to share this PDF with anyone for free, latest version of this book can be downloaded from: https://goalkicker.com/PythonBook"
//                +"\n"+"This Python® Notes for Professionals book is compiled from Stack Overﬂow Documentation, the content is written by the beautiful people at Stack Overﬂow. Text content is released under Creative Commons BY-SA, see credits at the end of this book whom contributed to the various chapters. Images may be copyright of their respective owners unless otherwise specified."
//                +"\n"+"This is an unofficial free book created for educational purposes and is not aﬃliated with oﬃcial Python® group(s) or company(s) nor Stack Overﬂow. All trademarks and registered trademarks are the property of their respective company owners."
//                +"\n"+"The information presented in this book is not guaranteed to be correct nor accurate, use at your own risk"
//                +"\n"+"Please send feedback and corrections to web@petercv.com";
//
//
//        //TODO: Get the data from recycler view in here
//        Book book = new Book(2, "Python Notes for Professionals", "GoalKicker", 700,"https://miro.medium.com/max/350/1*3EYug6a-vKg5FxDvqY3dbw.png",
//                "Professional hints and tricks", longDesc);

        Intent intent = getIntent();
        if (intent != null) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (incomingBook != null){
                    setData(incomingBook);
                    handleAlreadyReadBooks (incomingBook);
                    handleWantToReadBooks (incomingBook);
                    handleCurrentlyReadyBooks (incomingBook);
                    handleFavoriteBooks (incomingBook);
                }
            }
        }
    }

    private void   handleWantToReadBooks (final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existsInWantReadBooks = false;

        for (Book b: wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existsInWantReadBooks = true;
            }
        }
        if (existsInWantReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        }else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToWantReadBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Successfully added", Toast.LENGTH_SHORT).show();
                        // navigate the user to already read books
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    }else   {
                        Toast.makeText(BookActivity.this, "Couldn't add the book to the list, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadyBooks (final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existsInCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existsInCurrentlyReadingBooks = true;
            }
        }
        if (existsInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        }else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReadyBooks (book)) {
                        Toast.makeText(BookActivity.this, "Book Successfully added", Toast.LENGTH_SHORT).show();
                        // navigate the user to already read books
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);

                    }else   {
                        Toast.makeText(BookActivity.this, "Couldn't add the book to the list, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handleFavoriteBooks (final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

        boolean exitsInFavoriteBooks = false;

        for (Book b: favoriteBooks) {
            if (b.getId() == book.getId()) {
                exitsInFavoriteBooks = true;
            }
        }
        if (exitsInFavoriteBooks) {
            btnAddToFavorite.setEnabled(false);
        }else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Successfully added", Toast.LENGTH_SHORT).show();
                        // navigate the user to already read books
                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);

                    }else   {
                        Toast.makeText(BookActivity.this, "Couldn't add the book to the list, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyReadBooks(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existsInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existsInAlreadyReadBooks = true;
            }
        }
        if (existsInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyReadBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Successfully added", Toast.LENGTH_SHORT).show();
                        // navigate the user to already read books
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);

                    }else   {
                        Toast.makeText(BookActivity.this, "Couldn't add the book to the list, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData (Book book) {
        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescDetail.setText(book.getShortDisc());
        txtLongDesc.setText(book.getLongDisc());
        Glide.with(this)
                .asBitmap().load(book.getImageURL())
                .into(imgBook);
    }

    private void initViews () {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtPages = findViewById(R.id.txtPages);
        txtDescDetail = findViewById(R.id.txtDescDetail);
        txtLongDesc = findViewById(R.id.txtLongDesc);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorites);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);

        imgBook = findViewById(R.id.imgBook);
    }
}