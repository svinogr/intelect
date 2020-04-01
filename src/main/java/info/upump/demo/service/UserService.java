package info.upump.demo.service;

import info.upump.demo.model.User;
import info.upump.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(s);

        if (byUsername == null) throw  new  UsernameNotFoundException("такого user нет в базе");

        return byUsername;
    }
    public Iterable<User> findAllUsers() {
        Iterable<User> all = userRepository.findAll();
        return  all;
    }
}
