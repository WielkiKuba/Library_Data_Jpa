package com.Library.Library;

import jakarta.persistence.*;

@Entity
@Table(name="rents")
public class Rent {
    public Rent(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client",nullable = false)
    private int client;

    @Column(name = "book",nullable = false)
    private int book;

    public long getId(){
        return id;
    }

    public int getClient(){
        return client;
    }
    public int getBook(){
        return book;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setClient(int client){
        this.client = client;
    }

    public void setBook(int book){
        this.book = book;
    }
}
