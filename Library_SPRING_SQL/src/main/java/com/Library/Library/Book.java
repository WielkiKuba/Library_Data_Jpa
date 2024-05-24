package com.Library.Library;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    public Book(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="free",nullable = false)
    private int free;

    @Column(name="author",nullable = false)
    private String author;

    @Column(name="year",nullable = false)
    private String year;

    @Column(name="publisher",nullable = false)
    private String publisher;

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFree(int free){
        this.free = free;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setYear(String year){
        this.year = year;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getFree(){
        return free;
    }
    public String getAuthor(){
        return author;
    }
    public String getYear(){
        return year;
    }
    public String getPublisher(){
        return publisher;
    }
}
