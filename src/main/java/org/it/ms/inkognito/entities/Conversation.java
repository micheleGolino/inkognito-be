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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries({
    @NamedQuery(name = "Conversation.findByUser1Id", query = "SELECT c FROM Conversation c WHERE c.user1Id = :user1Id"),
    @NamedQuery(name = "Conversation.findByUser2Id", query = "SELECT c FROM Conversation c WHERE c.user2Id = :user2Id"),
    @NamedQuery(name = "Conversation.findBetweenUsers", query = "SELECT c FROM Conversation c WHERE (c.user1Id = :userA AND c.user2Id = :userB) OR (c.user1Id = :userB AND c.user2Id = :userA)")
})
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
	@Schema(description = "Unique identifier of the conversation")
	private BigInteger id;

	@Column(name = "user1_id", nullable = false)
	@Schema(description = "Identifier of the first user")
	private BigInteger user1Id;

	@Column(name = "user2_id", nullable = false)
	@Schema(description = "Identifier of the second user")
	private BigInteger user2Id;

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
