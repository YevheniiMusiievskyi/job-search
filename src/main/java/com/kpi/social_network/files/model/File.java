package com.kpi.social_network.files.model;

import com.kpi.social_network.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class File extends BaseEntity {

	@Column(nullable = false)
	private String path;

	@Column(name = "content_type", nullable = false)
	private String contentType;
}
