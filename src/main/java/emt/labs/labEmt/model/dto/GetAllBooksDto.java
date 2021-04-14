package emt.labs.labEmt.model.dto;

import emt.labs.labEmt.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBooksDto {

    private Long id;

    private String name;

    private Category category;

    private Integer availableCopies;

    private String authorName;

    private String authorSurname;

    private Long authorId;
}
