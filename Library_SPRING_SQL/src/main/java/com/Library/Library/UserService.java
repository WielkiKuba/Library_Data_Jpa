package com.Library.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public List<Client> findById(long userId){
        return userRepository.findById(userId);
    }
    public List<Client> findAll(){
        return userRepository.findAll();
    }
    public List<Client> findByUserData(String name, String surname){
        return userRepository.findByNameAndSurname(name,surname);
    }
    public void addUser(String name,String surname){
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        userRepository.save(client);
    }
    public void addUser(Client client){
        userRepository.save(client);
    }
    public boolean deleteUser(Client client){
        try{
            userRepository.delete(client);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean deleteUser(String name,String surname){
        List<Client> user = userRepository.findByNameAndSurname(name,surname);
        if(!user.isEmpty()){
            for(Client client:user){
                try{
                    userRepository.delete(client);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        }else{
            return false;
        }
        return false;
    }
    public void deleteUser(long id){
        List<Client> user = userRepository.findById(id);
        if(!user.isEmpty()){
            for(Client client:user){
                userRepository.delete(client);
            }
        }
    }
}
