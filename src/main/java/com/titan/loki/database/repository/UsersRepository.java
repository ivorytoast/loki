package com.titan.loki.database.repository;

import com.titan.loki.database.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserDAO, String> {}
