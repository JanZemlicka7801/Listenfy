package business;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    //CREATE TABLE Artists (
    //                         artist_id INT AUTO_INCREMENT PRIMARY KEY,
    //                         artist_first_name VARCHAR(50) ,
    //                         artist_last_name VARCHAR(50) NOT NULL,
    //                         band BOOLEAN DEFAULT FALSE,
    //                         description TEXT
    //);
    @EqualsAndHashCode.Include
    private int artistId;
    private String artistFirstName;
    private String artistLastName;
    private boolean band;
    private String description;
}