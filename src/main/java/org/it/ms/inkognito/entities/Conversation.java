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

@Schema(description = "Entity representing a conversation between two users")
@Entity
@Table(name = "conversations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conversation extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the conversation", example = "1")
	private BigInteger id;

	@Column(name = "user1_id", nullable = false)
	@Schema(description = "Identifier of the first user", example = "1", required = true)
	private BigInteger user1Id;

	@Column(name = "user2_id", nullable = false)
	@Schema(description = "Identifier of the second user", example = "2", required = true)
	private BigInteger user2Id;

	@Column(name = "note")
	@Schema(description = "Additional notes", example = "Conversation between John and Jane")
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
