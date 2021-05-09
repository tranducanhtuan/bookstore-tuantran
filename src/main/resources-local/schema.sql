-- DROP TABLE IF EXISTS author;
CREATE TABLE IF NOT EXISTS author (
  author_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  author_name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  created_dt DATETIME NOT NULL,
  updated_by VARCHAR(100),
  updated_dt DATETIME
);

-- DROP TABLE IF EXISTS book;
CREATE TABLE IF NOT EXISTS book (
  book_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  book_title VARCHAR(100) NOT NULL,
  author_id INT(11) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  created_dt DATETIME NOT NULL,
  updated_by VARCHAR(100),
  updated_dt DATETIME,
  foreign key (author_id) references author(author_id)
);