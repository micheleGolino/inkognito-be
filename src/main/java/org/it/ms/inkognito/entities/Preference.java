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
	@Schema(description = "Unique identifier of the preference", example = "1")
	private BigInteger id;

	@Column(name = "user_id", nullable = false)
	@Schema(description = "Identifier of the associated user", example = "1", required = true)
	private BigInteger userId;

	@Column(name = "desired_gender")
	@Schema(description = "Desired gender for matching", example = "Female")
	private String desiredGender;

	@Column(name = "max_distance")
	@Schema(description = "Maximum acceptable distance", example = "50")
	private Integer maxDistance;

	@Column(name = "min_age")
	@Schema(description = "Minimum age preference", example = "18")
	private Integer minAge;

	@Column(name = "max_age")
	@Schema(description = "Maximum age preference", example = "35")
	private Integer maxAge;

	@Column(name = "note")
	@Schema(description = "Additional notes", example = "Prefer active lifestyle")
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
