/**
	Created by Johnny Lin 22-Jan-19
**/

USE musically;

DROP USER IF EXISTS musically_admin@localhost;
CREATE USER musically_admin@'localhost' IDENTIFIED BY 'admin';

GRANT ALL ON musically.* TO musically_admin@'localhost';


FLUSH PRIVILEGES;

use musically;

create table albums (
	id int primary key auto_increment,
    title varchar(255) not null,
    release_date date not null,
    recording_label varchar(255) not null,
    number_of_tracks int not null,
    added_date date not null,
    cost_price decimal(8,2) not null,
    list_price decimal(8,2) not null,
    sale_price decimal(8,2) not null,
    removed bool default false,
    remove_date date default null,
    cover_filepath varchar(255) not null
)ENGINE=InnoDB;

CREATE TABLE genres (
	id int primary key auto_increment,
    genre varchar(255) not null unique
)ENGINE=InnoDB;

CREATE TABLE artists (
	id int primary key auto_increment,
    artist_name varchar(255) not null unique
)ENGINE=InnoDB;

CREATE TABLE albums_genre (
	album_id int not null,
    genre_id int not null,
    FOREIGN KEY (album_id) REFERENCES albums(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
)ENGINE=InnoDB;

CREATE TABLE albums_artist (
	album_id int not null,
    artist_id int not null,
    FOREIGN KEY (artist_id) REFERENCES artists(id),
    FOREIGN KEY (album_id) REFERENCES albums(id)
)ENGINE=InnoDB;

create table tracks (
    id int primary key not null auto_increment,
    album_id int not null,
    title varchar(255) not null,
    songwriter varchar(255) not null,
    play_length varchar(255) not null,
    album_selection int not null,
    cost_price decimal(8,2) not null,
    list_price decimal(8,2) not null,
    sale_price decimal(8,2) not null,
    sellable_as_single bool default true,
    removed bool default false,
    remove_date date default null,
    FOREIGN KEY (album_id) REFERENCES albums(id)
)ENGINE=InnoDB;

/*Users and purchases section*/

create table user_types (
	id int primary key auto_increment,
    type varchar(255) not null
)ENGINE=InnoDB;

create table users (
	id int primary key auto_increment,
    last_name varchar(255) not null,
    first_name varchar(255) not null,
    company_name varchar(255),
    address_1 varchar(255) not null,
    address_2 varchar(255),
    city varchar(255) not null,
    province varchar(255) not null,
    country varchar(255) not null,
    postal_code varchar(255) not null,
    home_phone varchar(255) not null,
    cell_phone varchar(255),
    email varchar(255) not null unique,
    password varchar(255) not null,
    last_search_genre int not null,
	user_type_id int not null,
    FOREIGN KEY (last_search_genre) REFERENCES genres(id),
	FOREIGN KEY (user_type_id) REFERENCES user_types(id)
)ENGINE=InnoDB;

create table reviews (
	id int primary key auto_increment,
    date date not null,
    user_id int not null,
    rating int not null,
    review varchar(2000),
    approved bool not null default true,
    album_id int,
    track_id int,
    foreign key (user_id) references users(id),
	foreign key (album_id) references albums(id),
	foreign key (track_id) references tracks(id)
)ENGINE=InnoDB;

create table orders (
	id int primary key auto_increment,
    sale_date date not null,
    user_id int not null,
    sub_total decimal(8,2) not null,
    pst decimal(8,2) not null,
    gst decimal(8,2) not null,
    hst decimal(8,2) not null,
    gross_value decimal(8,2) not null,
    is_finalized bool not null default false,
    foreign key (user_id) references users(id)
)ENGINE=InnoDB;

create table order_items (
    id int primary key auto_increment,
    order_id int not null,
    album_id int,
    track_id int,
    price decimal(8,2) not null,
    foreign key (order_id) references orders(id),
    foreign key (album_id) references albums(id),
    foreign key (track_id) references tracks(id)
)ENGINE=InnoDB;

/**survey section**/

create table survey_questions (
	id int primary key auto_increment,
	question varchar(255) not null,
    answer_1 varchar(255) not null,
    answer_2 varchar(255) not null,
    answer_3 varchar(255) not null,
    answer_4 varchar(255) not null
)ENGINE=InnoDB;

create table current_survey (
	id int primary key auto_increment,
    question_id int not null,
	answer_1_count int not null default 0,
    answer_2_count int not null default 0,
    answer_3_count int not null default 0,
    answer_4_count int not null default 0,
    foreign key (question_id) references survey_questions(id)
)ENGINE=InnoDB;

/** banner ad section **/
create table banner_ads (
	id int primary key auto_increment,
    name varchar(255),
    img_path varchar(255),
    link varchar(255)
)ENGINE=InnoDB;