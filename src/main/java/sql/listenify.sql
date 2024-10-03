DROP DATABASE IF EXISTS listenify;
CREATE DATABASE IF NOT EXISTS listenify;

USE listenify;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS Artists;
CREATE TABLE Artists (
                         artist_id INT AUTO_INCREMENT PRIMARY KEY,
                         artist_name VARCHAR(100) NOT NULL,
                         genre VARCHAR(50)
);

DROP TABLE IF EXISTS Albums;
CREATE TABLE Albums (
                        album_id INT AUTO_INCREMENT PRIMARY KEY,
                        artist_id INT NOT NULL,
                        album_title VARCHAR(100) NOT NULL,
                        release_date DATE,
                        FOREIGN KEY (artist_id) REFERENCES Artists(artist_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Songs;
CREATE TABLE Songs (
                       song_id INT AUTO_INCREMENT PRIMARY KEY,
                       album_id INT NOT NULL,
                       song_title VARCHAR(100) NOT NULL,
                       duration TIME,
                       FOREIGN KEY (album_id) REFERENCES Albums(album_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Playlists;
CREATE TABLE Playlists (
                           playlist_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL,
                           playlist_name VARCHAR(100) NOT NULL,
                           is_public BOOLEAN DEFAULT FALSE,
                           creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Playlist_Songs;
CREATE TABLE Playlist_Songs (
                                playlist_id INT NOT NULL,
                                song_id INT NOT NULL,
                                PRIMARY KEY (playlist_id, song_id),
                                FOREIGN KEY (playlist_id) REFERENCES Playlists(playlist_id) ON DELETE CASCADE,
                                FOREIGN KEY (song_id) REFERENCES Songs(song_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Ratings;
CREATE TABLE Ratings (
                         user_id INT NOT NULL,
                         song_id INT NOT NULL,
                         rating INT CHECK (rating BETWEEN 1 AND 5),
                         PRIMARY KEY (user_id, song_id),
                         FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
                         FOREIGN KEY (song_id) REFERENCES Songs(song_id) ON DELETE CASCADE
);
