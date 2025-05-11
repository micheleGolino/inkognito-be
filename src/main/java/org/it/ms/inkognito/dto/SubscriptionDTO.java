package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare una sottoscrizione utente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta una sottoscrizione utente")
public class SubscriptionDTO {
    @Schema(description = "ID univoco della sottoscrizione")
    private BigInteger id;
    @Schema(description = "ID utente associato")
    private BigInteger userId;
    @Schema(description = "Tipo di sottoscrizione")
    private String subscriptionType;
    @Schema(description = "Data inizio sottoscrizione")
    private LocalDateTime startDate;
    @Schema(description = "Data fine sottoscrizione")
    private LocalDateTime endDate;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
}
