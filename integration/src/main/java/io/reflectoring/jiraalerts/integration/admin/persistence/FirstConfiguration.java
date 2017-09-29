package io.reflectoring.jiraalerts.integration.admin.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

@Entity
@Table(name = "FIRST_CONFIGURATION")
public class FirstConfiguration {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "FIRST_CONFIGURATION")
	private boolean firstConfiguration;
}
