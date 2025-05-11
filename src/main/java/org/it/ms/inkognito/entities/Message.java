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

@NamedQuery(name = "Message.findByConversationId", query = "SELECT m FROM Message m WHERE m.conversationId = :conversationId ORDER BY m.sentAt ASC")
@NamedQuery(name = "Message.findBySenderId", query = "SELECT m FROM Message m WHERE m.senderId = :senderId")
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
	@Schema(description = "Unique identifier of the message")
	private BigInteger id;

	@Column(name = "conversation_id", nullable = false)
	@Schema(description = "Identifier of the conversation")
	private BigInteger conversationId;

	@Column(name = "sender_id", nullable = false)
	@Schema(description = "Identifier of the sender")
	private BigInteger senderId;

	@Column(name = "message_text", nullable = false)
	@Schema(description = "Text of the message")
	private String messageText;

	@Column(name = "sent_at", nullable = false)
	@Schema(description = "Timestamp when the message was sent")
	private LocalDateTime sentAt;

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
