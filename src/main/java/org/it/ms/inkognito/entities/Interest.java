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

@NamedQuery(name = "Interest.findByName", query = "SELECT i FROM Interest i WHERE i.name = :name")
@NamedQuery(name = "Interest.findAllOrdered", query = "SELECT i FROM Interest i ORDER BY i.name ASC")
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
	@Schema(description = "Unique identifier of the interest")
	private BigInteger id;

	@Column(name = "name", nullable = false)
	@Schema(description = "Name of the interest")
	private String name;

	@Column(name = "description")
	@Schema(description = "Description of the interest")
	private String description;

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
