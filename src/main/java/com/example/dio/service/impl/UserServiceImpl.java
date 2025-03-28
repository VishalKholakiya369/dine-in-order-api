package com.example.dio.service.impl;

import com.example.dio.security.util.UserIdentity;
import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserIdentity userIdentity;

    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        User user = this.createUserByRole(registrationRequest.getRole());

       // this.mapToNewUser(user,user2);
       // this.mapToEntity(registrationRequest,user);
        userMapper.mapToEntity(registrationRequest, user);
        this.EncryptPassword(user);
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse findUserById() {
        return null;
    }

    private void EncryptPassword(User user){
      String encodedPassword =  passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
    }





//    @Override
//    public UserResponse findUserById() {
//        User user= userIdentity.getCurrentUser();
//   //     return this.mapToUserResponse(user);
//
//     //  return userMapper.mapToUserResponse();
////                .map(userMapper::mapToUserResponse)
////                .orElseThrow(()-> new UserNotFoundByIdException("Faild to find user,user not found by id"));
//    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest) {
        User existingUser = userIdentity.getCurrentUser();

        userMapper.mapToNewUser(userRequest, existingUser);
        userRepository.save(existingUser);
      //  existingUser.setUsername(updatedUser.getUsername());
        return userMapper.mapToUserResponse(existingUser);
    }

    /**
     * Produces and returns child instance of the User based on the user role.
     *
     * @param role The role of the user
     * @return User the parent reference containing either of Staff or Admin instance.
     */

    private User createUserByRole(UserRole role){
        User user;
        switch (role){
            case ADMIN -> user = new Admin();
            case STAFF -> user = new Staff();
            default -> throw new RuntimeException("Failed to register user,invalid user Type!");
        }
        return  user;
    }

//    private void mapToNewUser(User source,User target){
//        target.setUsername(source.getUsername());
//        target.setEmail(source.getEmail());
//        target.setPassword(source.getPassword());
//        target.setRole(source.getRole());
//        target.setPhNo(source.getPhNo());
//        target.setCreatAt(source.getCreatAt());
//        target.setLastModifirdAt(source.getLastModifirdAt());
//    }


}
