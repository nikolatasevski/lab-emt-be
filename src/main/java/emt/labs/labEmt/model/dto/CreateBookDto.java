package emt.labs.labEmt.model.dto;

import emt.labs.labEmt.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {

    private String name;

    private Integer availableCopies;

    private Category category;

    private Long authorId;
}
