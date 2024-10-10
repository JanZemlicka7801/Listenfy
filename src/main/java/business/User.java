package business;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class User {
    //CREATE TABLE Users (
    //                       user_id INT AUTO_INCREMENT PRIMARY KEY,
    //                       username VARCHAR(50) NOT NULL UNIQUE,
    //                       password VARCHAR(255) NOT NULL,
    //                       email VARCHAR(100) NOT NULL UNIQUE,
    //                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    //);

    private int userId;
    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;


    public User() {
    }

    //UserId should be handled by database
    public User(String username, String password, String email, LocalDate registrationDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
