package com.Library.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WebController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    RentService rentService;

    @GetMapping("/bookList")
    public List<Book> bookList(){
        return bookService.bookList();
    }

    @PostMapping("/bookSearch/id/{id}")
    public Optional<Book> search(@PathVariable long id){
        return bookService.bookList(id);
    }

    @PostMapping("/bookSearch/{method}/{value}")
    public ResponseEntity<List<Book>> bookSearch(@PathVariable String method,@PathVariable String value){
        List<Book> listOfBooks;
        if((method.toUpperCase()).equals("NAME")){
            listOfBooks = bookService.bookListByName(value);
            return ResponseEntity.ok(listOfBooks);
        } else if (method.toUpperCase().equals("AUTHOR")) {
            listOfBooks = bookService.bookListByAuthor(value);
            return ResponseEntity.ok(listOfBooks);
        }
        else if(method.toUpperCase().equals("FREE")){
            try{
                int intValue = Integer.parseInt(value);
                listOfBooks = bookService.bookListByFree(intValue);
                return ResponseEntity.ok(listOfBooks);
            }catch (NumberFormatException e){
                return ResponseEntity.notFound().build();
            }
        }
        else if(method.toUpperCase().equals("USERDATA")){
            String[] userData = value.split(" ");
            String name = "";
            String surname = "";
            if(userData.length!=0){
                name = userData[0];
                surname = userData[1];
            }
            long userId = userIdByUserData(name,surname);
            try{
                List<Book> listOfBook = bookService.bookListByFree((int)userId);
                return ResponseEntity.ok(listOfBook);
            }catch (NumberFormatException e){
                return ResponseEntity.notFound().build();
            }
        }
        return null;
    }
    @PostMapping("/clientSearch/{userData}")
    public ResponseEntity<List<Client>> clientSearch(@PathVariable String userData){
        String[] data = userData.split(" ");
        List<Client> user;
        if(data.length == 2){
            user = userService.findByUserData(data[0],data[1]);
            if(user.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(user);
            }
        }else if(data.length==1){
            try{
                long userId = Long.parseLong(data[0]);
                user = userService.findById(userId);
                if(user==null){
                    return ResponseEntity.notFound().build();
                }
                else{
                    return ResponseEntity.ok(user);
                }
            }catch (NumberFormatException e){
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/clientList")
    public List<Client> clientList(){
        return userService.findAll();
    }

    @GetMapping("/rentList")
    public List<Rent> rentList(){
        return rentService.rentList();
    }

    @PostMapping("/userManagement/{option}/{userData}")
    public void addUser(@PathVariable String option,@PathVariable String userData){
        String[] data = userData.split(" ");
        if(data.length==2){
            String name = data[0];
            String surname = data[1];
            if(option.toUpperCase().equals("ADD")){
                userService.addUser(name,surname);
            }
            else if(option.toUpperCase().equals("DELETE")){
                boolean done = userService.deleteUser(name,surname);
                if(!done){
                    throw new IllegalArgumentException("User not found");
                }
            }
        }else if(data.length==1){
            if(option.toUpperCase().equals("DELETE")){
                try{
                    int userId = Integer.parseInt(data[0]);
                    userService.deleteUser(userId);
                }catch (NumberFormatException e){
                    throw new IllegalArgumentException("Invalid user data");
                }
            }
        }
        else{
            throw new IllegalArgumentException("Invalid user data");
        }
    }

    @PostMapping("/rentManagement/{option}/{userId}/{bookId}")
    public void rentBook(@PathVariable String option,@PathVariable int userId,@PathVariable int bookId){
        Optional<Book> bookList= bookService.bookList(bookId);
        int free = 0;
        if(bookList.isPresent()){
            Book book = bookList.get();
            if(option.toUpperCase().equals("ADD")){
                free = book.getFree();
                if(free==0){
                    rentService.rentAdd(bookId,userId);
                    book.setFree(userId);
                    bookService.bookSave(book);
                }else{
                    throw new IllegalArgumentException("Book is busy now");
                }
            }else if(option.toUpperCase().equals("DELETE")){
                boolean done = rentService.rentDelete(userId,bookId);
                if(done){
                    book.setFree(0);
                    bookService.bookSave(book);
                }
            }
        }
    }

    public long userIdByUserData(String name,String surname){
        long userId=0;
        List<Client> listOfClients = userService.findByUserData(name,surname);
        for(Client client:listOfClients){
            userId = client.getId();
            if(userId!=0){
                break;
            }
        }
        return userId;
    }
}
