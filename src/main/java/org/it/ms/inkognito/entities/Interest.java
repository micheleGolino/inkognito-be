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

@Schema(description = "Entity representing an interest")
@Entity
@Table(name = "interests")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Interest extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the interest", example = "1")
	private BigInteger id;

	@Column(name = "name", nullable = false)
	@Schema(description = "Name of the interest", example = "Hiking", required = true)
	private String name;

	@Column(name = "description")
	@Schema(description = "Description of the interest", example = "Outdoor activities")
	private String description;

	@Column(name = "note")
	@Schema(description = "Additional notes", example = "Popular interest")
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
