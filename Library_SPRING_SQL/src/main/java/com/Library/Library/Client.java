package com.Library.Library;

import jakarta.persistence.*;



@Entity
@Table(name="clients")
public class Client {

    public Client(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="surname",nullable = false)
    private String surname;

    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

}
