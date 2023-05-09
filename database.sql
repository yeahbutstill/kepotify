CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
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

ALTER TABLE users ADD COLUMN created_by CHARACTER VARYING(150);
ALTER TABLE users ADD COLUMN updated_by CHARACTER VARYING(150);
ALTER TABLE users ADD COLUMN created_at TIMESTAMP;
ALTER TABLE users ADD COLUMN updated_at TIMESTAMP;
ALTER TABLE users ADD COLUMN environment CHARACTER VARYING(150);
ALTER TABLE users ADD COLUMN status_record CHARACTER VARYING(150);

CREATE TABLE follows (
    user_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    artist_id UUID DEFAULT uuid_generate_v4(),
    playlist_id UUID DEFAULT uuid_generate_v4(),
    CONSTRAINT fk_user_follows FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_artist_follows FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_playlist_follows FOREIGN KEY (playlist_id) REFERENCES playlists (id),
    PRIMARY KEY (user_id, artist_id, playlist_id)
);

CREATE TABLE artists (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    name CHARACTER VARYING(150) NOT NULL,
    instagram CHARACTER VARYING(150),
    facebook CHARACTER VARYING(150),
    twitter CHARACTER VARYING(150),
    wikipedia CHARACTER VARYING(150),
    bio TEXT
);

CREATE TABLE sings (
    artist_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    song_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    CONSTRAINT fk_artist_sing FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_song_sing FOREIGN KEY (song_id) REFERENCES songs (id),
    PRIMARY KEY (artist_id, song_id)
);

CREATE TABLE has_artist_albums (
    artist_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    album_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    CONSTRAINT fk_artist_has FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_albums_has FOREIGN KEY (album_id) REFERENCES albums (id),
    PRIMARY KEY (artist_id, album_id)
);

CREATE TABLE albums (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    title CHARACTER VARYING(150) NOT NULL,
    release CHARACTER VARYING(150) NOT NULL,
    image CHARACTER VARYING(150) NOT NULL
);

CREATE TABLE songs (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    album_id uuid DEFAULT uuid_generate_v4() NOT NULL,
    title CHARACTER VARYING(150) NOT NULL,
    duration INT NOT NULL,
    CONSTRAINT fk_albums_songs FOREIGN KEY (album_id) REFERENCES albums(id)
);

CREATE TABLE concerts (
    id UUID DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
    location CHARACTER VARYING(150) NOT NULL,
    lon DOUBLE PRECISION,
    lat DOUBLE PRECISION,
    event_at TIMESTAMP NOT NULL ,
    url CHARACTER VARYING(150)NOT NULL
);

CREATE TABLE performs (
    artist_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    concert_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    CONSTRAINT fk_artist_performs FOREIGN KEY (artist_id) REFERENCES artists (id),
    CONSTRAINT fk_concert_performs FOREIGN KEY (concert_id) REFERENCES concerts (id),
    PRIMARY KEY (artist_id, concert_id)
);

CREATE TABLE playlist_categories (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
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
    user_id UUID DEFAULT uuid_generate_v4() NOT NULL,
    playlist_categories_id UUID DEFAULT uuid_generate_v4(),
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