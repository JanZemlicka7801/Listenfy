package business;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;


@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //CREATE TABLE Users (
    //                       user_id INT AUTO_INCREMENT PRIMARY KEY,
    //                       username VARCHAR(50) NOT NULL UNIQUE,
    //                       password VARCHAR(255) NOT NULL,
    //                       email VARCHAR(100) NOT NULL UNIQUE,
    //                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    //);
    @EqualsAndHashCode.Include
    private int userId;
    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;

}
