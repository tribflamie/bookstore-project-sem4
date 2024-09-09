package com.backend.bookstore.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.backend.bookstore.dtos.UserDto;
import com.backend.bookstore.models.User;
import com.backend.bookstore.repositories.UserRepository;
import com.backend.bookstore.security.CustomerUsersDetailsService;
import com.backend.bookstore.security.JwtFilter;
import com.backend.bookstore.security.JwtUtil;
import com.backend.bookstore.services.UserService;
import com.backend.bookstore.utils.BookstoreUtil;
import com.backend.bookstore.utils.EmailUtil;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));
                    return BookstoreUtil.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return BookstoreUtil.getResponseEntity("Email Already Exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return BookstoreUtil.getResponseEntity("Invalid Data", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("phone") && requestMap.containsKey("email")
                && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setPhone(requestMap.get("phone"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("true");
        user.setRole("user");
        return user;
    }

    @SuppressWarnings("unused")
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login {}", requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
            if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                return new ResponseEntity<String>(
                        "{\"token\":\"" + jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                customerUsersDetailsService.getUserDetail().getRole()) + "\"}",
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval" + "\"}",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials" + "\"}",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userRepository.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    userRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userRepository.getAllAdmin());
                    return BookstoreUtil.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);
                } else {
                    return BookstoreUtil.getResponseEntity("User Id Does Not exist", HttpStatus.OK);
                }
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
       allAdmin.remove(jwtFilter.getCurrentUser());
       if(status!=null&status.equalsIgnoreCase("true")){
            emailUtil.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "USER:- "+user+" \n is approved by \nADMIN:- "+jwtFilter.getCurrentUser(), allAdmin);
       } else {
        emailUtil.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "USER:- "+user+" \n is disabled by \nADMIN:- "+jwtFilter.getCurrentUser(), allAdmin);
       }
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return BookstoreUtil.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> request) {
        try {
            User thisUser = userRepository.findByEmail(jwtFilter.getCurrentUser());
            if(!thisUser.equals(null)){
                if(thisUser.getPassword().equals(request.get("oldPassword"))){
                    thisUser.setPassword(request.get("newPassword"));
                    userRepository.save(thisUser);
                    return BookstoreUtil.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
                }
                return BookstoreUtil.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
            }
            return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> request) {
        try {
            User user = userRepository.findByEmail(request.get("email"));
            if(!Objects.isNull(user)&&!Strings.isNullOrEmpty(user.getEmail())){
                emailUtil.forgotMail(user.getEmail(), "Credentials by Bookstore Management System", user.getPassword());
            }
            return BookstoreUtil.getResponseEntity("Check Your Mail For Credentials.", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
