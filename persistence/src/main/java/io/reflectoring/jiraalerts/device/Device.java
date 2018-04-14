package io.reflectoring.jiraalerts.device;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import io.reflectoring.jiraalerts.user.User;

/**
 * Entity for Devices.
 */
@Entity
@Table(name = "DEVICE")
@TypeDefs(@TypeDef(typeClass = DeviceTypeDef.class, name = "deviceTypeDef"))
public class Device implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "TYPE", nullable = false)
	@Type(type = "deviceTypeDef")
	private DeviceType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER")
	private User owner;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
