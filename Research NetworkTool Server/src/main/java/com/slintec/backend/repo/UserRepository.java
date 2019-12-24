package com.slintec.backend.repo;

import com.slintec.backend.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNic(String nic);

    Optional<User> findByUserNameOrEmailOrNicAndDeleted(String username, String email,String nic,Boolean isDeleted);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
