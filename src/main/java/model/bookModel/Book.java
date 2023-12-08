package model.bookModel;

public class Book {
    private final String name;
    private final BookStatus bookStatus;
    private final String author;
    private final String genre;
    private final int number;

    public Book(String name, BookStatus bookStatus,String author, String genre, int number) {
        this.name = name;
        this.bookStatus = bookStatus;
        this.author = author;
        this.genre = genre;
        this.number = number;
    }

    public String getAuthor(){
        return author;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public String getGenre() {
        return genre;
    }
}
