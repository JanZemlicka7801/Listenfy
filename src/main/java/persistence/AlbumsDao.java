package persistence;

import business.Albums;

import java.util.List;

public interface AlbumsDao {
    List<Albums> getAlbumsByArtistId(int artistId);
}
