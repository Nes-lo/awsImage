package com.codingdojo.projectexam.services;

import com.codingdojo.projectexam.models.LoginUser;
import com.codingdojo.projectexam.models.User;
import com.codingdojo.projectexam.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);

    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user, BindingResult result){

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            result.rejectValue("email", "Unique", "Existing Email!");
            return null;

        } else if (!Pattern
                .compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^.,&+=])(?=\\S+$).{8,16}$")
                .matcher(user.getPassword()).matches()) {
            result.rejectValue("password", "ValidationError", "The password must have alphanumeric characters, uppercase, lowercase and special character!");
            return null;
        } else if(!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "Matches", "The Confirm Password must match Password!");
            return null;
        } else if (!result.hasErrors()) {
            user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
            return userRepository.save(user);
        }
        return null;
    }
    public User login(LoginUser newLogin, BindingResult result){

        User user=userRepository.findByEmail(newLogin.getEmail()).orElse(null);
        if(user==null) {
            result.rejectValue("email", "Matches", "Invalid Email!");
            return null;
        }
        else if(Boolean.FALSE.equals(BCrypt.checkpw(newLogin.getPassword(),user.getPassword()))
        ){
            result.rejectValue("password", "Matches", "Invalid Password!");
            return null;
        }
        return user;
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
