package persistence;

import business.Artist;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDaoTest {

    @Test
    void getAllArtists_AllFound() throws SQLException {
        ArtistDao artistDao = new ArtistDaoImpl("test.database.properties");

        List<Artist> expected = generateExpectedArtists();

        List<Artist> results = artistDao.getAllArtists();

        assertEquals(expected.size(), results.size(), "The number of artists returned is incorrect.");

        for (Artist expectedArtist : expected) {
            assertTrue(results.contains(expectedArtist), "Expected artist not found in the results: " + expectedArtist);
        }
    }

    List<Artist> generateExpectedArtists() {
        List<Artist> artists = new ArrayList<>();

        artists.add(new Artist(1, "Jack", "Harlow", false, "American rapper, singer, and songwriter known for his unique blend of hip hop and pop."));
        artists.add(new Artist(2, "Freddie", "Mercury", false, "Legendary lead vocalist of the rock band Queen, known for his flamboyant stage presence."));
        artists.add(new Artist(3, "Post", "Malone", false, "American singer, songwriter, and record producer known for his diverse musical style."));
        artists.add(new Artist(4, "Mac", "Miller", false, "American rapper and singer known for his introspective lyrics and unique sound."));
        artists.add(new Artist(5, "Billie", "Eilish", false, "Grammy Award-winning singer known for her unique voice and distinct style."));
        artists.add(new Artist(6, "Kurt", "Cobain", false, "Lead singer and guitarist of the band Nirvana, known for his influential role in the grunge movement."));
        artists.add(new Artist(7, "Calvin", "Harris", false, "Scottish DJ and record producer known for his dance and electronic music."));
        artists.add(new Artist(8, "David", "Guetta", false, "French DJ known for his electronic music and collaborations with various artists."));
        artists.add(new Artist(9, "Dua", "Lipa", false, "English singer and songwriter known for her pop hits and powerful vocals."));
        artists.add(new Artist(10, "Jason", "Derulo", false, "American singer and songwriter known for his catchy pop and R&B tracks."));
        artists.add(new Artist(11, "Don", "Toliver", false, "American rapper and singer known for his melodic style and collaborations."));
        artists.add(new Artist(12, "Metro", "Boomin", false, "American record producer known for his work in hip-hop and trap music."));
        artists.add(new Artist(13, "J.", "Cole", false, "American rapper known for his deep lyrics and storytelling ability."));
        artists.add(new Artist(14, "Travis", "Scott", false, "American rapper and producer known for his innovative approach to music."));
        artists.add(new Artist(15, null, "OneRepublic", true, "American pop rock band known for their hit singles and dynamic sound."));
        artists.add(new Artist(16, null, "The Beatles", true, "Iconic English rock band known for revolutionizing modern music."));
        artists.add(new Artist(17, null, "Linkin Park", true, "American rock band known for their fusion of nu-metal and electronic music."));
        artists.add(new Artist(18, null, "Coldplay", true, "British rock band known for their melodic music and anthemic hits."));
        artists.add(new Artist(19, null, "Red Hot Chili Peppers", true, "American rock band known for their energetic performances and funk-rock sound."));


        return artists;
    }
}