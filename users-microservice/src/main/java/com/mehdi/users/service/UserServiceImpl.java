package com.mehdi.users.service;

import com.mehdi.users.entities.Role;
import com.mehdi.users.entities.User;
import com.mehdi.users.repositories.RoleRepository;
import com.mehdi.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRep;

    @Autowired
    RoleRepository roleRep;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        if (userRep.findByUsername(user.getUsername()) != null) {
            return userRep.findByUsername(user.getUsername());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRep.findByUsername(username);
        Role r = roleRep.findByRole(rolename);
        if (r != null) {
            if (usr.getRoles() == null) {
                usr.setRoles(new ArrayList<>());
            }
            if (!usr.getRoles().contains(r)) {
                usr.getRoles().add(r);
                return userRep.save(usr);
            }
        }
        return usr;
    }

    @Override
    public Role addRole(Role role) {
        if (roleRep.existsByRole(role.getRole())) {
            return roleRep.findByRole(role.getRole());
        }
        return roleRep.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRep.findAll();
    }
}