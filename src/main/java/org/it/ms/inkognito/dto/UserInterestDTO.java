package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare l'associazione tra utente e interesse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta l'associazione tra utente e interesse")
public class UserInterestDTO {
    @Schema(description = "ID composto da userId e interestId")
    private UserInterestIdDTO id;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
    @Schema(description = "Data di ultima modifica")
    private LocalDateTime dateUpdate;
    @Schema(description = "Utente che ha inserito il record")
    private String userInsert;
    @Schema(description = "Utente che ha modificato il record")
    private String userUpdate;
}
