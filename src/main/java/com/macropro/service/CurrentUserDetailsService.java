package com.macropro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macropro.dao.IUserDAO;
import com.macropro.model.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserDAO userDAO;

    public CurrentUserDetailsService() {

    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        
        if (user == null)
        	throw new UsernameNotFoundException("User not authorized.");
        
        return user;
    }
}