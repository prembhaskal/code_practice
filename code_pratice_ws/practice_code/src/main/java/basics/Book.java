package basics;

import java.util.Objects;

public class Book {
    String name;
    Author author;

    String genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

class Author {

    String name;
    String firstname;
    String surname;
    int age;


    Gender gender;

    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return age == author.age && Objects.equals(name, author.name) && gender == author.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }
}

enum Gender {
    MALE("male"), FEMALE("female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.gender;
    }
}

class BookBuilder {

    Book book;

    BookBuilder() {
        book = new Book();
    }

    Book get() {
        return this.book;
    }

    BookBuilder withDetails(String name, String genre, String authorName, int authAge, Gender gender) {
        book.setName(name);
        book.setGenre(genre);
        Author author = new Author();
        author.setName(authorName);
        author.setAge(authAge);
        author.setGender(gender);
        book.setAuthor(author);
        return this;
    }
}
