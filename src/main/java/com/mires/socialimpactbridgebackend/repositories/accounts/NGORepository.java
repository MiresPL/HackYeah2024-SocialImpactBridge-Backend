package com.mires.socialimpactbridgebackend.repositories.accounts;

import com.mires.common.objects.accounts.ngo.NGO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NGORepository extends CrudRepository<NGO, Long> {
    List<NGO> findAll();
}
