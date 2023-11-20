package com.kpi.job_search.files;

import com.kpi.job_search.files.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilesRepository extends JpaRepository<File, UUID> {
}
