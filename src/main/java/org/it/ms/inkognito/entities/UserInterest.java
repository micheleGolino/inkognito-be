package org.it.ms.inkognito.entities;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Entity representing the association between a user and an interest")
@Entity
@Table(name = "user_interests")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInterest extends PanacheEntityBase {

	@EmbeddedId
	@Schema(description = "Composite key consisting of user_id and interest_id")
	private UserInterestId id;

	@Column(name = "note")
	@Schema(description = "Additional notes", example = "User loves outdoor activities")
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
