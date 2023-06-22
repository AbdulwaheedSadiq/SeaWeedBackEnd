package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepos extends JpaRepository<Roles,Long> {
}
