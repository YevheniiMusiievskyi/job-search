package com.kpi.job_search.db;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "`timestamp`")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private Date updatedAt;

    public BaseEntity(UUID id) {
        this.id = id;
    }
}
