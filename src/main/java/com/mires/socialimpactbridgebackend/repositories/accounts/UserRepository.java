package com.mires.socialimpactbridgebackend.repositories.accounts;

import com.mires.common.objects.accounts.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
