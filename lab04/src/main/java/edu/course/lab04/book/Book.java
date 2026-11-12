package edu.course.lab04.book;

import java.util.Objects;

public final class Book {
    private String isbn;
    private String title;

    public Book(String isbn, String title){
        if (isbn == null || isbn.isBlank()){
            throw new IllegalArgumentException("isbn must not be null or blank");
        }

        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("title must not be null or blank");
        }

        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        return isbn.equals(((Book) o).isbn);
    }

    @Override
    public int hashCode(){
        return Objects.hash(isbn);
    }

    @Override
    public String toString(){
        return "Book{isbn='" + isbn + "', title='" + title + "'}";
    }
}
