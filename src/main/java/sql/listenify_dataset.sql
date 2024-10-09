INSERT INTO Artists (artist_first_name, artist_last_name, band) VALUES
                                                                    ('Jack', 'Harlow', FALSE),
                                                                    ('Freddie', 'Mercury', FALSE),
                                                                    ('Post', 'Malone', FALSE),
                                                                    ('Mac', 'Miller', FALSE),
                                                                    ('Billie', 'Eilish', FALSE),
                                                                    ('Kurt', 'Cobain', FALSE),
                                                                    ('Calvin', 'Harris', FALSE),
                                                                    ('David', 'Guetta', FALSE),
                                                                    ('Dua', 'Lipa', FALSE),
                                                                    ('Jason', 'Derulo', FALSE),
                                                                    ('Don', 'Toliver', FALSE),
                                                                    ('Metro', 'Boomin', FALSE),
                                                                    ('J.', 'Cole', FALSE),
                                                                    ('Travis', 'Scott', FALSE),
                                                                    ('', 'OneRepublic', TRUE),
                                                                    ('', 'The Beatles', TRUE),
                                                                    ('', 'Linkin Park', TRUE),
                                                                    ('', 'Coldplay', TRUE),
                                                                    ('', 'Red Hot Chili Peppers', TRUE);

INSERT INTO Genres (genre_name) VALUES
                                    ('Pop'),
                                    ('Rock'),
                                    ('Hip Hop'),
                                    ('Jazz'),
                                    ('Classical'),
                                    ('Electronic'),
                                    ('Country'),
                                    ('Reggae'),
                                    ('Blues'),
                                    ('Folk');

INSERT INTO Albums (artist_id, album_title, release_year) VALUES
                                                              (1, 'Come Home the Kids Miss You', 2022),
                                                              (1, 'That’s What They All Say', 2020),
                                                              (1, 'Sweet Action', 2019),
                                                              (2, 'A Night at the Opera', 1975),
                                                              (2, 'News of the World', 1977),
                                                              (2, 'The Game', 1980),
                                                              (3, 'Beerbongs & Bentleys', 2018),
                                                              (3, 'Hollywood’s Bleeding', 2019),
                                                              (3, 'Stoney', 2016),
                                                              (4, 'Swimming', 2018),
                                                              (4, 'GO:OD AM', 2015),
                                                              (4, 'The Divine Feminine', 2016),
                                                              (5, 'When We All Fall Asleep, Where Do We Go?', 2019),
                                                              (5, 'Happier Than Ever', 2021),
                                                              (6, 'Nevermind', 1991),
                                                              (6, 'In Utero', 1993),
                                                              (6, 'Bleach', 1989),
                                                              (7, '18 Months', 2012),
                                                              (7, 'Motion', 2014),
                                                              (7, 'Funk Wav Bounces Vol. 1', 2017),
                                                              (8, 'Nothing But the Beat', 2011),
                                                              (8, 'Listen', 2014),
                                                              (8, '7', 2018),
                                                              (9, 'Future Nostalgia', 2020),
                                                              (9, 'Dua Lipa', 2017),
                                                              (9, 'Club Future Nostalgia', 2020),
                                                              (10, 'Everything Is 4', 2015),
                                                              (10, 'Jason Derulo', 2013),
                                                              (10, '2Sides', 2019),
                                                              (11, 'Heaven or Hell', 2020),
                                                              (11, 'Life of a Don', 2021),
                                                              (11, 'Flocky Flocky', 2021),
                                                              (12, 'Not All Heroes Wear Capes', 2018),
                                                              (12, 'Savage Mode II', 2020),
                                                              (12, 'Double or Nothing', 2017),
                                                              (13, 'KOD', 2018),
                                                              (13, '2014 Forest Hills Drive', 2014),
                                                              (13, 'The Off-Season', 2021),
                                                              (14, 'Astroworld', 2018),
                                                              (14, 'Rodeo', 2015),
                                                              (14, 'Birds in the Trap Sing McKnight', 2016),
                                                              (15, 'Native', 2013),
                                                              (15, 'Waking Up', 2009),
                                                              (15, 'Oh My My', 2016),
                                                              (16, 'Sgt. Pepper’s Lonely Hearts Club Band', 1967),
                                                              (16, 'Abbey Road', 1969),
                                                              (16, 'Revolver', 1966),
                                                              (17, 'Meteora', 2003),
                                                              (17, 'Hybrid Theory', 2000),
                                                              (17, 'Minutes to Midnight', 2007),
                                                              (18, 'A Rush of Blood to the Head', 2002),
                                                              (18, 'Parachutes', 2000),
                                                              (18, 'Viva la Vida or Death and All His Friends', 2008),
                                                              (19, 'Californication', 1999),
                                                              (19, 'Blood Sugar Sex Magik', 1991),
                                                              (19, 'Stadium Arcadium', 2006);


INSERT INTO Songs (album_id, song_title, duration) VALUES
                                                       (1, 'First Class', '00:03:22'),
                                                       (2, 'Rendezvous', '00:01:53'),
                                                       (3, 'WHATS POPPIN', '00:02:19'),
                                                       (4, 'Love Of My Life', '00:03:37'),
                                                       (5, 'We Will Rock You', '00:02:01'),
                                                       (6, 'Play The Game', '00:03:32'),
                                                       (7, 'Paranoid', '00:03:41'),
                                                       (8, 'Circles', '00:03:35'),
                                                       (9, 'Go Flex', '00:02:59'),
                                                       (10, 'Perfecto', '00:03:35'),
                                                       (11, 'Weekend', '00:03:28'),
                                                       (12, 'Congratulations', '00:04:16'),
                                                       (13, 'xanny', '00:04:03'),
                                                       (14, 'COPYCAT', '00:03:14'),
                                                       (15, 'NDA', '00:03:15'),
                                                       (16, 'Breed', '00:03:03'),
                                                       (17, 'Dumb', '00:02:32'),
                                                       (18, 'Blew', '00:02:55'),
                                                       (19, 'Mansion', '00:02:08'),
                                                       (20, 'Faith', '00:02:35'),
                                                       (21, 'Feels', '00:03:43'),
                                                       (22, 'Titanium', '00:04:05'),
                                                       (23, 'Listen', '00:03:47'),
                                                       (24, 'Flames', '00:03:12'),
                                                       (25, 'Levitating', '00:03:23'),
                                                       (26, 'Genesis', '00:03:28'),
                                                       (27, 'Cool', '00:03:29'),
                                                       (28, 'Cheyenne', '00:03:36'),
                                                       (29, 'What If', '00:03:20'),
                                                       (30, 'Be The One', '00:03:24'),
                                                       (31, 'No Idea', '00:02:33'),
                                                       (32, 'Way Bigger', '00:03:20'),
                                                       (33, 'Flocky Flocky', '00:02:49'),
                                                       (34, 'Overdue', '00:02:37'),
                                                       (35, 'Runnin', '00:03:39'),
                                                       (36, 'So Good', '00:02:50'),
                                                       (37, 'ATM', '00:03:38'),
                                                       (38, 'No Role Modelz', '00:04:52'),
                                                       (39, 'a m a r i', '00:02:29'),
                                                       (40, 'YOSEMITE', '00:02:30'),
                                                       (41, 'Wasted', '00:03:41'),
                                                       (42, 'the ends', '00:03:21'),
                                                       (43, 'Love Runs Out', '00:03:44'),
                                                       (44, 'Made For You', '00:03:45'),
                                                       (45, 'Wherever I Go', '00:02:49'),
                                                       (46, 'Fixing A Hole', '00:02:36'),
                                                       (47, 'Come Together', '00:04:20'),
                                                       (48, 'Love You To', '00:03:00'),
                                                       (49, 'Numb', '00:03:07'),
                                                       (50, 'In The End', '00:03:36'),
                                                       (51, 'No More Sorrow', '00:03:41'),
                                                       (52, 'Clocks', '00:05:07'),
                                                       (53, 'Sparks', '00:03:47'),
                                                       (54, 'Lost!', '00:03:55'),
                                                       (55, 'Californication', '00:05:29'),
                                                       (56, 'Blood Sugar Sex Magik', '00:04:31'),
                                                       (57, 'West Sand', '00:04:28');

INSERT INTO Song_Genres (song_id, genre_id) VALUES
                                                (1, 3),  -- 'First Class' (Hip Hop)
                                                (1, 1),  -- 'First Class' (Pop)
                                                (2, 3),  -- 'Rendezvous' (Hip Hop)
                                                (3, 3),  -- 'WHATS POPPIN' (Hip Hop)
                                                (4, 2),  -- 'Love Of My Life' (Rock)
                                                (5, 2),  -- 'We Will Rock You' (Rock)
                                                (6, 2),  -- 'Play The Game' (Rock)
                                                (7, 2),  -- 'Paranoid' (Rock)
                                                (8, 3),  -- 'Circles' (Hip Hop)
                                                (9, 3),  -- 'Go Flex' (Hip Hop)
                                                (10, 3), -- 'Perfecto' (Hip Hop)
                                                (11, 3), -- 'Weekend' (Hip Hop)
                                                (12, 3), -- 'Congratulations' (Hip Hop)
                                                (13, 1), -- 'xanny' (Pop)
                                                (14, 1), -- 'COPYCAT' (Pop)
                                                (15, 1), -- 'NDA' (Pop)
                                                (16, 2), -- 'Breed' (Rock)
                                                (17, 2), -- 'Dumb' (Rock)
                                                (18, 2), -- 'Blew' (Rock)
                                                (19, 3), -- 'Mansion' (Hip Hop)
                                                (20, 3), -- 'Faith' (Hip Hop)
                                                (21, 1), -- 'Feels' (Pop)
                                                (22, 6), -- 'Titanium' (Electronic)
                                                (23, 6), -- 'Listen' (Electronic)
                                                (24, 6), -- 'Flames' (Electronic)
                                                (25, 1), -- 'Levitating' (Pop)
                                                (26, 1), -- 'Genesis' (Pop)
                                                (27, 1), -- 'Cool' (Pop)
                                                (28, 1), -- 'Cheyenne' (Pop)
                                                (29, 1), -- 'What If' (Pop)
                                                (30, 1), -- 'Be The One' (Pop)
                                                (31, 3), -- 'No Idea' (Hip Hop)
                                                (32, 3), -- 'Way Bigger' (Hip Hop)
                                                (33, 3), -- 'Flocky Flocky' (Hip Hop)
                                                (34, 3), -- 'Overdue' (Hip Hop)
                                                (35, 3), -- 'Runnin' (Hip Hop)
                                                (36, 3), -- 'So Good' (Hip Hop)
                                                (37, 3), -- 'ATM' (Hip Hop)
                                                (38, 3), -- 'No Role Modelz' (Hip Hop)
                                                (39, 3), -- 'a m a r i' (Hip Hop)
                                                (40, 3), -- 'YOSEMITE' (Hip Hop)
                                                (41, 3), -- 'Wasted' (Hip Hop)
                                                (42, 3), -- 'the ends' (Hip Hop)
                                                (43, 1), -- 'Love Runs Out' (Pop)
                                                (44, 1), -- 'Made For You' (Pop)
                                                (45, 1), -- 'Wherever I Go' (Pop)
                                                (46, 2), -- 'Fixing A Hole' (Rock)
                                                (47, 2), -- 'Come Together' (Rock)
                                                (48, 2), -- 'Love You To' (Rock)
                                                (49, 2), -- 'Numb' (Rock)
                                                (50, 2), -- 'In The End' (Rock)
                                                (51, 2), -- 'No More Sorrow' (Rock)
                                                (52, 1), -- 'Clocks' (Pop)
                                                (53, 1), -- 'Sparks' (Pop)
                                                (54, 1), -- 'Lost!' (Pop)
                                                (55, 2), -- 'Californication' (Rock)
                                                (56, 2), -- 'Blood Sugar Sex Magik' (Rock)
                                                (57, 2); -- 'West Sand' (Rock)
