CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    email CHARACTER VARYING(150) NOT NULL,
    name CHARACTER VARYING(150) NOT NULL,
    birthday DATE NOT NULL,
    password CHARACTER VARYING(150) NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL
);
ALTER TABLE users
    ADD type CHARACTER VARYING(50);
ALTER TABLE users
    ADD member_expired_date TIMESTAMP;

CREATE TABLE follow_artists (
    user_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    artist_id UUID NULL,
    CONSTRAINT fk_user_follow_artists FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_artist_follow_artists FOREIGN KEY (artist_id) REFERENCES artists (id),
    PRIMARY KEY (user_id, artist_id)
);

CREATE TABLE follow_playlists (
    user_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    playlist_id UUID NULL,
    CONSTRAINT fk_user_follow_playlists FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_playlist_follow_playlists FOREIGN KEY (playlist_id) REFERENCES playlists (id),
    PRIMARY KEY (user_id, playlist_id)
);

CREATE TABLE artists (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    name CHARACTER VARYING(150) NOT NULL,
    instagram CHARACTER VARYING(150),
    facebook CHARACTER VARYING(150),
    twitter CHARACTER VARYING(150),
    wikipedia CHARACTER VARYING(150),
    bio TEXT,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL
);

CREATE TABLE sings (
    artist_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    song_id UUID NOT NULL,
    CONSTRAINT fk_artist_sing FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_song_sing FOREIGN KEY (song_id) REFERENCES songs (id),
    PRIMARY KEY (artist_id, song_id)
);

CREATE TABLE has_artist_albums (
    artist_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    album_id UUID NOT NULL,
    CONSTRAINT fk_artist_has FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_albums_has FOREIGN KEY (album_id) REFERENCES albums (id),
    PRIMARY KEY (artist_id, album_id)
);

CREATE TABLE albums (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    title CHARACTER VARYING(150) NOT NULL,
    release CHARACTER VARYING(150) NOT NULL,
    image CHARACTER VARYING(150) NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL
);

CREATE TABLE songs (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    album_id UUID NOT NULL,
    title CHARACTER VARYING(150) NOT NULL,
    duration INT NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL,
    CONSTRAINT fk_albums_songs FOREIGN KEY (album_id) REFERENCES albums(id)
);

CREATE TABLE concerts (
    id UUID DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
    location CHARACTER VARYING(150) NOT NULL,
    lon DOUBLE PRECISION,
    lat DOUBLE PRECISION,
    event_at TIMESTAMP NOT NULL ,
    url CHARACTER VARYING(150)NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL
);
ALTER TABLE concerts ADD COLUMN created_by CHARACTER VARYING(150);
ALTER TABLE concerts ADD COLUMN updated_by CHARACTER VARYING(150);
ALTER TABLE concerts ADD COLUMN created_at TIMESTAMP;
ALTER TABLE concerts ADD COLUMN updated_at TIMESTAMP;
ALTER TABLE concerts ADD COLUMN environment CHARACTER VARYING(150);
ALTER TABLE concerts ADD COLUMN status_record CHARACTER VARYING(150);

CREATE TABLE performs (
    artist_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    concert_id UUID NOT NULL,
    CONSTRAINT fk_artist_performs FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_concert_performs FOREIGN KEY (concert_id) REFERENCES concerts (id),
    PRIMARY KEY (artist_id, concert_id)
);

CREATE TABLE playlist_categories (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    name CHARACTER VARYING(150) NOT NULL,
    image CHARACTER VARYING(150) NOT NULL,
    icon CHARACTER VARYING(150) NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL
);

CREATE TABLE playlists (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    user_id UUID NOT NULL,
    playlist_categories_id UUID DEFAULT NULL,
    name CHARACTER VARYING(150) NOT NULL,
    description TEXT,
    image CHARACTER VARYING(150),
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL,
    CONSTRAINT fk_user_playlist FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_category_playlist FOREIGN KEY (playlist_categories_id) REFERENCES playlist_categories (id)
);

CREATE TABLE contain_playlists (
    song_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    playlist_id UUID NOT NULL,
    CONSTRAINT fk_song_contain_playlists FOREIGN KEY (song_id) REFERENCES songs (id),
    CONSTRAINT fk_playlist_contain_playlist FOREIGN KEY (playlist_id) REFERENCES playlists (id),
    PRIMARY KEY (song_id, playlist_id)
);

CREATE TABLE users_like_songs (
    user_id UUID NOT NULL,
    song_id UUID NOT NULL,
    CONSTRAINT fk_users_users_like_songs FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_songs_users_like_songs FOREIGN KEY (song_id) REFERENCES songs (id),
    PRIMARY KEY (user_id, song_id)
);

CREATE TABLE podcasts (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    user_id UUID NOT NULL,
    podcast_categories_id UUID NULL,
    name CHARACTER VARYING(150) NOT NULL,
    about TEXT,
    image CHARACTER VARYING(150),
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL,
    CONSTRAINT fk_user_podcasts FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_category_podcasts FOREIGN KEY (podcast_categories_id) REFERENCES podcast_categories (id)
);

CREATE TABLE podcast_categories (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    name CHARACTER VARYING(150) NOT NULL,
    image CHARACTER VARYING(150) NOT NULL,
    icon CHARACTER VARYING(150) NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL
);

CREATE TABLE episodes (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    podcast_id UUID NOT NULL,
    title CHARACTER VARYING(150) NOT NULL,
    description TEXT,
    duration INT,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150) NOT NULL,
    status_record CHARACTER VARYING(150) NOT NULL,
    CONSTRAINT fk_podcast_episodes FOREIGN KEY (podcast_id) REFERENCES podcasts (id)
);

CREATE TABLE payments (
    id CHARACTER VARYING(100) PRIMARY KEY NOT NULL,
    amount NUMERIC NOT NULL,
    created_by CHARACTER VARYING(150),
    updated_by CHARACTER VARYING(150),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    environment CHARACTER VARYING(150),
    status_record CHARACTER VARYING(150)
);

CREATE TABLE payments_gopay (
    id CHARACTER VARYING(100) PRIMARY KEY NOT NULL,
    gopay_id CHARACTER VARYING(100) NOT NULL,
    CONSTRAINT fk_payments_gopay FOREIGN KEY (id) REFERENCES payments (id)
);

CREATE TABLE payments_credit_card (
    id CHARACTER VARYING(100) PRIMARY KEY NOT NULL,
    masked_card CHARACTER VARYING(100) NOT NULL,
    bank CHARACTER VARYING(100) NOT NULL,
    CONSTRAINT fk_payments_credit_card FOREIGN KEY (id) REFERENCES payments (id)
);