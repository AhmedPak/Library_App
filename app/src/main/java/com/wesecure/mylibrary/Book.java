package com.wesecure.mylibrary;

public class Book {

    private int id;
    private String name;
    private String author;
    private int pages;
    private String imageURL;
    private String shortDisc;
    private String longDisc;
    private boolean isExpanded;

    public Book(int id, String name, String author, int pages, String imageURL, String shortDisc, String longDisc) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.imageURL = imageURL;
        this.shortDisc = shortDisc;
        this.longDisc = longDisc;
        isExpanded = false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShortDisc() {
        return shortDisc;
    }

    public void setShortDisc(String shortDisc) {
        this.shortDisc = shortDisc;
    }

    public String getLongDisc() {
        return longDisc;
    }

    public void setLongDisc(String longDisc) {
        this.longDisc = longDisc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", imageURL='" + imageURL + '\'' +
                ", shortDisc='" + shortDisc + '\'' +
                ", longDisc='" + longDisc + '\'' +
                '}';
    }
}
