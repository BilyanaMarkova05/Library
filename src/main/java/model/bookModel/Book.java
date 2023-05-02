package model.bookModel;

public class Book {
    private final String name;
    private final BookStatus bookStatus;

    public Book(String name, BookStatus bookStatus) {
        this.name = name;
        this.bookStatus = bookStatus;
    }

    public String getName() {
        return name;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }
}
