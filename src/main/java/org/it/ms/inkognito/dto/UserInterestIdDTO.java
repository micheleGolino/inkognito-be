package org.it.ms.inkognito.dto;

import java.io.Serializable;
import java.math.BigInteger;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO per rappresentare la chiave composta di UserInterest.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO per la chiave composta di UserInterest")
public class UserInterestIdDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema(description = "ID utente")
    private BigInteger userId;
    @Schema(description = "ID interesse")
    private BigInteger interestId;
}
