package com.example.authserver.dao;

import com.example.authserver.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}