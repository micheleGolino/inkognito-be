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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Schema(description = "Unique identifier of the user", example = "1")
	private BigInteger id;

	@Column(name = "email", unique = true, nullable = false)
	@Schema(description = "User email address", example = "john.doe@example.com", required = true)
	private String email;

	// The password must be stored as a hash (e.g., BCrypt or Argon2)
	@Column(name = "password", nullable = false)
	@Schema(description = "User password hash", example = "$2a$10$...")
	private String password;

	@Column(name = "first_name")
	@Schema(description = "User first name", example = "John")
	private String firstName;

	@Column(name = "last_name")
	@Schema(description = "User last name", example = "Doe")
	private String lastName;

	@Column(name = "note")
	@Schema(description = "Additional notes about the user", example = "Test user")
	private String note;

	@Column(name = "date_insert")
	@Schema(description = "Date of insertion", example = "2025-03-02T10:15:30")
	private LocalDateTime dateInsert;

	@Column(name = "date_update")
	@Schema(description = "Date of last update", example = "2025-03-02T10:15:30")
	private LocalDateTime dateUpdate;

	@Column(name = "user_insert")
	@Schema(description = "User who inserted the record", example = "system")
	private String userInsert;

	@Column(name = "user_update")
	@Schema(description = "User who last updated the record", example = "admin")
	private String userUpdate;
}
