package model.bookModel;

public class Book {
    private final String name;
    private final BookStatus bookStatus;
    private final String genre;

    public Book(String name, BookStatus bookStatus, String genre) {
        this.name = name;
        this.bookStatus = bookStatus;
        this.genre = genre;
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
