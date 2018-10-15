/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angularjs.bankapp.controller;

import com.angularjs.bankapp.model.User;
import com.angularjs.bankapp.restmodels.UserResponse;
import com.angularjs.bankapp.service.IUserService;
import com.angularjs.bankapp.settings.Constants;
import com.angularjs.bankapp.shared.SimpleResponse;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ravindra.palli
 */
@RestController
public class UserController {

    @Autowired
    IUserService userService;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private static final Logger LOGGER =
            Logger.getLogger(UserController.class.getName());

    @GetMapping(value={"/user"})
    public List<UserResponse> getListOfUsers() {
        try {
            LOGGER.info("getListOfUsers: Attemping to get users list");
            return userService.findAllUsers();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,
                    "getListOfUsers: Failed to get users list {0}",
                    e.toString()
            );
            throw e;
        }
    }

    @PostMapping(value={"/user"})
    public SimpleResponse saveUser(@Valid User user, BindingResult result) {
        try {
            LOGGER.info("saveUser: Attemping to save user data");
            if (result.hasErrors()) {
            return new SimpleResponse(Constants.VALIDATION_FAILED);
            }
            userService.saveUser(user);
            return new SimpleResponse(Constants.SUCCESS);
        } catch(Exception e) {
            LOGGER.log(Level.WARNING,
                    "saveUser: Failed to save user {0}",
                    e.toString()
            );
            throw e;
        }
    }

    @PutMapping(value={"/user/{id}"})
    public SimpleResponse updateUser(
            @Valid User user,
            BindingResult result,
            @PathVariable int id) {
        try {
            LOGGER.info("updateUser: Attemping to update user data");
            if (result.hasErrors()) {
            return new SimpleResponse(Constants.VALIDATION_FAILED);
            } 
            userService.updateUser(user);
            return new SimpleResponse(Constants.SUCCESS);
        } catch(Exception e) {
            LOGGER.log(Level.WARNING,
                    "updateUser: Failed to update user {0}",
                    e.toString()
            );
            throw e;
        }
    }

    @DeleteMapping(value={"/user/{id}"})
    public SimpleResponse deleteUser(@PathVariable int id) {
        try {
            LOGGER.info("deleteUser: Attemping to delete user data");
            userService.delete(id);
            return new SimpleResponse(Constants.SUCCESS);
        } catch(Exception e) {
            LOGGER.log(Level.WARNING,
                    "deleteUser: Failed to delete user {0}",
                    e.toString()
            );
            throw e;
        }
    }
    
}
