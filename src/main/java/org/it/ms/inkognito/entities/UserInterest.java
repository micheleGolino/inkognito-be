package org.it.ms.inkognito.entities;

import java.time.LocalDateTime;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQuery(name = "UserInterest.findByUserId", query = "SELECT ui FROM UserInterest ui WHERE ui.id.userId = :userId")
@NamedQuery(name = "UserInterest.findByInterestId", query = "SELECT ui FROM UserInterest ui WHERE ui.id.interestId = :interestId")
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
