package com.project.brainstomingbe.security;

import com.project.brainstomingbe.User.User;
import com.project.brainstomingbe.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        return new UserDetailsImpl(user);
    }

    public UserDetailsImpl loadUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + id));

        return new UserDetailsImpl(user);
    }
}
