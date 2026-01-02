-- Core schema for users, books, reviews.
-- Compatible with H2 and PostgreSQL.

create table users (
	id uuid not null,
	email varchar(255) not null,
	nickname varchar(50) not null,
	password varchar(255) not null,
	created_at timestamp not null,
	updated_at timestamp,
	is_deleted boolean not null,
	constraint pk_users primary key (id),
	constraint uq_users_email unique (email),
	constraint uq_users_nickname unique (nickname)
);

create table books (
	id uuid not null,
	title varchar(255) not null,
	author varchar(255) not null,
	description varchar(255) not null,
	publisher varchar(255) not null,
	published_date date not null,
	isbn varchar(255) not null,
	thumbnail_url varchar(255) not null,
	review_count integer not null,
	rating decimal(2, 1) not null,
	created_at timestamp not null,
	updated_at timestamp,
	is_deleted boolean not null,
	constraint pk_books primary key (id),
	constraint uq_books_isbn unique (isbn)
);

create table reviews (
	id uuid not null,
	user_id uuid not null,
	book_id uuid not null,
	content varchar(500) not null,
	rating decimal(2, 1) not null,
	like_count integer not null,
	comment_count integer not null,
	created_at timestamp not null,
	updated_at timestamp,
	is_deleted boolean not null,
	constraint pk_reviews primary key (id),
	constraint uq_reviews_user_book unique (user_id, book_id),
	constraint fk_reviews_user foreign key (user_id) references users (id),
	constraint fk_reviews_book foreign key (book_id) references books (id)
);
