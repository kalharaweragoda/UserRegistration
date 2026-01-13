package dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String reenterPassword;
}
