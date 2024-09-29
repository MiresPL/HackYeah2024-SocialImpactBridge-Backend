package com.mires.socialimpactbridgebackend.repositories.experience;

import com.mires.common.objects.experience.Experience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Long> {
    Experience save(Experience experience);
}
