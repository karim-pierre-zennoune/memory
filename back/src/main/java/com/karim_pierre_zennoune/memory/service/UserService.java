package com.karim_pierre_zennoune.memory.service;




import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    public User saveUser(User user) {
         System.out.println("coucou 3");
        return userRepository.save(user);
    }

        public List<User> getUsers() {
             System.out.println("coucou dans getUsers service");

            List<User> plop = userRepository.findAll();
            System.out.println(plop);

        return plop;
    }

    
}
