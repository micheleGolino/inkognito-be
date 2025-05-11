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

@NamedQuery(name = "Preference.findByUserId", query = "SELECT p FROM Preference p WHERE p.userId = :userId")
@Schema(description = "Entity representing user preferences")
@Entity
@Table(name = "preferences")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Preference extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the preference")
	private BigInteger id;

	@Column(name = "user_id", nullable = false)
	@Schema(description = "Identifier of the associated user")
	private BigInteger userId;

	@Column(name = "desired_gender")
	@Schema(description = "Desired gender for matching")
	private String desiredGender;

	@Column(name = "max_distance")
	@Schema(description = "Maximum acceptable distance")
	private Integer maxDistance;

	@Column(name = "min_age")
	@Schema(description = "Minimum age preference")
	private Integer minAge;

	@Column(name = "max_age")
	@Schema(description = "Maximum age preference")
	private Integer maxAge;

	@Column(name = "note")
	@Schema(description = "Additional notes")
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
