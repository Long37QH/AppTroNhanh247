package com.example.nhatro247.Model;

public class User {
    private int idUser;
    private String username;
    private String pass;

    public User() {
    }

    public User(int idUser, String username, String pass) {
        this.idUser = idUser;
        this.username = username;
        this.pass = pass;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
