package com.kpi.job_search.user_profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.kpi.job_search.user_profile.model.UserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

    Optional<UserProfile> findByUserId(UUID userId);

    @Query("""
            SELECT userProfile FROM UserProfile userProfile
            JOIN userProfile.user u
            JOIN u.appliedVacancies v
            WHERE :vacancyId in (v.id)
            """)
    List<UserProfile> findCandidatesByVacancyId(UUID vacancyId, Pageable pageable);

}
