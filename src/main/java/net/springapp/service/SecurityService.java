package net.springapp.service;

public interface SecurityService {
    String findLoggedInEmail();

    void autologin(String username, String password);
}