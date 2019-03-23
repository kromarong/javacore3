package ru.kromarong.chatServer.auth;

import ru.kromarong.chatServer.connectToDB;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {

    public Map<String, String> clientList = new HashMap<>();

    public AuthServiceImpl() {

    }

    @Override
    public boolean authUser(String username, String password) throws SQLException {
        return connectToDB.findUser(username, password);
    }

}

