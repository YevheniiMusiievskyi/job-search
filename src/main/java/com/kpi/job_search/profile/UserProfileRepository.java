package com.kpi.job_search.profile;

import java.util.UUID;

import com.kpi.job_search.profile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

}
