package com.titan.loki.database;

import com.titan.loki.database.dao.UserDAO;
import com.titan.loki.database.repository.UsersRepository;
import com.titan.loki.model.User;
import com.titan.loki.model.UserLoginRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class Vision {

    @Autowired
    private UsersRepository usersRepository;

    public Vision() {}

    public List<User> getAllUsersInDatabase() {
        List<User> usersList = new ArrayList<>();
        try {
            List<UserDAO> data = usersRepository.findAll();
            Converter.dataToViewModelConverterForUserList(usersList, data);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
        return usersList;
    }

    public boolean isCorrectLogin(String username, String password) {
        if (username.isEmpty()) return false;
        if (password.isEmpty()) return false;

        List<User> usersList = getAllUsersInDatabase();
        for (User user : usersList) {
            log.info("Comparing " + user.getUserID() + ", " + user.getPassword() + " to: " + username + " and " + password);
            if (user.getUserID().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserInDatabase(String userID) {
        if (userID.isEmpty()) return false;

        List<User> usersList = getAllUsersInDatabase();
        for (User user : usersList) {
            log.info("Comparing " + user.getUserID() + ", " + user.getPassword() + " to: " + userID);
            if (user.getUserID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean createNewUser(UserLoginRequest request) {
        if (request == null) return false;
        if (request.getUsername().isEmpty()) return false;
        if (request.getPassword().isEmpty()) return false;
        if (usersRepository.existsById(request.getUsername())) return false;

        UserDAO user = new UserDAO();
        Converter.requestToDataConverter(request, user);
        usersRepository.save(user);

        return true;
    }

}
