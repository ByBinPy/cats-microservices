CREATE SCHEMA cats_db;
CREATE TABLE cats_db.owners
(
    id serial primary key,
    name varchar not null,
    date_of_birth date not null
);
CREATE TABLE cats_db.cats
(
    id serial primary key,
    name varchar not null,
    date_of_birth date not null,
    breed varchar,
    color int,
    owner_id bigint not null references cats_db.owners (id)
);
CREATE TABLE cats_db.friends
(
    cat_id bigint references cats_db.cats (id),
    friend_id bigint references cats_db.cats (id)
);

