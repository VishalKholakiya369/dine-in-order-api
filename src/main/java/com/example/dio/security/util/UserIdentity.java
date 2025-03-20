package com.example.dio.security.util;


import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserIdentity {

    private final UserRepository userRepository;

   public  Authentication getAuthentication(){
     return SecurityContextHolder.getContext().getAuthentication();
   }

   public  String getCurrentUserEmail(){
      return getAuthentication().getName();
   }

   public User getCurrentUser(){
     return   userRepository.findByEmail(this.getCurrentUserEmail())
               .orElseThrow(()-> new UserNotFoundByIdException("User not found!"));
   }

   public void validateOwnerShip(String ownerName) throws RuntimeException, IllegalAccessException {
       if(!getCurrentUserEmail().equals(ownerName)){
           throw new IllegalAccessException("owner not allow to modified the resorce request !!");
       }
   }

}
