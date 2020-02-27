package com.obarra.user.repository;

import com.obarra.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    @RestResource(path = "find-by-email")
    @Query("select u from User u where u.email=:email")
    User findMatchEmail(@Param("email") String email);

}
