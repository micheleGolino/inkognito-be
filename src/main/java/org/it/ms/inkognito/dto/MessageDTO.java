package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare un messaggio tra utenti.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta un messaggio tra utenti")
public class MessageDTO {
    @Schema(description = "ID univoco del messaggio")
    private Long id;
    @Schema(description = "ID utente mittente")
    private Long senderId;
    @Schema(description = "ID utente destinatario")
    private Long receiverId;
    @Schema(description = "Contenuto del messaggio")
    private String content;
    @Schema(description = "Data e ora di invio")
    private LocalDateTime sentAt;
}
