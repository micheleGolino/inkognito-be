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

@Schema(description = "Entity representing a user's subscription")
@Entity
@Table(name = "subscriptions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the subscription", example = "1")
	private BigInteger id;

	@Column(name = "user_id", nullable = false)
	@Schema(description = "Identifier of the associated user", example = "1", required = true)
	private BigInteger userId;

	@Column(name = "subscription_type")
	@Schema(description = "Type of subscription", example = "Premium")
	private String subscriptionType;

	@Column(name = "start_date")
	@Schema(description = "Subscription start date", example = "2025-03-02T10:15:30")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	@Schema(description = "Subscription end date", example = "2025-04-02T10:15:30")
	private LocalDateTime endDate;

	@Column(name = "note")
	@Schema(description = "Additional notes", example = "Active subscription")
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
