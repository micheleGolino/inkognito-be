package org.it.ms.inkognito.entities;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
@Schema(description = "Entity representing a user of the dating app")
@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the user")
	private BigInteger id;

	@Column(name = "email", unique = true, nullable = false)
	@Schema(description = "User email address")
	private String email;

	@Column(name = "username", unique = true)
	@Schema(description = "Username dell'utente (mobile only)")
	private String username;

	@Column(name = "bio")
	@Schema(description = "Biografia dell'utente (mobile only)")
	private String bio;

	@Column(name = "image_url")
	@Schema(description = "URL immagine profilo (mobile only)")
	private String imageUrl;

	// The password must be stored as a hash (e.g., BCrypt or Argon2)
	@Column(name = "password", nullable = false)
	@Schema(description = "User password hash")
	private String password;

	@Column(name = "first_name")
	@Schema(description = "User first name")
	private String firstName;

	@Column(name = "last_name")
	@Schema(description = "User last name")
	private String lastName;

	@Column(name = "note")
	@Schema(description = "Additional notes about the user")
	private String note;

	@Column(name = "date_insert")
	@Schema(description = "Date of insertion")
	private LocalDateTime dateInsert;

	@Column(name = "date_update")
	@Schema(description = "Date of last update")
	private LocalDateTime dateUpdate;

	@Column(name = "user_insert")
	@Schema(description = "User who inserted the record")
	private String userInsert;

	@Column(name = "user_update")
	@Schema(description = "User who last updated the record")
	private String userUpdate;
}
