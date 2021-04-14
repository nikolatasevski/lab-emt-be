package emt.labs.labEmt.model.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponseDto implements Serializable {

    private String accessToken;

    private String tokenType = "Bearer";

    private String username;

    private List<String> roles;

}
