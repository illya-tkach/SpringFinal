package net.springapp.service;

import net.springapp.model.Role;
import net.springapp.model.User;
import net.springapp.repository.RoleRepository;
import net.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null){
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.getOne(2L));
            user.setRoles(roles);
        }

        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
       return  userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findOne(Long.parseLong(id));
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.delete(Long.parseLong(id));
    }


}
