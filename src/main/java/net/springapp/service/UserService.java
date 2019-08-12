package net.springapp.service;

import net.springapp.model.Role;
import net.springapp.model.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserService {
    void save(User user) throws DataIntegrityViolationException;

    List<User> getAllUsers();

    User findById(String id);

    List<Role> findAllRole();

    void deleteUserById(String id);
}
