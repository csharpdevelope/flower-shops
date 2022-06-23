package uz.example.flower.payload.response;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private Long id;
    @NotNull
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String role;
    private String url;
}
