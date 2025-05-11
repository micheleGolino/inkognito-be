package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare una conversazione tra due utenti.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta una conversazione tra due utenti")
public class ConversationDTO {
    @Schema(description = "ID univoco della conversazione")
    private BigInteger id;
    @Schema(description = "ID del primo utente")
    private BigInteger user1Id;
    @Schema(description = "ID del secondo utente")
    private BigInteger user2Id;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
}
