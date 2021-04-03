package com.titan.loki.database;

import com.titan.loki.database.dao.UserDAO;
import com.titan.loki.model.User;
import com.titan.loki.model.UserLoginRequest;

import java.util.List;

public class Converter {

    public static void requestToDataConverter(UserLoginRequest request, UserDAO user) {
        user.setUserID(request.getUsername());
        user.setPassword(request.getPassword());
    }

    public static void viewToDataModelConverter(User userView, UserDAO userData) {
        userData.setUserID(userView.getUserID());
        userData.setPassword(userView.getUserID());
    }

    public static void dataToViewModelConverter(User viewModel, UserDAO dataModel) {
        viewModel.setUserID(dataModel.getUserID());
        viewModel.setPassword(dataModel.getPassword());
    }

    public static void dataToViewModelConverterForUserList(List<User> viewModel, List<UserDAO> dataModel) {
        for (int i=0; i<dataModel.size(); i++) {
            User userView = new User();
            dataToViewModelConverter(userView,dataModel.get(i));
            viewModel.add(userView);
        }
    }

}
