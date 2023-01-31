package com.kuehne.city.service;
import com.kuehne.city.entity.User;
import com.kuehne.city.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUserName(username);

        if (user == null) {
            logger.debug("User not found:"+ username);
            throw new UsernameNotFoundException("User Not Found: " + username);
        }
        logger.debug("user details found -"+ username);
        return new org.springframework.security.core.userdetails.User(user.getUsername()
                , user.getPassword()
                , user.getAuthorities());

    }
}
