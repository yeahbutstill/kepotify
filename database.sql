CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

create table albums
(
    release       smallint,
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    title         varchar(255),
    updated_by    varchar(255),
    image         text,
    primary key (id)
);

create table artists
(
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    facebook      varchar(255),
    instagram     varchar(255),
    name          varchar(255),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    twitter       varchar(255),
    updated_by    varchar(255),
    wikipedia     varchar(255),
    bio           text,
    primary key (id)
);

create table concerts
(
    lat           float(53),
    lon           float(53),
    created_at    timestamp(6),
    event_at      timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    location      varchar(255),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    url           varchar(255),
    primary key (id)
);

create table contain_playlists
(
    playlist_id uuid not null,
    song_id     uuid not null,
    primary key (playlist_id, song_id)
);

create table episodes
(
    duration      integer,
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    podcast_id    uuid,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    title         varchar(255),
    updated_by    varchar(255),
    description   text,
    primary key (id)
);

create table follow_artists
(
    artist_id uuid not null,
    user_id   uuid not null,
    primary key (artist_id, user_id)
);

create table follow_playlists
(
    playlist_id uuid not null,
    user_id     uuid not null,
    primary key (playlist_id, user_id)
);

create table has_artist_albums
(
    album_id  uuid not null,
    artist_id uuid not null,
    primary key (album_id, artist_id)
);

create table payments
(
    amount        numeric(38, 2),
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    primary key (id)
);

create table payments_credit_card
(
    id          uuid not null,
    bank        varchar(255),
    masked_card varchar(255),
    primary key (id)
);

create table payments_gopay
(
    id       uuid not null,
    gopay_id varchar(255),
    primary key (id)
);

create table performs
(
    artist_id  uuid not null,
    concert_id uuid not null,
    primary key (artist_id, concert_id)
);

create table playlist_categories
(
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    name          varchar(255),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    icon          text,
    image         text,
    primary key (id)
);

create table playlists
(
    created_at             timestamp(6),
    updated_at             timestamp(6),
    version                bigint,
    id                     uuid         not null,
    playlist_categories_id uuid,
    user_id                uuid,
    created_by             varchar(255),
    environment            varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    name                   varchar(255),
    status_record          varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by             varchar(255),
    description            text,
    image                  text,
    primary key (id)
);

create table podcast_categories
(
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    name          varchar(255),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    icon          text,
    image         text,
    primary key (id)
);

create table podcasts
(
    created_at            timestamp(6),
    updated_at            timestamp(6),
    version               bigint,
    id                    uuid         not null,
    podcast_categories_id uuid,
    user_id               uuid,
    created_by            varchar(255),
    environment           varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    name                  varchar(255),
    status_record         varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by            varchar(255),
    about                 text,
    image                 text,
    primary key (id)
);

create table sings
(
    artist_id uuid not null,
    song_id   uuid not null,
    primary key (artist_id, song_id)
);

create table songs
(
    duration      integer,
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    album_id      uuid,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    title         varchar(255),
    updated_by    varchar(255),
    primary key (id)
);

create table transactions
(
    balance       numeric(38, 2),
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    primary key (id)
);

create table transactions_credit
(
    balance       numeric(38, 2),
    credit_amount numeric(38, 2),
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    primary key (id)
);

create table transactions_debit
(
    balance       numeric(38, 2),
    debit_amount  numeric(38, 2),
    created_at    timestamp(6),
    updated_at    timestamp(6),
    version       bigint,
    id            uuid         not null,
    created_by    varchar(255),
    environment   varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    status_record varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by    varchar(255),
    primary key (id)
);

create table users
(
    birthday            date         not null,
    created_at          timestamp(6),
    member_expired_date timestamp(6),
    updated_at          timestamp(6),
    version             bigint,
    id                  uuid         not null,
    type                varchar(31)  not null,
    created_by          varchar(255),
    email               varchar(255),
    environment         varchar(255) not null check (environment in ('SIT', 'UAT', 'PROD')),
    name                varchar(255),
    password            varchar(255),
    status_record       varchar(255) not null check (status_record in ('ACTIVE', 'INACTIVE')),
    updated_by          varchar(255),
    primary key (id)
);

create table users_like_songs
(
    song_id uuid not null,
    user_id uuid not null,
    primary key (song_id, user_id)
);

alter table if exists contain_playlists
    add constraint FKd8fgtbo77or6ojifmn4srw8e0
    foreign key (song_id)
    references songs;

alter table if exists contain_playlists
    add constraint FK8uikg0q94udcs32go8thnuy9m
    foreign key (playlist_id)
    references playlists;

alter table if exists episodes
    add constraint FKnetidsqw18chu709udfuyqdw5
    foreign key (podcast_id)
    references podcasts;

alter table if exists follow_artists
    add constraint FKlxc39fcfjy0d2wl8ckb04pgqb
    foreign key (artist_id)
    references artists;

alter table if exists follow_artists
    add constraint FKjmftbpmug42kqq88lobda6pec
    foreign key (user_id)
    references users;

alter table if exists follow_playlists
    add constraint FK3xcx6rrqjcn2ddgg2frhx5k5g
    foreign key (playlist_id)
    references playlists;

alter table if exists follow_playlists
    add constraint FKax09p2ysynuxab5t8i9w7m7iw
    foreign key (user_id)
    references users;

alter table if exists has_artist_albums
    add constraint FKmx9dsq5kmhiomlf6enssf7dv9
    foreign key (album_id)
    references albums;

alter table if exists has_artist_albums
    add constraint FKfqchbnmovbmdpn5536uotqqxb
    foreign key (artist_id)
    references artists;

alter table if exists payments_credit_card
    add constraint FK6vl95as9avtffd4sdyr5ekdvs
    foreign key (id)
    references payments;

alter table if exists payments_gopay
    add constraint FK31svl1pv6j6pw6plptmu9gujb
    foreign key (id)
    references payments;

alter table if exists performs
    add constraint FK78amy86wqan1ojv41mbj5cvby
    foreign key (concert_id)
    references concerts;

alter table if exists performs
    add constraint FK6xsy74mv9es5dgb55oc4a3qmr
    foreign key (artist_id)
    references artists;

alter table if exists playlists
    add constraint FKr6m6q4tw94rvij398lxgcb4vl
    foreign key (playlist_categories_id)
    references playlist_categories;

alter table if exists playlists
    add constraint FKtgjwvfg23v990xk7k0idmqbrj
    foreign key (user_id)
    references users;

alter table if exists podcasts
    add constraint FK4c4visi8eb9ywpb2dw463guci
    foreign key (podcast_categories_id)
    references podcast_categories;

alter table if exists podcasts
    add constraint FKrvfqucih76m0pao9dad3gfwdb
    foreign key (user_id)
    references users;

alter table if exists sings
    add constraint FKhmd3rm8yajufj32gjsiucisgv
    foreign key (song_id)
    references songs;

alter table if exists sings
    add constraint FKixcidffqdsqyve94plsethk57
    foreign key (artist_id)
    references artists;

alter table if exists songs
    add constraint FKte4gkb2cqtk2erfa87oopj2cj
    foreign key (album_id)
    references albums;

alter table if exists users_like_songs
    add constraint FK92p4k1ww8spqfcmsqs7pa71vb
    foreign key (song_id)
    references songs;

alter table if exists users_like_songs
    add constraint FK90koxkohrmjgn4ukpvv0ix65k
    foreign key (user_id)
    references users;