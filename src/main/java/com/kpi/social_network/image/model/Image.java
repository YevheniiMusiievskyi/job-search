package com.kpi.social_network.image.model;

import com.kpi.social_network.db.BaseEntity;
import com.kpi.social_network.files.model.File;
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
