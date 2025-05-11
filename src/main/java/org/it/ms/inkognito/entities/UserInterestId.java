package org.it.ms.inkognito.entities;

import java.io.Serializable;
import java.math.BigInteger;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Composite key for UserInterest")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserInterestId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	@Schema(description = "User identifier")
	private BigInteger userId;

	@Column(name = "interest_id")
	@Schema(description = "Interest identifier")
	private BigInteger interestId;
}
