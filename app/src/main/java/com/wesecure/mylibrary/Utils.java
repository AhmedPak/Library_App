package com.wesecure.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {


    private static final String ALL_BOOKS_KEY = "all books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;


    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (getAllBooks() == null) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }

        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }
        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }
        if (getFavoriteBooks() == null) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.apply();
        }
    }

    private void initData () {
        //TODO: add initial data
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "1Q84", "Haruki Murakami",
                1350, "https://upload.wikimedia.org/wikipedia/en/b/b9/1Q84bookcover.jpg",
                "A dystopian Novel", "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a \"real\" one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world"));
        books.add(new Book(2, "Python Notes for Professionals", "GoalKicker", 700,"https://miro.medium.com/max/350/1*3EYug6a-vKg5FxDvqY3dbw.png",
                "Professional hints and tricks", "Free book with over 700 pages for IT professionals to learn and master python. "));
        books.add(new Book(3, "The Thristle And The Drone","Akbar Ahmed", 440, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1356473553l/15864058.jpg","How America's war on terror became a global war on tribal Islam","In the wake of the 9/11 attacks, the United States declared war on terrorism. More than ten years later, the results are decidedly mixed. Here world-renowned author, diplomat, and scholar Akbar Ahmed reveals an important yet largely ignored result of this war: in many nations it has exacerbated the already broken relationship between central governments and the largely rural Muslim tribal societies on the peripheries of both Muslim and non-Muslim nations. The center and the periphery are engaged in a mutually destructive civil war across the globe, a conflict that has been intensified by the war on terror.\n" +
                "\n" +
                "Conflicts between governments and tribal societies predate the war on terror in many regions, from South Asia to the Middle East to North Africa, pitting those in the centers of power against those who live in the outlying provinces. Akbar Ahmed's unique study demonstrates that this conflict between the center and the periphery has entered a new and dangerous stage with U.S. involvement after 9/11 and the deployment of drones, in the hunt for al Qaeda, threatening the very existence of many tribal societies.\n" +
                "\n" +
                "American firepower and its vast anti-terror network have turned the war on terror into a global war on tribal Islam. And too often the victims are innocent children at school, women in their homes, workers simply trying to earn a living, and worshipers in their mosques. Battered by military attacks or drone strikes one day and suicide bombers the next, the tribes bemoan, \"Every day is like 9/11 for us.\"\n" +
                "\n" +
                "In The Thistle and the Drone, the third volume in Ahmed's groundbreaking trilogy examining relations between America and the Muslim world, the author draws on forty case studies representing the global span of Islam to demonstrate how the U.S. has become involved directly or indirectly in each of these societies. The study provides the social and historical context necessary to understand how both central governments and tribal societies have become embroiled in America's war. Beginning with Waziristan and expanding to societies in Central Asia, the Middle East, North Africa, and elsewhere, Ahmed offers a fresh approach to the conflicts studied and presents an unprecedented paradigm for understanding and winning the war on terror."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.apply();
    }

    public static Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (books != null){
            for (Book b: books){
                if (b.getId() == id){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyReadBooks (Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantReadBooks (Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavoriteBooks (Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadyBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.apply();
                return true;
            }
        }
        return false;    }

    public boolean removeFromCurrentlyReadingBooks (Book book) {
        ArrayList<Book>  books = getCurrentlyReadingBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavoriteBooks (Book book) {
        ArrayList<Book>  books = getFavoriteBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromAlreadyReadBooks (Book book) {
        ArrayList<Book>  books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToReadBooks(Book book) {
        ArrayList<Book>  books = getWantToReadBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.apply();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
