package ru.kromarong.chatServer;

import ru.kromarong.chatClient.swing.PatternList;
import ru.kromarong.chatServer.auth.AuthService;
import ru.kromarong.chatServer.auth.AuthServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;


public class ChatServer {


    private AuthService authService = new AuthServiceImpl();

    private ExecutorService threadService = Executors.newCachedThreadPool();

    private Map<String, ClientHandler> clientHandlerMap = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start(7777);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("New client connected!");

                try {
                    String authMessage = inp.readUTF();
                    Matcher authMatcher = PatternList.AUTH_PATTERN.matcher(authMessage);
                    Matcher regMatcher = PatternList.REG_PATTERN.matcher(authMessage);
                    if (authMatcher.matches()) {
                        String username = authMatcher.group(1);
                        String password = authMatcher.group(2);
                        if (authService.authUser(username, password)) {
                            clientHandlerMap.put(username, new ClientHandler(username, socket, this));
                            out.writeUTF("/auth successful");
                            out.flush();
                            broadcastUserConnected(username);
                            System.out.printf("Authorization for user %s successful%n", username);
                        } else {
                            System.out.printf("Authorization for user %s failed%n", username);
                            out.writeUTF("/auth fails");
                            out.flush();
                            socket.close();
                        }
                    } else if (regMatcher.matches()) {
                        String user = regMatcher.group(1);
                        String pwd = regMatcher.group(2);
                        if (connectToDB.findUser(user)) {
                            out.writeUTF("/reg failed");
                            out.flush();
                        } else {
                            connectToDB.createClient(user, user, pwd);
                            clientHandlerMap.put(user, new ClientHandler(user, socket, this));
                            out.writeUTF("/reg successful");
                            out.flush();
                            broadcastUserConnected(user);
                            System.out.printf("Registration for user %s successful%n", user);
                        }
                    } else {
                        System.out.printf("Incorrect authorization message %s%n", authMessage);
                        out.writeUTF("/auth fails");
                        out.flush();
                        socket.close();
                    }

                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadService.shutdown();
        }
    }

    public void sendMessage(String userTo, String userFrom, String msg) throws IOException {
        ClientHandler userToClientHandler = clientHandlerMap.get(userTo);
        if (userToClientHandler != null) {
            userToClientHandler.sendMessage(userFrom, msg);
        } else {
            System.out.printf("User %s not found. Message from %s is lost.%n", userTo, userFrom);
        }
    }


    public void sendUserList(ClientHandler clientHandler) throws IOException {
        DataOutputStream out = new DataOutputStream(clientHandler.getSocket().getOutputStream());
        for(String key : clientHandlerMap.keySet()){
            out.writeUTF(String.format(PatternList.USER_UPD_STRING, key));
        }
    }

    public void unsubscribeClient(ClientHandler clientHandler) throws IOException {
        clientHandlerMap.remove(clientHandler.getUsername());
        broadcastUserDisconnected(clientHandler.getUsername());
    }

    public void broadcastUserConnected(String username) throws IOException {
        for(ClientHandler clientHandler : clientHandlerMap.values()){
            DataOutputStream out = new DataOutputStream(clientHandler.getSocket().getOutputStream());
            out.writeUTF(String.format(PatternList.USER_CON_STRING, username));
        }
    }

    public void broadcastUserDisconnected(String username) throws IOException {
        for(ClientHandler clientHandler : clientHandlerMap.values()){
            DataOutputStream out = new DataOutputStream(clientHandler.getSocket().getOutputStream());
            out.writeUTF(String.format(PatternList.USER_DIS_STRING, username));
        }
    }

    public ExecutorService getThreadService() {
        return threadService;
    }

}
