package com.dsgcode.myhome.repository;

import com.dsgcode.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
