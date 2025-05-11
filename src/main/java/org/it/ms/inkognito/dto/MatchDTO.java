package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare un match tra due utenti.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta un match tra due utenti")
public class MatchDTO {
    @Schema(description = "ID del primo utente")
    private Long userId1;
    @Schema(description = "ID del secondo utente")
    private Long userId2;
    @Schema(description = "Data e ora del match")
    private String matchedAt;
}
