package emt.labs.labEmt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorDto {

    private String name;

    private String surname;

    private Long countryId;
}
