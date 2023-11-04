package com.kpi.social_network.files;

import com.kpi.social_network.files.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilesRepository extends JpaRepository<File, UUID> {
}
