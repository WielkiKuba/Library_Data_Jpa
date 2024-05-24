package com.Library.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    RentRepository rentRepository;

    public List<Rent> rentList(){
        return rentRepository.findAll();
    }
    public void rentAdd(Rent rent){
        rentRepository.save(rent);
    }
    public void rentAdd(int bookId,int userId){
        Rent rent = new Rent();
        rent.setBook(bookId);
        rent.setClient(userId);
        rentRepository.save(rent);
    }
    public Optional<Rent> listByClientBook(int userId, int bookId){
        return rentRepository.findByClientAndBook(userId,bookId);
    }
    public void rentDelete(Rent rent){
        rentRepository.delete(rent);
    }
    public void rentDelete(int rentId){
        List<Rent> rents = rentRepository.findById(rentId);
        if(!rents.isEmpty()){
            for(Rent rent:rents){
                rentDelete(rent);
            }
        }
    }
    public boolean rentDelete(int userId,int bookId){
        Optional<Rent> rent = listByClientBook(userId,bookId);
        if(rent.isPresent()){
            Rent rentToDelete = rent.get();
            rentDelete(rentToDelete);
            return true;
        }
        else{
            return false;
        }
    }
}
