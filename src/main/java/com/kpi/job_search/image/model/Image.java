package com.kpi.job_search.image.model;

import com.kpi.job_search.db.BaseEntity;
import com.kpi.job_search.files.model.File;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Table(name = "images")
public class Image extends BaseEntity {

    @Column(name = "link", nullable = false)
    private String link;

    @OneToOne(fetch = FetchType.LAZY)
    private File file;
}
