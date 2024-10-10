package business;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    //CREATE TABLE Genres (
    //                        genre_id INT AUTO_INCREMENT PRIMARY KEY,
    //                        genre_name VARCHAR(50) NOT NULL UNIQUE
    //);
    @EqualsAndHashCode.Include
    private int genreId;
    private String genre_name;
}
