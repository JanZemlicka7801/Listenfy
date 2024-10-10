package business;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class Albums {
    //CREATE TABLE Albums (
    //                        album_id INT AUTO_INCREMENT PRIMARY KEY,
    //                        artist_id INT NOT NULL,
    //                        album_title VARCHAR(100) NOT NULL,
    //                        release_year YEAR,
    //                        FOREIGN KEY (artist_id) REFERENCES Artists(artist_id) ON DELETE CASCADE
    //);
    @EqualsAndHashCode.Include
    private int album_id;
    private int artist_id;
    private String album_title;
    private int release_year;
}