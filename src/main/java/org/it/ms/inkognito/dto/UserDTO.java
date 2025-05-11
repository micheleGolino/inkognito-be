package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare un utente dell'app di dating.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta un utente dell'app di dating")
public class UserDTO {
    @Schema(description = "ID univoco dell'utente")
    private BigInteger id;
    @Schema(description = "Email dell'utente")
    private String email;
    @Schema(description = "Nome dell'utente")
    private String firstName;
    @Schema(description = "Cognome dell'utente")
    private String lastName;
    @Schema(description = "Username dell'utente")
    private String username;
    @Schema(description = "Biografia dell'utente")
    private String bio;
    @Schema(description = "URL immagine profilo")
    private String imageUrl;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
    // altri campi utili per il mobile
}
