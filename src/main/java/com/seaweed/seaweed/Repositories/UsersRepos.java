package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepos extends JpaRepository<Users,Long> {
    Optional<Users> findUsersById(Long id);
}
