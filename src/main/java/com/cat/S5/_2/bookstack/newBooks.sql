-- Disable FK to avoid order issues
SET FOREIGN_KEY_CHECKS = 0;

-- Clean existing data
TRUNCATE TABLE books;
TRUNCATE TABLE authors;
TRUNCATE TABLE genres;
TRUNCATE TABLE books_authors;
TRUNCATE TABLE books_genres;

SET FOREIGN_KEY_CHECKS = 1;

-- Insert genres
INSERT IGNORE INTO genres (id, name) VALUES
(1,'Fiction'), (2,'Novella'), (3,'Short Stories'), (4,'Poetry'),
(5,'Folktales'), (6,'History/Essay'), (7,'Memoir'), (8,'Graphic'),
(9,'Journalism'), (10,'Cookbook'), (11,'Essay');

-- Insert authors with made-up IDs (adapte selon ta base)
INSERT IGNORE INTO authors (author_id, first_name, last_name) VALUES
(100, 'Susan', 'Abulhawa'),
(101, 'Hala', 'Alyan'),
(102, 'Adania', 'Shibli'),
(103, 'Sahar', 'Khalifeh'),
(104, 'Ghassan', 'Kanafani'),
(105, 'Isabella', 'Hammad'),
(106, 'Etaf', 'Rum'),
(107, 'Zaina', 'Arafat'),
(108, 'Susan Muaddi', 'Darraj'),
(109, 'Huzama', 'Habayeb'),
(110, 'Sahar', 'Mustafah'),
(111, 'Suad', 'Amiry'),
(112, 'Randa', 'Jarrar'),
(113, 'Mahmoud', 'Darwish'),
(114, 'Mosab', 'Abu Toha'),
(115, 'Ibrahim Muhawi', ''),
(116, 'Nayrouz', 'Qarmout'),
(117, 'Ghada', 'Karmi'),
(118, 'Ramzy', 'Baroud'),
(119, 'Edward W.', 'Said'),
(120, 'Naji', 'al-Ali'),
(121, 'Raja', 'Shehadeh'),
(122, 'Rashid', 'Khalidi'),
(123, 'Ilan', 'Pappé'),
(124, 'Baruch', 'Kimmerling'),
(125, 'Joel', 'Migdal'),
(126, 'Jean-Pierre', 'Filiu'),
(127, 'Leila', 'Khaled'),
(128, 'Amira', 'Hass'),
(129, 'Laila el-Haddad', ''),
(130, 'Maggie', 'Schmitt'),
(131, 'Ahed', 'Tamimi'),
(132, 'Dena', 'Takruri'),
(133, 'NS', 'Nuseibeh');

-- Insert books
INSERT INTO books (book_id, title, description, publication_year, language, isbn) VALUES
(200, 'Mornings in Jenin','Multi‑generational Palestinian family saga post‑Nakba',2010,'English','9781608190461'),
(201, 'Against the Loveless World','Portrait of a woman born of Palestinian refugee parents',2020,'English',NULL),
(202, 'The Blue Between Sky and Water','Historical fiction set in Palestine & America',2015,'English',NULL),
(203, 'Salt Houses','Historical fiction following four generations of a Palestinian family',2017,'English','9781328915856'),
(204, 'Minor Detail','Meditation on a 1949 crime and memory in Palestine',2020,'English','9781913097172'),
(205, 'Wild Thorns','Life under occupation in Nablus in 1972',1976,'English',NULL),
(206, 'Of Noble Origins','Social commentary post‑1967 war',1981,'English',NULL),
(207, 'Passage to the Plaza','Family saga against backdrop of occupation',2005,'English',NULL),
(208, 'My First and Only Love','Intimate portrayal of love and exile',2001,'English',NULL),
(209, 'Returning to Haifa','Novella exploring return and memory after Nakba',1969,'English',NULL),
(210, 'Men in the Sun','Stories of Palestinian refugees and migration',1962,'English',NULL),
(211, 'The Land of Sad Oranges','Nostalgic stories of pre‑Nakba Palestine',1963,'English',NULL),
(212, 'All That''s Left to You','Novella about displacement and resistance',1966,'English',NULL),
(213, 'The Lover','Partially‑published novel about Palestinian identity',1971,'English',NULL),
(214, 'The Parisian','Historical fiction of Palestinian return to Paris',2022,'English',NULL),
(215, 'Enter Ghost','Haunted theatre production intertwining politics & identity',2024,'English','9781529919998'),
(216, 'A Woman Is No Man','Impact of tradition on Palestinian‑American women',2019,'English',NULL),
(217, 'You Exist Too Much','Queer Palestinian‑American coming‑of‑age story',2020,'English',NULL),
(218, 'The Inheritance of Exile','Short stories about Palestinian diaspora',2020,'English',NULL),
(219, 'A Curious Land','Family history and Palestinian refugee experience',2019,'English',NULL),
(220, 'Velvet','Exile, identity, and personal history in Palestine',2011,'English',NULL),
(221, 'The Beauty of Your Face','Intersecting lives in refugee camps',2018,'English',NULL),
(222, 'Behind You Is the Sea','Stories of Palestinian and Lebanese women',2019,'English',NULL),
(223, 'Mother of Strangers','Life in exile and return to Palestine',2019,'English',NULL),
(224, 'A Map of Home','Coming‑of‑age in immigrant family',2003,'English',NULL),
(225, 'In the Presence of Absence','Poetry and prose on exile and memory',2006,'English',NULL),
(226, 'Memory for Forgetfulness','Prose poem on the 1982 Siege of Beirut',1982,'English',NULL),
(227, 'Forest of Noise','Poetry from Gaza expressing life under siege',2023,'English',NULL),
(228, 'Speak, Bird, Speak Again','Palestinian folktales retold',2012,'English',NULL),
(229, 'The Sea Cloak and Other Stories','Modern Palestinian short stories',2017,'English',NULL),
(230, 'In Search of Fatima','Memoir of Palestinian doctor exiled from Jerusalem',2002,'English',NULL),
(231, 'Return','Continuation of Fatima memoir on return',2004,'English',NULL),
(232, 'My Father Was a Freedom Fighter','Journalistic family memoir',2010,'English',NULL),
(233, 'Out of Place','Autobiography of Palestinian‑American identity',1999,'English',NULL),
(234, 'The Question of Palestine','Classic political essay on Palestinian nationalism',1979,'English',NULL),
(235, 'Zionism from the Standpoint of Its Victims','Critical essay on Zionism',1979,'English',NULL),
(236, 'A Child in Palestine','Political cartoon & memoir',1970,'English',NULL),
(237, 'Palestinian Walks','Reflections on geography, law & identity',2008,'English',NULL),
(238, 'The Hundred Years'' War on Palestine','History of settler‑colonialism and resistance',2020,'English','9781781259344'),
(239, 'The Iron Cage','History of the Palestinian struggle for statehood',2006,'English',NULL),
(240, 'Palestinian Identity','Study of society under occupation',1997,'English',NULL),
(241, 'The Forgotten Palestinians','Accounts of refugees post‑1948',2002,'English',NULL),
(242, 'The Ethnic Cleansing of Palestine','Critical history of Nakba and expulsion',2006,'English',NULL),
(243, 'The Palestinian People: A History','Comprehensive history of Palestinian nationalism',2000,'English',NULL),
(244, 'Gaza: A History','History of Gaza from Ottoman era to present',2009,'English',NULL),
(245, 'On the Front Lines of the Palestinian Revolution','Memoir by Leila Khaled',1973,'English',NULL),
(246, 'Drinking the Sea at Gaza','Journalistic accounts from Gaza',2012,'English',NULL),
(247, 'A Gaza Kitchen','Recipes & stories from Gaza',2018,'English',NULL),
(248, 'They Called Me a Lioness','Memoir of activist jailed by Israel',2022,'English',NULL),
(249, 'Namesake','Essays on identity, feminism & heritage',2024,'English',NULL);

-- Relationship tables
INSERT INTO books_authors (book_id, author_id) VALUES
(200,100),(201,100),(202,100),(203,101),(204,102),
(205,103),(206,103),(207,103),(208,103),(209,104),
(210,104),(211,104),(212,104),(213,104),(214,105),
(215,105),(216,106),(217,107),(218,108),(219,108),
(220,109),(221,110),(222,108),(223,111),(224,112),
(225,113),(226,113),(227,114),(228,115),(229,116),
(230,117),(231,117),(232,118),(233,119),(234,119),
(235,119),(236,120),(237,121),(238,122),(239,122),
(240,122),(241,123),(242,123),(243,124),(243,125),
(244,126),(245,127),(246,128),(247,129),(247,130),
(248,131),(248,132),(249,133);

INSERT INTO books_genres (book_id, genre_id) VALUES
(200,1),(201,1),(202,1),(203,1),(204,1),
(205,1),(206,1),(207,1),(208,1),(209,1),
(210,3),(211,3),(212,2),(213,1),(214,1),
(215,1),(216,1),(217,1),(218,1),(219,1),
(220,1),(221,1),(222,1),(223,1),(224,1),
(225,4),(226,4),(227,4),(228,5),(229,3),
(230,7),(231,7),(232,7),(233,7),(234,6),
(235,6),(236,8),(237,7),(238,6),(239,6),
(240,6),(241,6),(242,6),(243,6),(244,6),
(245,7),(246,9),(247,10),(248,7),(249,11);
