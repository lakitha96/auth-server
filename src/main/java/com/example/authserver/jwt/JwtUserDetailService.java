package com.example.authserver.jwt;

import com.example.authserver.dao.UserDao;
import com.example.authserver.model.User;
import com.example.authserver.model.UserCustomDetail;
import com.example.authserver.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Service
public class JwtUserDetailService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public JwtUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserResource getUserResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setUsername(user.getUsername());
		userResource.setPassword(user.getPassword());
        userResource.setUuid(user.getUuid());
        userResource.setEmail(user.getEmail());
        userResource.setRole(user.getRole());
        return userResource;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User byEmailAndActive = userDao.findByEmail(email);
        if (byEmailAndActive == null) {
            throw new UsernameNotFoundException("Email not found.");
        }
        return new UserCustomDetail(getUserResource(byEmailAndActive));
    }
}
