-- Disable foreign key checks temporarily to avoid issues with insert order
SET FOREIGN_KEY_CHECKS = 0;

-- Clean up existing data (optional, uncomment if you want to start fresh with books, authors, and genres)
-- TRUNCATE TABLE books;
-- TRUNCATE TABLE authors;
-- TRUNCATE TABLE genres;
-- TRUNCATE TABLE books_genres; -- Assuming a many-to-many join table for books and genres
-- TRUNCATE TABLE books_authors; -- Assuming a many-to-many join table for books and authors

-- Re-enable foreign key checks if you choose not to truncate
SET FOREIGN_KEY_CHECKS = 1;

-- ---
-- Authors
-- ---
-- Insert some authors if they don't exist. Adjust IDs or remove if you have existing authors.
INSERT IGNORE INTO authors (author_id, first_name, last_name) VALUES
(1, 'Agatha', 'Christie'),
(2, 'George', 'Orwell'),
(3, 'Jane', 'Austen'),
(4, 'J.R.R.', 'Tolkien'),
(5, 'Harper', 'Lee'),
(6, 'Stephen', 'King'),
(7, 'J.K.', 'Rowling'),
(8, 'F. Scott', 'Fitzgerald'),
(9, 'Arthur Conan', 'Doyle'),
(10, 'Mark', 'Twain'),
(11, 'Charles', 'Dickens');


-- ---
-- Genres
-- ---
-- Insert some genres if they don't exist. Adjust IDs or remove if you have existing genres.
INSERT IGNORE INTO genres (id, name) VALUES
(1, 'Mystery'),
(2, 'Dystopian'),
(3, 'Classic'),
(4, 'Fantasy'),
(5, 'Fiction'),
(6, 'Drama'),
(7, 'Horror'),
(8, 'Young Adult'),
(9, 'Thriller'),
(10, 'Adventure'),
(11, 'Crime');


-- ---
-- Books
-- ---
-- Insert books
INSERT INTO books (title, description, publication_year, language, isbn) VALUES
('And Then There Were None', 'Ten strangers, each lured to a remote island, discover they are trapped with a killer.', 1939, 'English', '9780062073488'), -- Author: Agatha Christie (1), Genre: Mystery (1), Thriller (9)
('1984', 'A dystopian social science fiction novel and cautionary tale.', 1949, 'English', '9780451524935'), -- Author: George Orwell (2), Genre: Dystopian (2), Classic (3)
('Pride and Prejudice', 'A classic novel of manners, love, and societal expectations in 19th-century England.', 1813, 'English', '9780141439518'), -- Author: Jane Austen (3), Genre: Classic (3), Romance (fictional genre id 12, adding fiction 5)
('The Hobbit', 'A fantasy novel by J.R.R. Tolkien, serving as a prequel to The Lord of the Rings.', 1937, 'English', '9780345339683'), -- Author: J.R.R. Tolkien (4), Genre: Fantasy (4), Adventure (10)
('To Kill a Mockingbird', 'A novel about the serious issues of rape and racial inequality.', 1960, 'English', '9780446310789'), -- Author: Harper Lee (5), Genre: Fiction (5), Drama (6), Classic (3)
('The Shining', 'A horror novel about a family looking after an isolated hotel during winter.', 1977, 'English', '9780345806789'), -- Author: Stephen King (6), Genre: Horror (7), Thriller (9)
('Harry Potter and the Sorcerer''s Stone', 'The first novel in the Harry Potter series, introducing the wizarding world.', 1997, 'English', '9780590353427'), -- Author: J.K. Rowling (7), Genre: Young Adult (8), Fantasy (4)
('The Great Gatsby', 'A novel portraying the Roaring Twenties in America.', 1925, 'English', '9780743273565'), -- Author: F. Scott Fitzgerald (8), Genre: Classic (3), Drama (6)
('A Study in Scarlet', 'The first story to feature Sherlock Holmes and Dr. Watson.', 1887, 'English', '9781503290685'), -- Author: Arthur Conan Doyle (9), Genre: Mystery (1), Crime (11)
('The Adventures of Tom Sawyer', 'A classic American novel about a boy growing up along the Mississippi River.', 1876, 'English', '9780486280590'), -- Author: Mark Twain (10), Genre: Adventure (10), Classic (3)
('A Christmas Carol', 'A novella by Charles Dickens about Ebenezer Scrooge.', 1843, 'English', '9780140439050'), -- Author: Charles Dickens (11), Genre: Classic (3), Drama (6)
('The Hound of the Baskervilles', 'Sherlock Holmes investigates a mysterious curse.', 1902, 'English', '9780486272557'), -- Author: Arthur Conan Doyle (9), Genre: Mystery (1), Thriller (9)
('The Stand', 'Stephen King''s epic post-apocalyptic dark fantasy novel.', 1978, 'English', '9780385199577'), -- Author: Stephen King (6), Genre: Horror (7), Fantasy (4)
('Frankenstein', 'A novel about a scientist who creates a grotesque creature.', 1818, 'English', '9780486282112'), -- Author: Mary Shelley (fictional new author), Genre: Classic (3), Horror (7)
('Murder on the Orient Express', 'Hercule Poirot investigates a murder on a train.', 1934, 'English', '9780062073495'); -- Author: Agatha Christie (1), Genre: Mystery (1), Crime (11)


-- ---
-- Book-Author and Book-Genre Relationships
-- ---
-- This section assumes you have join tables named 'books_authors' and 'books_genres'.
-- Adjust table and column names (book_id, author_id, genre_id) if yours are different.

-- And Then There Were None
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'And Then There Were None'), (SELECT author_id FROM authors WHERE last_name = 'Christie'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'And Then There Were None'), (SELECT id FROM genres WHERE name = 'Mystery')), ((SELECT book_id FROM books WHERE title = 'And Then There Were None'), (SELECT id FROM genres WHERE name = 'Thriller'));

-- 1984
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = '1984'), (SELECT author_id FROM authors WHERE last_name = 'Orwell'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = '1984'), (SELECT id FROM genres WHERE name = 'Dystopian')), ((SELECT book_id FROM books WHERE title = '1984'), (SELECT id FROM genres WHERE name = 'Classic'));

-- Pride and Prejudice
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'Pride and Prejudice'), (SELECT author_id FROM authors WHERE last_name = 'Austen'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'Pride and Prejudice'), (SELECT id FROM genres WHERE name = 'Classic')), ((SELECT book_id FROM books WHERE title = 'Pride and Prejudice'), (SELECT id FROM genres WHERE name = 'Fiction')); -- Assuming Romance is mapped to Fiction here

-- The Hobbit
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Hobbit'), (SELECT author_id FROM authors WHERE last_name = 'Tolkien'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Hobbit'), (SELECT id FROM genres WHERE name = 'Fantasy')), ((SELECT book_id FROM books WHERE title = 'The Hobbit'), (SELECT id FROM genres WHERE name = 'Adventure'));

-- To Kill a Mockingbird
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'To Kill a Mockingbird'), (SELECT author_id FROM authors WHERE last_name = 'Lee'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'To Kill a Mockingbird'), (SELECT id FROM genres WHERE name = 'Fiction')), ((SELECT book_id FROM books WHERE title = 'To Kill a Mockingbird'), (SELECT id FROM genres WHERE name = 'Drama')), ((SELECT book_id FROM books WHERE title = 'To Kill a Mockingbird'), (SELECT id FROM genres WHERE name = 'Classic'));

-- The Shining
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Shining'), (SELECT author_id FROM authors WHERE last_name = 'King'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Shining'), (SELECT id FROM genres WHERE name = 'Horror')), ((SELECT book_id FROM books WHERE title = 'The Shining'), (SELECT id FROM genres WHERE name = 'Thriller'));

-- Harry Potter and the Sorcerer's Stone
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'Harry Potter and the Sorcerer''s Stone'), (SELECT author_id FROM authors WHERE last_name = 'Rowling'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'Harry Potter and the Sorcerer''s Stone'), (SELECT id FROM genres WHERE name = 'Young Adult')), ((SELECT book_id FROM books WHERE title = 'Harry Potter and the Sorcerer''s Stone'), (SELECT id FROM genres WHERE name = 'Fantasy'));

-- The Great Gatsby
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Great Gatsby'), (SELECT author_id FROM authors WHERE last_name = 'Fitzgerald'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Great Gatsby'), (SELECT id FROM genres WHERE name = 'Classic')), ((SELECT book_id FROM books WHERE title = 'The Great Gatsby'), (SELECT id FROM genres WHERE name = 'Drama'));

-- A Study in Scarlet
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'A Study in Scarlet'), (SELECT author_id FROM authors WHERE last_name = 'Doyle'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'A Study in Scarlet'), (SELECT id FROM genres WHERE name = 'Mystery')), ((SELECT book_id FROM books WHERE title = 'A Study in Scarlet'), (SELECT id FROM genres WHERE name = 'Crime'));

-- The Adventures of Tom Sawyer
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Adventures of Tom Sawyer'), (SELECT author_id FROM authors WHERE last_name = 'Twain'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Adventures of Tom Sawyer'), (SELECT id FROM genres WHERE name = 'Adventure')), ((SELECT book_id FROM books WHERE title = 'The Adventures of Tom Sawyer'), (SELECT id FROM genres WHERE name = 'Classic'));

-- A Christmas Carol
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'A Christmas Carol'), (SELECT author_id FROM authors WHERE last_name = 'Dickens'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'A Christmas Carol'), (SELECT id FROM genres WHERE name = 'Classic')), ((SELECT book_id FROM books WHERE title = 'A Christmas Carol'), (SELECT id FROM genres WHERE name = 'Drama'));

-- The Hound of the Baskervilles
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Hound of the Baskervilles'), (SELECT author_id FROM authors WHERE last_name = 'Doyle'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Hound of the Baskervilles'), (SELECT id FROM genres WHERE name = 'Mystery')), ((SELECT book_id FROM books WHERE title = 'The Hound of the Baskervilles'), (SELECT id FROM genres WHERE name = 'Thriller'));

-- The Stand
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Stand'), (SELECT author_id FROM authors WHERE last_name = 'King'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'The Stand'), (SELECT id FROM genres WHERE name = 'Horror')), ((SELECT book_id FROM books WHERE title = 'The Stand'), (SELECT id FROM genres WHERE name = 'Fantasy'));

-- Frankenstein
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'Frankenstein'), (SELECT author_id FROM authors WHERE first_name = 'Mary' AND last_name = 'Shelley'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'Frankenstein'), (SELECT id FROM genres WHERE name = 'Classic')), ((SELECT book_id FROM books WHERE title = 'Frankenstein'), (SELECT id FROM genres WHERE name = 'Horror'));

-- Murder on the Orient Express
INSERT INTO books_authors (book_id, author_id) VALUES ((SELECT book_id FROM books WHERE title = 'Murder on the Orient Express'), (SELECT author_id FROM authors WHERE last_name = 'Christie'));
INSERT INTO books_genres (book_id, genre_id) VALUES ((SELECT book_id FROM books WHERE title = 'Murder on the Orient Express'), (SELECT id FROM genres WHERE name = 'Mystery')), ((SELECT book_id FROM books WHERE title = 'Murder on the Orient Express'), (SELECT id FROM genres WHERE name = 'Crime'));