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

@Schema(description = "Entity representing a message within a conversation")
@Entity
@Table(name = "messages")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the message", example = "1")
	private BigInteger id;

	@Column(name = "conversation_id", nullable = false)
	@Schema(description = "Identifier of the conversation", example = "1", required = true)
	private BigInteger conversationId;

	@Column(name = "sender_id", nullable = false)
	@Schema(description = "Identifier of the sender", example = "1", required = true)
	private BigInteger senderId;

	@Column(name = "message_text", nullable = false)
	@Schema(description = "Text of the message", example = "Hello, how are you?", required = true)
	private String messageText;

	@Column(name = "sent_at", nullable = false)
	@Schema(description = "Timestamp when the message was sent", example = "2025-03-02T10:15:30", required = true)
	private LocalDateTime sentAt;

	@Column(name = "note")
	@Schema(description = "Additional notes", example = "Initial greeting")
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
