package org.generation.italy.universogames.auth;

import org.generation.italy.universogames.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, UserDAO {

}
