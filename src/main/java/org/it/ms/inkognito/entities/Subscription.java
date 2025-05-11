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

@NamedQuery(name = "Subscription.findByUserId", query = "SELECT s FROM Subscription s WHERE s.userId = :userId")
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
	@Schema(description = "Unique identifier of the subscription")
	private BigInteger id;

	@Column(name = "user_id", nullable = false)
	@Schema(description = "Identifier of the associated user")
	private BigInteger userId;

	@Column(name = "subscription_type")
	@Schema(description = "Type of subscription")
	private String subscriptionType;

	@Column(name = "start_date")
	@Schema(description = "Subscription start date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	@Schema(description = "Subscription end date")
	private LocalDateTime endDate;

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
