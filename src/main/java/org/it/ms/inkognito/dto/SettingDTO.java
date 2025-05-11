package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare le impostazioni utente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta le impostazioni di un utente")
public class SettingDTO {
    @Schema(description = "ID univoco delle impostazioni")
    private BigInteger id;
    @Schema(description = "ID utente associato")
    private BigInteger userId;
    @Schema(description = "Codice lingua")
    private String language;
    @Schema(description = "Tema preferito")
    private String theme;
    @Schema(description = "Notifiche push abilitate")
    private Boolean pushNotificationsEnabled;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
}
