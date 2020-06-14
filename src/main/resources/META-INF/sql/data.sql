INSERT INTO user_types (type) VALUES ("consumer");
INSERT INTO user_types (type) VALUES("manager");

INSERT INTO genres (genre) VALUES('Rap');
INSERT INTO genres (genre) VALUES('Metal');
INSERT INTO genres (genre) VALUES('K-Pop');
INSERT INTO genres (genre) VALUES('Pop');
INSERT INTO genres (genre) VALUES('Rock');
INSERT INTO genres (genre) VALUES('Jazz');

INSERT INTO users (last_name, first_name, address_1, city, province, country, postal_code, home_phone, email, password, last_search_genre, user_type_id) VALUES ('Consumer', 'Dawson', 'address', 'city', 'qc', 'canada', 'postal', '1234567890', 'cst.send@gmail.com', 'dawsoncollege', 1, 1);
INSERT INTO users (last_name, first_name, address_1, city, province, country, postal_code, home_phone, email, password, last_search_genre, user_type_id) VALUES ('Manager', 'Dawson', 'address', 'city', 'qc', 'canada', 'postal', '1234567890', 'cst.receive@gmail.com', 'collegedawson', 1, 2);

INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (1, 'Scorpion', '2018-06-29', 'Cash Money Records', 13, '2019-01-21', 12.25, 15, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (2, 'Ride the Lightning', '1984-07-27', 'Elektra Records', 8, '2019-01-21', 11.98, 15, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (3, 'Kamikaze', '2018-08-31', 'Shady Records', 8, '2019-01-21', 13.98, 17, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (4, 'YES or YES', '2018-11-05', 'JYP Entertainment', 7, '2019-01-21', 8.99, 10, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (5, 'Skool Luv Affair', '2014-02-12', 'Bighit Enternainment', 10, '2019-01-21', 11.99, 13.50, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (6, 'Seventh Heaven', '2009-05-12', 'SME Records', 14, '2019-01-21', 16.99, 20, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (7, 'The Dark Side of the Moon', '1973-03-01', 'Harvest', 10, '2019-01-21', 11.99, 14, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (8, 'A Night in Vienna', '2004-09-30', 'Verve Records', 10, '2019-01-21', 7.99, 10, 0, false, null, 'FilePathGoesHere');
INSERT INTO albums (id, title, release_date, recording_label, number_of_tracks, added_date, cost_price, list_price, sale_price, removed, remove_date, cover_filepath) VALUES (9, 'Birks Works', '1957-04-07', 'Verve Records', 10, '2019-01-21', 17.99, 20, 0, false, null, 'FilePathGoesHere');

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Survival', 'Aubrey Graham, Dion Wilson, Noah Shebib,Klaus Netzle, Manuel Landy', '2:16', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Nonstop', 'Graham, Brytavious Chambers, Wilson', '3:58', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Elevate', 'Graham, Gary Fountaine, Jahron Brathwaite', '3:04', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Emotionless', 'Graham, Mariah Carey,R obert Clivillés, David Cole,Wilson, Shebib, Andrew Gowie', '5:02', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Gods Plan', 'Graham, Daveon Jackson, Matthew Samuels, Shebib, Ronald LaTour, Brock Korsan', '3:19', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Im Upset', 'Graham, Jordan Ortiz', '3:34', 6, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, '8 Out of 10', 'Graham, Samuels, Jahaan Sweet, Matthew OBrien, Abrim Tilmon, Leon Ware, Arthur Ross', '3:15', 7, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Mob Ties', 'Graham, Samuels, Allen Ritter, Tavor Hollins Jr, Dave Atkinson, Samuel Barnes, Anthony Cruz, Nasir Jones, Inga Marchand, Cory McKay, Jean-Claude Olivier', '3:25', 8, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Cant Take a Joke', 'Graham, Max Eberhardt', '9:43', 9, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Sandras Rose', 'Graham, Maneesh Bidaye, Christopher Martin', '3:36', 10, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Talk Up', 'Graham, Shawn Carter, Paul Beauregard,L eroy BonnerOShea Jackson, Marshall Jones, Ralph Middlebrooks, Walter Morisson, Andrew Noland, Gregory Webster, Andre Young', '3:43', 11, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (1, 'Is There More', 'Graham, Wallis Lane, Raynford Humphrey, Jeffrey Rashad, Stephen Garrett, Timothy Mosley', '3:46', 12, 1.29, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (2, 'Fight Fire with fire', 'Cliff Burton, Lars Ulrich, Hetfield', '4:45', 1, 2.50, 1.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (2, 'Ride the Lightning', 'Hetfield, Burton, Ulrich, Dave Mustaine', '6:38', 2, 2.50, 1.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (2, 'for Whom the Bell Tolls', 'Hetfield, Burton, Ulrich', '5:11', 3,2.50, 1.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (2, 'Fade To Black', 'Burton, Hammett, Ulrich, Hetfield', '6:55', 4, 2.50, 1.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'The Ringer', 'Ray Fraser, Matthew Jacobson, Katorah Marrero, Marshall Mathers,Luis Resto, Ronald Spence Jr', '5:37', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Greatest', 'Jordan Carter, Kendrick Duckworth, Asheton Hogan, Jordan Jenks, Mathers, Jeremy Miller, Anthony Tiffith, Michael Williams II, Symere Woods', '3:46', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Lucky You', 'Ray Fraser, Gary Lucas, Mathers, Matthew Samuels, Jahaan Sweet', '4:04', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Stepping Stone', 'Mathers, Luis Resto, Mario Resto', '5:09', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Normal', 'Erik Bodin, Fraser, Larry Griffin Jr, Mathers, Yukimi Nagano, Maurice Nichols, R. Sheldon, Fredrik Wallin, Hċkan Wirenstrand', '3:42', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Kamikaze', 'Bobby Ervin, Mathers, Dwayne Simon, James Smith, Timothy Suby', '3:36', 6, 1.29, 0.99, 0, true, false, null); 
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Fall', 'B. J. Burton, Mathers, L. Resto, Justin Vernon, Williams', '4:22', 7, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Nice Guy', 'Fred Ball, L. Griffin Jr, Mathers, L. Resto, Jessica Reyez', '2:30', 8, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Good Guy', 'Norio Aono, Fraser, Lisa Gomamoto, Mathers, Reyez, Yutaka Yamada', '2:22', 9, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (3, 'Venom', 'Mathers, Luis Resto', '4:29', 10, 1.29, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'YES or YES', 'Shim Eunji', '3:59', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'SAY YOU LOVE ME', 'Sophiya, Secret Weapon', '3:34', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'LALALA', 'Jeongyeon (TWICE)', '3:08', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'YOUNG & WILD','Chaeyoung (TWICE), Flying Lab', '3:03', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'SUNSET', 'Jihyo (TWICE)', '3:44', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'AFTER MOON', 'Yong-bae, Lee Gi (OREO), C-no (Korea)', '3:25', 6, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (4, 'BDZ (Korean Ver.)', 'J.Y. Park', '3:16', 7, 1.29, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Intro: Skool Luv Affair','Pdogg, Slow Rabbit, RM, Suga, J-Hope', '2:58', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Boy in Luv','Pdogg, "Hitman" Bang, RM, Suga, Supreme Boi', '3:50', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Skit: Soulmate', 'BTS', '1:32', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Where You From', 'Cream, RM, Suga, J-Hope', '4:00', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Just One Day', 'Pdogg, RM, Suga, J-Hope', '3:59', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Tomorrow', 'Suga, Slow Rabbit, RM, J-Hope', '4:21', 6, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'BTS Cypher, Pt. 2: Triptych','Supreme Boi, RM, Suga, J-Hope', '4:48', 7, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Spine Breaker', 'Pdogg, OWO, RM, Suga, J-Hope, Slow Rabbi, Supreme Boi', '3:58', 8, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Jump', 'Suga, Pdogg, Supreme Boi, RM, J-Hope', '3:56', 9, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (5, 'Outro: Propose', 'Slow Rabbit, Pdogg, Jin', '2:02', 10, 1.29, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Ouverture', 'Yuki Kajiura', '1:34', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Oblivious', 'Yuki Kajiura', '4:22', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Love Come Down', 'Yuki Kajiura', '4:44', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Natsu No Ringo', 'Yuki Kajiura', '4:02', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Fairytale', 'Yuki Kajiura', '5:13', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Aria', 'Yuki Kajiura', '5:23', 6, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Mata Kaze Ga Tsuyokunatta', 'Yuki Kajiura', '4:56', 7, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Kizuato', 'Yuki Kajiura', '4:39', 8, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Sernato', 'Yuki Kajiura', '4:53', 9, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Ongaku', 'Yuki Kajiura', '5:38', 10, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Ashita No Keshiki', 'Yuki Kajiura', '5:25', 11, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Sprinter', 'Yuki Kajiura', '5:04', 12, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Kimi Ga Hikari Ni Kaeteiku', 'Yuki Kajiura', '4:42', 13, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (6, 'Seventh Heaven', 'Yuki Kajiura', '6:12', 14, 1.29, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Speak to Me', 'Nick Mason', '1:30', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Breathe', 'Roger Waters, David Gilmour, Richard Wright', '2:43', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'On the Run', 'Roger Waters, David Gilmour', '3:30', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Time', 'Roger Waters, David Gilmour, Richard Wright, Nick Mason', '6:53', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'The Great Gig in the Sky', 'Richard Wright, Clare Torry', '4:15', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Money', 'Roger Waters', '6:30', 6, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Us and Them', 'Roger Waters, Richard Wright', '7:51', 7, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Any Colour You Like', 'David Gilmour, Nick Mason, Richard Wright', '3:24', 8, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Brain Damage', 'Roger Waters', '3:50', 9, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (7, 'Eclipse', 'Roger Waters', '2:03', 10, 1.29, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Intro', 'Oscar Peterson', '0:58', 1, 0.99, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Night Time', 'Oscar Peterson', '8:35', 2, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'When Summer Comes', 'Oscar Peterson', '7:10', 3, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Cakewalk', 'Oscar Peterson', '8:31', 4, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Requiem', 'Oscar Peterson', '8:49', 5, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Wheatland', 'Oscar Peterson', '9:19', 6, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'The Backyard Blues', 'Oscar Peterson', '8:13', 7, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Satin Doll', 'Duke Ellington, Johnny Mercer, Billy Strayhorn', '9:48', 8, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Sweet Georgia Brown', 'Ben Bernie, Kenneth Casey, Maceo Pinkard', '8:23', 9, 0.99, 0.99, 0, false, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (8, 'Hymn to Freedom', 'Oscar Peterson', '7:56', 10, 0.99, 0.99, 0, true, false, null);

insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Jordu', 'Duke Jordan', '4:13', 1, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Birks Works', 'Dizzy Gillespie', '4:56', 2, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Umbrella Man', 'Vincent Rose, Larry Stock, James Cavanaugh', '3:02', 3, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Autumn Leaves', 'Joseph Kosma, Jacques Prévert, Johnny Mercer', '3:11', 4, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Tangerine', 'Victor Schertzinger', '3:43', 5, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Over the Rainbow', 'Harold Arlen, Yip Harburg', '4:37', 6, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Yo No Quiero Bailar', 'Joe Willoughby', '4:42', 7, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'If You Could See Me Now', "Tadd Dameron, Carl Sigman", '3:30', 8, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Left Hand Corner', 'Ernie Wilkins', '2:26', 9, 1.29, 0.99, 0, true, false, null);
insert into tracks (album_id, title, songwriter, play_length, album_selection, cost_price, list_price, sale_price, sellable_as_single, removed, remove_date) VALUES (9, 'Whisper Not', "Benny Golson, Leonard Feather", '5:17', 10, 1.29, 0.99, 0, true, false, null);

INSERT INTO artists (artist_name) VALUES ('Pink Floyd');
INSERT INTO artists (artist_name) VALUES ('Oscar Peterson');
INSERT INTO artists (artist_name) VALUES ('Dizzy Gillespie');
INSERT INTO artists (artist_name) VALUES ('TWICE');
INSERT INTO artists (artist_name) VALUES ('BTS');
INSERT INTO artists (artist_name) VALUES ('Kalafina');
INSERT INTO artists (artist_name) VALUES ('Drake');
INSERT INTO artists (artist_name) VALUES ('Metallica');
INSERT INTO artists (artist_name) VALUES ('Eminem');

INSERT INTO albums_genre (album_id, genre_id) VALUES (1, 1);
INSERT INTO albums_genre (album_id, genre_id) VALUES (2, 2);
INSERT INTO albums_genre (album_id, genre_id) VALUES (3, 1);
INSERT INTO albums_genre (album_id, genre_id) VALUES (4, 3);
INSERT INTO albums_genre (album_id, genre_id) VALUES (5, 3);
INSERT INTO albums_genre (album_id, genre_id) VALUES (6, 4);
INSERT INTO albums_genre (album_id, genre_id) VALUES (7, 5);
INSERT INTO albums_genre (album_id, genre_id) VALUES (8, 5);
INSERT INTO albums_genre (album_id, genre_id) VALUES (9, 6);

INSERT INTO albums_artist (album_id, artist_id) VALUES (1,7);
INSERT INTO albums_artist (album_id, artist_id) VALUES (2,8);
INSERT INTO albums_artist (album_id, artist_id) VALUES (3,9);
INSERT INTO albums_artist (album_id, artist_id) VALUES (4,4);
INSERT INTO albums_artist (album_id, artist_id) VALUES (5,5);
INSERT INTO albums_artist (album_id, artist_id) VALUES (6,6);
INSERT INTO albums_artist (album_id, artist_id) VALUES (7,1);
INSERT INTO albums_artist (album_id, artist_id) VALUES (8,2);
INSERT INTO albums_artist (album_id, artist_id) VALUES (9,3);