package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per risposte semplici (es. token, messaggi di stato).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO per risposte semplici come token o messaggi di stato")
public class SimpleResponseDTO {
    @Schema(description = "Messaggio o token restituito dalla risposta")
    private String message;
}
