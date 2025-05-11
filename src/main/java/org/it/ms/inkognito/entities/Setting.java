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

@NamedQuery(name = "Setting.findByUserId", query = "SELECT s FROM Setting s WHERE s.userId = :userId")
@Schema(description = "Entity representing user settings")
@Entity
@Table(name = "settings")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Setting extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the setting")
	private BigInteger id;

	@Column(name = "user_id", nullable = false)
	@Schema(description = "Identifier of the associated user")
	private BigInteger userId;

	@Column(name = "language")
	@Schema(description = "Language code")
	private String language;

	@Column(name = "theme")
	@Schema(description = "Theme preference")
	private String theme;

	@Column(name = "push_notifications_enabled")
	@Schema(description = "Flag indicating if push notifications are enabled")
	private Boolean pushNotificationsEnabled;

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
