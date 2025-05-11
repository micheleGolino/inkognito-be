package org.it.ms.inkognito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * DTO per rappresentare un interesse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO che rappresenta un interesse")
public class InterestDTO {
    @Schema(description = "ID univoco dell'interesse")
    private BigInteger id;
    @Schema(description = "Nome dell'interesse")
    private String name;
    @Schema(description = "Descrizione dell'interesse")
    private String description;
    @Schema(description = "Note aggiuntive")
    private String note;
    @Schema(description = "Data di inserimento")
    private LocalDateTime dateInsert;
}
