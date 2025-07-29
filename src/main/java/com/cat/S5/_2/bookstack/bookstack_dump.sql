-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 28-07-2025 a las 13:43:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bookstack`
--
CREATE DATABASE IF NOT EXISTS `bookstack`;
USE `bookstack`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authors`
--

CREATE TABLE `authors` (
  `author_id` bigint(20) NOT NULL,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `authors`
--

INSERT INTO `authors` (`author_id`, `first_name`, `last_name`) VALUES
(115, 'Ibrahim Muhawi', ''),
(129, 'Laila el-Haddad', ''),
(114, 'Mosab', 'Abu Toha'),
(13, 'Susan', 'Abulhawa'),
(120, 'Naji', 'al-Ali'),
(101, 'Hala', 'Alyan'),
(111, 'Suad', 'Amiry'),
(107, 'Zaina', 'Arafat'),
(3, 'Jane', 'Austen'),
(118, 'Ramzy', 'Baroud'),
(1, 'Agatha', 'Christie'),
(108, 'Susan Muaddi', 'Darraj'),
(113, 'Mahmoud', 'Darwish'),
(11, 'Charles', 'Dickens'),
(9, 'Arthur Conan', 'Doyle'),
(126, 'Jean-Pierre', 'Filiu'),
(8, 'F. Scott', 'Fitzgerald'),
(109, 'Huzama', 'Habayeb'),
(105, 'Isabella', 'Hammad'),
(128, 'Amira', 'Hass'),
(112, 'Randa', 'Jarrar'),
(12, 'You-Jeong', 'JEONG'),
(104, 'Ghassan', 'Kanafani'),
(117, 'Ghada', 'Karmi'),
(127, 'Leila', 'Khaled'),
(122, 'Rashid', 'Khalidi'),
(103, 'Sahar', 'Khalifeh'),
(124, 'Baruch', 'Kimmerling'),
(6, 'Stephen', 'King'),
(5, 'Harper', 'Lee'),
(136, 'Naguib', 'Mahfouz'),
(125, 'Joel', 'Migdal'),
(110, 'Sahar', 'Mustafah'),
(133, 'NS', 'Nuseibeh'),
(2, 'Georges', 'Orwell'),
(123, 'Ilan', 'Pappé'),
(116, 'Nayrouz', 'Qarmout'),
(7, 'J.K.', 'Rowling'),
(106, 'Etaf', 'Rum'),
(119, 'Edward W.', 'Said'),
(134, 'aziz', 'sanhaji'),
(130, 'Maggie', 'Schmitt'),
(121, 'Raja', 'Shehadeh'),
(102, 'Adania', 'Shibli'),
(132, 'Dena', 'Takruri'),
(131, 'Ahed', 'Tamimi'),
(4, 'J.R.R.', 'Tolkien'),
(10, 'Mark', 'Twain');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `books`
--

CREATE TABLE `books` (
  `book_id` bigint(20) NOT NULL,
  `description` text DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `publication_year` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `books`
--

INSERT INTO `books` (`book_id`, `description`, `image_url`, `isbn`, `language`, `publication_year`, `title`) VALUES
(1, 'Christie\'s masterpiece !!!!\nTen strangers, each lured to a remote island, discover they are trapped with a killer.', 'https://m.media-amazon.com/images/I/61oDiFbhrhL._SL1500_.jpg', '9780062073488', 'English', 1940, 'And Then There Were None'),
(2, 'A dystopian social science fiction novel and cautionary tale.', 'https://m.media-amazon.com/images/I/71sOSrd+JxL._SL1500_.jpg', '9780451524935', NULL, 1949, '1984'),
(3, 'A classic novel of manners, love, and societal expectations in 19th-century England.', 'https://m.media-amazon.com/images/I/81a3sr-RgdL._SL1500_.jpg', '9780141439518', NULL, 1813, 'Pride and Prejudice'),
(4, 'A fantasy novel by J.R.R. Tolkien, serving as a prequel to The Lord of the Rings.', 'https://images-na.ssl-images-amazon.com/images/I/91b0C2YNSrL.jpg', '9780345339683', 'English', 1937, 'The Hobbit'),
(5, 'A novel about the serious issues of rape and racial inequality.', 'https://m.media-amazon.com/images/I/51tDHl8Z7cL.jpg', '9780446310789', NULL, 1960, 'To Kill a Mockingbird'),
(6, 'A horror novel about a family looking after an isolated hotel during winter.', 'https://m.media-amazon.com/images/I/71Og9BY-IOL._SL1500_.jpg', '9780345806789', NULL, 1977, 'The Shining'),
(7, 'The first novel in the Harry Potter series, introducing the wizarding world.', 'https://m.media-amazon.com/images/I/A1jGvzIZ7ZL._SL1500_.jpg', '9780590353427', NULL, 1997, 'Harry Potter and the Sorcerer\'s Stone'),
(8, 'A novel portraying the Roaring Twenties in America.', 'https://m.media-amazon.com/images/I/61HCFHAYbkL._SL1491_.jpg', '9780743273565', NULL, 1925, 'The Great Gatsby'),
(9, 'The first story to feature Sherlock Holmes and Dr. Watson.', 'https://m.media-amazon.com/images/I/61hCD8nIP9L._SL1499_.jpg', '9781503290685', NULL, 1887, 'A Study in Scarlet'),
(10, 'A classic American novel about a boy growing up along the Mississippi River.', 'https://m.media-amazon.com/images/I/71OjPWzSgSL._SL1500_.jpg', '9780486280590', NULL, 1876, 'The Adventures of Tom Sawyer'),
(12, 'Sherlock Holmes investigates a mysterious curse.', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1355929358i/8921.jpg', '9780486272557', NULL, 1902, 'The Hound of the Baskervilles'),
(13, 'Stephen King\'s epic post-apocalyptic dark fantasy novel.', 'https://m.media-amazon.com/images/I/41rdqA06gfS.jpg', '9780385199577', NULL, 1978, 'The Stand'),
(15, 'Hercule Poirot investigates a murder on a train.', 'https://m.media-amazon.com/images/I/71gziNlBHiL._SL1500_.jpg', '9780062073495', NULL, 1934, 'Murder on the Orient Express'),
(17, 'Mornings in Jenin is a devastating novel of love and loss, war and oppression, and heartbreak and hope, spanning five countries and four generations of one of the most intractable conflicts of our lifetime.\n\nPalestine, 1948. Half a million Palestinians are forced from their homes. A mother clutches her six-month-old son as Israeli soldiers march through the village of Ein Hod. In a split second, her son is snatched from her arms and the fate of the Abulheja family is changed forever.\n\nForced into a refugee camp in Jenin and exiled from the ancient village that is their lifeblood, the family struggles to rebuild their world. Their stories unfold through the eyes of the youngest sibling, Amal, the daughter born in the camp who will eventually find herself alone in the United States; the eldest son who loses everything in the struggle for freedom; the stolen son who grows up as an Israeli, becoming an enemy soldier to his own brother.\n\n\'The writer\'s pain - and the beauty of her prose - are very real\' Telegraph', 'https://m.media-amazon.com/images/I/81bbqBJMScL._SL1500_.jpg', '1408809486', 'English', 2011, 'Mornings in Jenin'),
(201, 'Portrait of a woman born of Palestinian refugee parents', 'https://m.media-amazon.com/images/I/81fFJ6Xg9ZL._SL1500_.jpg', '1982137045', NULL, 2020, 'Against the Loveless World'),
(202, 'Historical fiction set in Palestine & America', 'https://m.media-amazon.com/images/I/81mBp4DqliL._SL1500_.jpg', '1410487458', NULL, 2015, 'The Blue Between Sky and Water'),
(203, 'Historical fiction following four generations of a Palestinian family', 'https://m.media-amazon.com/images/I/81IsxHbCx-L._SL1500_.jpg', '9781328915856', NULL, 2017, 'Salt Houses'),
(204, 'Meditation on a 1949 crime and memory', 'https://m.media-amazon.com/images/I/81OpqPFxgoL._SL1200_.jpg', '9781913097172', NULL, 2020, 'Minor Detail'),
(205, 'Life under occupation in Nablus in 1972', 'https://m.media-amazon.com/images/I/71pzFcCibaL._SL1500_.jpg', '1566563364', 'English', 1976, 'Wild Thorns'),
(206, 'Social commentary post‑1967 war', 'https://m.media-amazon.com/images/I/91vwDXk12nL._SL1500_.jpg', '977416542X', 'English', 1981, 'Of Noble Origins'),
(207, 'Family saga against backdrop of occupation', 'https://m.media-amazon.com/images/I/81JHLJxxUCL._SL1500_.jpg', '978-0857427724', NULL, 2005, 'Passage to the Plaza'),
(208, 'A deeply poetic account of love and resistance through a young girl’s eyes by acclaimed writer, Sahar Khalifeh, called \"the Virginia Woolf of Palestinian literature” (Börsenblatt)\n\nNidal, after many decades of restless exile, returns to her family home in Nablus, where she had lived with her grandmother before the 1948 Nakba that scattered her family across the globe. She was a young girl when the popular resistance began and, through the bloodshed and bitter struggle, Nidal fell in love with freedom fighter Rabie. He was her first and only real love―him and all that he represented: Palestine in its youth, the resistance fighters in the hills, the nation as embodied in her family home and in the land.\n\nMany years later, Nidal and Rabie meet, and he encourages her to read her uncle Amin’s memoirs. She immerses herself in the details of her family and national past and discovers the secret history of her absent mother.\n\nFilled with emotional urgency and political immediacy, Sahar Khalifeh spins an epic tale reaching from the final days of the British Mandate to today with clear-eyed realism and great imagination.', 'https://m.media-amazon.com/images/I/81AM4RZDPUL._SL1500_.jpg', '9774169832', 'English', 2001, 'My First and Only Love'),
(209, '\"Palestine\'s Children offers the concerned reader an excellent work wherein the translation maintains the powerful spirit that animates the Arabic original.\"—Aida A. Bamia, Journal of Third World Studies\n\n\"[Kanafani] unabashedly depicts the hardship of life in the refugee camps, the agony of succumbing to numerous political or ideological shifts, and life nearly devoid of hope.... The novella Returning to Haifa speaks volumes about the enduring traumas of war.... In a moving, concise manner, this story touches upon many small issues that together contribute to the conflict between the Palestinians and Zionists including identity, language, class strife, and the deceptively difficult task of defining ‘homeland.\'\"—Christine Dykgraaf, MESA Bulletin\n\n\"Politics and the novel,\" Ghassan Kanafani once said, \"are an indivisible case.\" Fadl al-Naqib reflected that Kanafani \"wrote the Palestinian story, then he was written by it.\" His narratives offer entry into the Palestinian experience of the conflict that has anguished the people of the Middle East for more than a century.\n\nIn Palestine\'s Children, each story involves a child--a child who is victimized by political events and circumstances, but who nevertheless participates in the struggle toward a better future. As in Kanafani\'s other fiction, these stories explore the need to recover the past--the lost homeland--by action. At the same time, written by a major talent, they have a universal appeal.', 'https://m.media-amazon.com/images/I/71EWYQ5luJL._SL1500_.jpg', '0894108905', NULL, 1969, 'Palestine\'s Children: Returning to Haifa and other stories'),
(210, 'Stories of Palestinian refugees and migration', 'https://m.media-amazon.com/images/I/81fGir+UueL._SL1500_.jpg', '9780894108570', 'English', 1962, 'Men in the Sun and other Palestinian stories'),
(211, 'Nostalgic stories of pre‑Nakba Palestine', NULL, NULL, 'English', 1963, 'The Land of Sad Oranges'),
(212, 'The vivid story of twenty-four hours in the real and remembered lives of a brother and sister living in Gaza and separated from their family.\n\nGhassan Kanafani’s writings are among the most influential in modern Palestinian literature. In his novels, short stories, and plays, he explores complex political questions encased in beautiful narratives and lyrical prose.\n\nAll That\'s Left to You presents the vivid story of twenty-four hours in the real and remembered lives of a brother and sister living in Gaza and separated from their family. The desert and time emerge as characters as Kanafani speaks through the desert, the brother, and the sister to build the powerful rhythm of the narrative. The Palestinian attachment to land and family, and the sorrow over their loss, are symbolized by the young man’s unremitting anger and shame over his sister’s sexual disgrace.\n\nThis remarkable collection of stories provides evidence to the English-reading public of Kanafani’s position within modern Arabic literature. Not only was he committed to portraying the miseries and aspirations of his people, the Palestinians, in whose cause he died, but he was also an innovator within the extensive world of Arabic fiction.', 'https://m.media-amazon.com/images/I/61bS9XquAbL._SL1500_.jpg', '1623717248', 'English', 1966, 'All That\'s Left to You'),
(213, 'Partially‑published novel about Palestinian identity', NULL, NULL, NULL, 1971, 'The Lover'),
(214, 'Historical fiction of Palestinian return to Paris', NULL, NULL, 'English', 2022, 'The Parisian'),
(215, 'Haunted theatre production intertwining politics & identity', 'https://images-na.ssl-images-amazon.com/images/I/81EZHoZlrtL.jpg', '9781529919998', 'English', 2024, 'Enter Ghost'),
(216, 'Impact of tradition on Palestinian‑American women', NULL, NULL, 'English', 2019, 'A Woman Is No Man'),
(217, 'Queer Palestinian‑American coming‑of‑age story', NULL, NULL, 'English', 2020, 'You Exist Too Much'),
(218, 'Short stories about Palestinian diaspora', NULL, NULL, 'English', 2020, 'The Inheritance of Exile'),
(219, 'Family history and Palestinian refugee experience', NULL, NULL, 'English', 2019, 'A Curious Land'),
(220, 'Exile, identity, and personal history in Palestine', NULL, NULL, 'English', 2011, 'Velvet'),
(221, 'Intersecting lives in refugee camps', NULL, NULL, 'English', 2018, 'The Beauty of Your Face'),
(222, 'Stories of Palestinian and Lebanese women', NULL, NULL, 'English', 2019, 'Behind You Is the Sea'),
(223, 'Life in exile and return to Palestine', NULL, NULL, 'English', 2019, 'Mother of Strangers'),
(224, 'Coming‑of‑age in immigrant family', NULL, NULL, 'English', 2003, 'A Map of Home'),
(225, 'Poetry and prose on exile and memory', NULL, NULL, 'English', 2006, 'In the Presence of Absence'),
(226, 'Prose poem on the 1982 Siege of Beirut', NULL, NULL, 'English', 1982, 'Memory for Forgetfulness'),
(227, 'Poetry from Gaza expressing life under siege', NULL, NULL, 'English', 2023, 'Forest of Noise'),
(228, 'Palestinian folktales retold', NULL, NULL, 'English', 2012, 'Speak, Bird, Speak Again'),
(229, 'Modern Palestinian short stories', NULL, NULL, 'English', 2017, 'The Sea Cloak and Other Stories'),
(230, 'Memoir of Palestinian doctor exiled from Jerusalem', NULL, NULL, 'English', 2002, 'In Search of Fatima'),
(231, 'Continuation of Fatima memoir on return', NULL, NULL, 'English', 2004, 'Return'),
(232, 'Journalistic family memoir', NULL, NULL, 'English', 2010, 'My Father Was a Freedom Fighter'),
(233, 'Autobiography of Palestinian‑American identity', NULL, NULL, 'English', 1999, 'Out of Place'),
(234, 'Classic political essay on Palestinian nationalism', NULL, NULL, 'English', 1979, 'The Question of Palestine'),
(235, 'Critical essay on Zionism', NULL, NULL, 'English', 1979, 'Zionism from the Standpoint of Its Victims'),
(236, 'Political cartoon & memoir', NULL, NULL, 'English', 1970, 'A Child in Palestine'),
(237, 'Reflections on geography, law & identity', NULL, NULL, 'English', 2008, 'Palestinian Walks'),
(238, 'A landmark history of one hundred years of war waged against the Palestinians from the foremost US historian of the Middle East, told through pivotal events and family history\n\nIn 1899, Yusuf Diya al-Khalidi, mayor of Jerusalem, alarmed by the Zionist call to create a Jewish national home in Palestine, wrote a letter aimed at Theodore Herzl: the country had an indigenous people who would not easily accept their own displacement. He warned of the perils ahead, ending his note, in the name of God, let Palestine be left alone. Thus Rashid Khalidi, al-Khalidis great-great-nephew, begins this sweeping history, the first general account of the conflict told from an explicitly Palestinian perspective.\n\nDrawing on a wealth of untapped archival materials and the reports of generations of family membersmayors, judges, scholars, diplomats, and journalistsThe Hundred Years\'\' War on Palestine upends accepted interpretations of the conflict, which tend, at best, to describe a tragic clash between two peoples with claims to the same territory. Instead, Khalidi traces a hundred years of colonial war on the Palestinians, waged first by the Zionist movement and then Israel, but backed by Britain and the United States, the great powers of the age. He highlights the key episodes in this colonial campaign, from the 1917 Balfour Declaration to the destruction of Palestine in 1948, from Israels 1982 invasion of Lebanon to the endless and futile peace process.\n\nOriginal, authoritative, and important, The Hundred Years\'\' War on Palestine is not a chronicle of victimization, nor does it whitewash the mistakes of Palestinian leaders or deny the emergence of national movements on both sides. In reevaluating the forces arrayed against the Palestinians, it offers an illuminating new view of a conflict that continues to this day.', 'https://m.media-amazon.com/images/I/81fEw8i8omL._SL1500_.jpg', '9781781259344', NULL, 2020, 'The Hundred Years\' War on Palestine'),
(239, 'History of the Palestinian struggle for statehood', NULL, NULL, 'English', 2006, 'The Iron Cage'),
(240, 'Study of society under occupation', NULL, NULL, 'English', 1997, 'Palestinian Identity'),
(241, 'Accounts of refugees post‑1948', NULL, NULL, 'English', 2002, 'The Forgotten Palestinians'),
(242, 'Critical history of Nakba and expulsion', NULL, NULL, 'English', 2006, 'The Ethnic Cleansing of Palestine'),
(243, 'Comprehensive history of Palestinian nationalism', NULL, NULL, 'English', 2000, 'The Palestinian People: A History'),
(244, 'History of Gaza from Ottoman era to present', NULL, NULL, 'English', 2009, 'Gaza: A History'),
(245, 'Memoir by Leila Khaled', NULL, NULL, 'English', 1973, 'On the Front Lines of the Palestinian Revolution'),
(246, 'Journalistic accounts from Gaza', NULL, NULL, 'English', 2012, 'Drinking the Sea at Gaza'),
(247, 'Recipes & stories from Gaza', 'https://images-na.ssl-images-amazon.com/images/I/81XH2pXAYNL.jpg', '9781935982234', 'English', 2018, 'A Gaza Kitchen'),
(248, 'Memoir of activist jailed by Israel', NULL, NULL, 'English', 2022, 'They Called Me a Lioness'),
(249, 'Essays on identity, feminism & heritage', NULL, NULL, 'English', 2024, 'Namesake'),
(250, 'enoutef est un jeune egyptien', NULL, '123456', 'french', 2009, 'Enmoutef'),
(253, 'cdjsndc', NULL, NULL, 'woof', 1245, 'My life'),
(254, 'First published in Arabic in 1959, the story of an Egyptian family mirrors the spiritual history of humankind as a feudal lord disowns one son for diabolical pride and puts another son to the ultimate test. By the Nobel Prize-winning author of Arabian Nights and Days. ', 'https://m.media-amazon.com/images/I/91rO7YtH76L._SL1500_.jpg', ' 0385420943', 'English', 1996, 'Children of the Alley');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `books_authors`
--

CREATE TABLE `books_authors` (
  `book_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `books_authors`
--

INSERT INTO `books_authors` (`book_id`, `author_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(12, 9),
(13, 6),
(15, 1),
(17, 13),
(201, 13),
(202, 13),
(203, 101),
(204, 102),
(205, 103),
(206, 103),
(207, 103),
(208, 103),
(209, 104),
(210, 104),
(211, 104),
(212, 104),
(213, 104),
(214, 105),
(215, 105),
(216, 106),
(217, 107),
(218, 108),
(219, 108),
(220, 109),
(221, 110),
(222, 108),
(223, 111),
(224, 112),
(225, 113),
(226, 113),
(227, 114),
(228, 115),
(229, 116),
(230, 117),
(231, 117),
(232, 118),
(233, 119),
(234, 119),
(235, 119),
(236, 120),
(237, 121),
(238, 122),
(239, 122),
(240, 122),
(241, 123),
(242, 123),
(243, 124),
(243, 125),
(244, 126),
(245, 127),
(246, 128),
(247, 129),
(247, 130),
(248, 131),
(248, 132),
(249, 133),
(250, 134),
(253, 129),
(254, 136);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `books_genres`
--

CREATE TABLE `books_genres` (
  `book_id` bigint(20) NOT NULL,
  `genre_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `books_genres`
--

INSERT INTO `books_genres` (`book_id`, `genre_id`) VALUES
(1, 3),
(1, 18),
(2, 2),
(2, 3),
(3, 3),
(3, 5),
(4, 4),
(4, 10),
(5, 3),
(5, 5),
(5, 6),
(6, 7),
(6, 9),
(7, 4),
(7, 8),
(8, 3),
(8, 6),
(9, 1),
(9, 11),
(10, 3),
(10, 10),
(12, 1),
(12, 9),
(13, 4),
(13, 7),
(201, 5),
(202, 5),
(203, 5),
(204, 5),
(205, 5),
(206, 5),
(207, 5),
(208, 5),
(209, 12),
(210, 3),
(211, 3),
(212, 3),
(213, 5),
(214, 5),
(215, 5),
(216, 5),
(217, 5),
(218, 5),
(219, 5),
(220, 5),
(221, 5),
(222, 5),
(223, 5),
(224, 5),
(225, 15),
(226, 15),
(227, 15),
(228, 5),
(229, 3),
(230, 14),
(231, 14),
(232, 14),
(233, 14),
(234, 13),
(235, 13),
(236, 16),
(237, 14),
(238, 12),
(239, 12),
(240, 12),
(241, 12),
(242, 12),
(243, 12),
(244, 12),
(245, 14),
(246, 17),
(247, 18),
(248, 14),
(249, 13),
(250, 10),
(253, 2),
(253, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genres`
--

CREATE TABLE `genres` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `genres`
--

INSERT INTO `genres` (`id`, `name`) VALUES
(10, 'Adventure'),
(3, 'Classic'),
(18, 'Cookbook'),
(11, 'Crime'),
(6, 'Drama'),
(2, 'Dystopian'),
(13, 'Essay'),
(4, 'Fantasy'),
(5, 'Fiction'),
(16, 'Graphic'),
(12, 'History'),
(7, 'Horror'),
(17, 'Journalism'),
(14, 'Memoir'),
(1, 'Mystery'),
(15, 'Poetry'),
(9, 'Thriller'),
(8, 'Young Adult');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'ROLE_USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `email`, `password`, `user_name`, `role`) VALUES
(1, 'zohra@example.com', '$2a$10$dGAWE6xeg/Tu0phBwrx00.f8BkkD0hnDWxuDMtasN6ERct45gvLYK', 'Zohra Bell', 'ROLE_ADMIN'),
(2, 'romina@example.com', '$2a$10$GxAYKin2IMK3YyiC8Qdmt.nmsb28CjQXeh/BsH0qTVYyBHy5kyp7q', 'Romi', 'ROLE_ADMIN'),
(3, 'toumette@gmail.com', '$2a$10$FrXc31yC3pZOwzZNij/YW.7apdao7WPQdIhBt8lNX81Kk8N4bBMNC', 'Touma', 'ROLE_USER'),
(4, 'chérifon@gmail.com', '$2a$10$5uewgLJi8KsvIc/lHwu9B.Ki25zExgJNGp78D4ZQqOIkdwQ8FrxyK', 'Chérif', 'ROLE_USER'),
(5, 'aziz@gmail.com', '$2a$10$OwbPiM7c9h49EfF1cHCO3OZEg6XuBN180mM35R1TJVQHFHc1wqx0W', 'Aziz', 'ROLE_USER'),
(6, 'alinette@gmail.com', '$2a$10$P16q4i1C8jI27bub9biPe.buDMAakKgtP7sYr8QHIersERn5.VuNm', 'Aline', 'ROLE_USER'),
(8, 'kehna@example.com', '$2a$10$mKaRrWxolaCBIM1V/BzCvOTovMhL/7NeChns/89sHg.nrSWiSUzDe', 'Kehna', 'ROLE_ADMIN'),
(9, 'ninou@example.com', '$2a$10$aYqwXlz8ltxEgth3uKLGIeMHE1t/fHP3Kr5PIkMd2Xm45FwtCR2Iy', 'Ninou', 'ROLE_USER'),
(14, 'mimi@gmail.com', '$2a$10$/edvB1K5Zi/jfW3jPWo6Ue19pI3pK9DiWhm57yAgxkmaZoZrTGwVa', 'Mimi', 'ROLE_USER'),
(15, 'admin', 'admin@bookstack.com', 'ADMIN1234', 'ROLE_ADMIN'),
(16, 'user1', 'user1@email.com', 'user1234', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_book_associations`
--

CREATE TABLE `user_book_associations` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `status` enum('DROPPED','FINISHED','ON_HOLD','READING','TO_BE_READ','WISHLIST') DEFAULT NULL,
  `book_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_book_associations`
--

INSERT INTO `user_book_associations` (`id`, `comment`, `rating`, `status`, `book_id`, `user_id`) VALUES
(0, 'I loved it !', 5, 'FINISHED', 254, 2),
(2, 'ndjsndjkskjfb', 0, 'WISHLIST', 1, 6),
(52, NULL, 0, 'WISHLIST', 2, 6),
(102, 'increible', 5, 'FINISHED', 3, 6),
(153, 'wonderful', 4, 'FINISHED', 7, 6),
(202, 'wonderful', 4, 'FINISHED', 8, 6),
(252, 'wonderful', 4, 'FINISHED', 10, 6),
(302, 'wonderful', 4, 'FINISHED', 15, 6),
(352, 'genial', 5, 'FINISHED', 2, 5),
(353, 'I saw her on tv last week and cant wait to read her sjkncsnmcnsncnsmdc nsm cd nsmndc sdncsbdjhbdfbkfbsrvkvfsdvhajkbvlsjkbvflbvfjlsbevgjhsbvhjbdvfhjbdfvbshdkvb s dvdsbvffsjlkfbhswbd', 4, 'TO_BE_READ', 207, 5),
(354, NULL, 0, 'WISHLIST', 7, 5),
(402, NULL, 1, 'DROPPED', 3, 5),
(452, NULL, 0, 'WISHLIST', 219, 6),
(453, NULL, 0, 'READING', 203, 6),
(502, NULL, 0, 'WISHLIST', 232, 1),
(503, NULL, 0, 'WISHLIST', 231, 1),
(504, NULL, 0, 'WISHLIST', 243, 1),
(505, 'an absolute must-read', 5, 'FINISHED', 2, 1),
(506, NULL, 0, 'WISHLIST', 10, 5),
(507, NULL, 0, 'WISHLIST', 1, 1),
(552, NULL, 0, 'WISHLIST', 3, 1),
(602, NULL, 0, 'WISHLIST', 231, 14),
(603, NULL, 0, 'TO_BE_READ', 207, 14),
(604, NULL, 0, 'READING', 203, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_book_associations_seq`
--

CREATE TABLE `user_book_associations_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_book_associations_seq`
--

INSERT INTO `user_book_associations_seq` (`next_val`) VALUES
(701);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`author_id`),
  ADD UNIQUE KEY `UK6va0jjhs08lootjg6xosj1b2d` (`last_name`,`first_name`);

--
-- Indices de la tabla `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `UKkibbepcitr0a3cpk3rfr7nihn` (`isbn`);

--
-- Indices de la tabla `books_authors`
--
ALTER TABLE `books_authors`
  ADD PRIMARY KEY (`book_id`,`author_id`),
  ADD KEY `FK3qua08pjd1ca1fe2x5cgohuu5` (`author_id`);

--
-- Indices de la tabla `books_genres`
--
ALTER TABLE `books_genres`
  ADD PRIMARY KEY (`book_id`,`genre_id`),
  ADD KEY `FKgkat05y2cec3tcpl6ur250sd0` (`genre_id`);

--
-- Indices de la tabla `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpe1a9woik1k97l87cieguyhh4` (`name`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indices de la tabla `user_book_associations`
--
ALTER TABLE `user_book_associations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn0jfq2q8heys535paanq5q9gt` (`book_id`),
  ADD KEY `FK39ivct607arb4hs0emvsra1gr` (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `authors`
--
ALTER TABLE `authors`
  MODIFY `author_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT de la tabla `books`
--
ALTER TABLE `books`
  MODIFY `book_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=255;

--
-- AUTO_INCREMENT de la tabla `genres`
--
ALTER TABLE `genres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `books_authors`
--
ALTER TABLE `books_authors`
  ADD CONSTRAINT `FK1b933slgixbjdslgwu888m34v` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  ADD CONSTRAINT `FK3qua08pjd1ca1fe2x5cgohuu5` FOREIGN KEY (`author_id`) REFERENCES `authors` (`author_id`);

--
-- Filtros para la tabla `books_genres`
--
ALTER TABLE `books_genres`
  ADD CONSTRAINT `FKgkat05y2cec3tcpl6ur250sd0` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`),
  ADD CONSTRAINT `FKlv42b6uemg63q27om39jjbt9o` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);

--
-- Filtros para la tabla `user_book_associations`
--
ALTER TABLE `user_book_associations`
  ADD CONSTRAINT `FK39ivct607arb4hs0emvsra1gr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKn0jfq2q8heys535paanq5q9gt` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
