package com.mires.socialimpactbridgebackend.repositories.accounts;

import com.mires.common.objects.accounts.MainAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainAccountRepository extends CrudRepository<MainAccount, Long> {
    List<MainAccount> findAll();
    MainAccount findByEmail(String email);
}
