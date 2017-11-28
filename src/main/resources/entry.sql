create schema bank;

create TABLE bank.customer
(
id INTEGER not NULL,
firstname VARCHAR (255),
lastName VARCHAR (255),
pesel VARCHAR (11),
email VARCHAR (60),
password VARCHAR (80)
PRIMARY KEY (id)
);

CREATE TABLE moviedb.movie
(
id INTEGER NOT NULL,
title VARCHAR (255),
PRIMARY KEY (id)
);

CREATE TABLE moviedb.moviecast
(
id INTEGER NOT NULL,
movieid INTEGER,
actorid INTEGER,
PRIMARY KEY (id)
);

INSERT INTO moviedb.actor (id, firstname, lastName) VALUES (1, 'Arnold', 'Szwarceneger');
INSERT INTO moviedb.actor (id, firstname, lastName) VALUES (2, 'Stach', 'De Niro');
INSERT INTO moviedb.actor (id, firstname, lastName) VALUES (3, 'Olek', 'Paczino');

INSERT INTO moviedb.movie (id, title) VALUES (1, 'Ogniem i Springiem');
INSERT INTO moviedb.movie (id, title) VALUES (2, 'Atak Beanów');
INSERT INTO moviedb.movie (id, title) VALUES (3, 'Akcja Development');

INSERT INTO moviedb.moviecast (id, actorid, movieid) VALUES (1, 1, 1);
INSERT INTO moviedb.moviecast (id, actorid, movieid) VALUES (2, 1, 3);
INSERT INTO moviedb.moviecast (id, actorid, movieid) VALUES (3, 2, 2);
INSERT INTO moviedb.moviecast (id, actorid, movieid) VALUES (4, 2, 1);