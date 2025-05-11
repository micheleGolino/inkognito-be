package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare le preferenze utente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta le preferenze di un utente")
public class PreferenceDTO {
    @Schema(description = "ID univoco della preferenza")
    private BigInteger id;
    @Schema(description = "ID utente associato")
    private BigInteger userId;
    @Schema(description = "Genere desiderato per il matching")
    private String desiredGender;
    @Schema(description = "Distanza massima accettata")
    private Integer maxDistance;
    @Schema(description = "Età minima preferita")
    private Integer minAge;
    @Schema(description = "Età massima preferita")
    private Integer maxAge;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
}
