# kepotify
small service spotify

```plantuml
@startuml
!theme amiga

entity Artist {
    * id : VARCHAR
    --
    * name : VARCHAR
    instagram : VARCHAR
    facebook : VARCHAR
    twitter : VARCHAR
    wikipedia : VARCHAR
    bio : TEXT

}

entity Album {
    * id : VARCHAR
    --
    * title : VARCHAR
    * release : YEAR
    * image : VARCHAR
}

entity Song {
    * id : VARCHAR
    --
    * album_id : VARCHAR
    * title : VARCHAR
    * duratrion : INT
}

entity PlaylistCategory {
    * id : VARCHAR
    --
    * name : VARCHAR
    * image : VARCHAR
    * icon : VARCHAR
}

entity Playlist {
    * id : VARCHAR
    --
    * name : VARCHAR
    description : TEXT
    image : VARCHAR
    category_id : VARCHAR
    * user_id : VARCHAR
}

entity PodcastCategory {
    * id : VARCHAR
    --
    * name : VARCHAR
    * image : VARCHAR
    * icon : VARCHAR
}

entity Podcast {
    * id : VARCHAR
    --
    * name : VARCHAR
    about : TEXT
    image : VARCHAR
    category_id : VARCHAR
    * user_id : VARCHAR
}

entity Episode {
    * id : VARCHAR
    --
    * podcast_id : VARCHAR
    * title : VARCHAR
    description : TEXT
    published_at : DATE
    duratrion : INT
}

entity User {
    * id : VARCHAR
    --
    * email : VARCHAR
    * name : VARCHAR
    * birthday : DATE
    * password : VARCHAR
}

entity Concert {
    * id : VARCHAR
    --
    * location : VARCHAR
    lon : DOUBLE
    lat : DOUBLE
    * event_at : DATETIME
    * url : VARCHAR
}

Artist }|--|{ Album : Many to Many : Has
Album ||--|{ Song  : One to Many : Contains
Artist }|--|{ Song : Many to Many : Sing
PlaylistCategory |o--|{ Playlist  : One to Many : Contains
User ||--o{ Playlist : One to Many : Create
Playlist }o--o{ Song : Many to Many : Contains
PodcastCategory |o--|{ Podcast : One to Many : Contains
User ||--o{ Podcast : One to Many : Create
Podcast ||--o{ Episode : One to Many : Contains
Artist }|--o{ Concert : Many to Many : Perform
User }o--o{ Artist : Many to Many : Follow
User }o--o{ Song : Many to Many : Like Song
User }o--o{ Playlist : Many to Many : Follow
@enduml
```

# Setup Docker
```shell
docker run --rm \
--name restful-kepotify \
-e POSTGRES_DB=kepotifydb \
-e POSTGRES_USER=kepotify \
-e POSTGRES_PASSWORD=PNSJkxXvVNDAhePMuExTBuRR \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v "$PWD/kepotifydb-data:/var/lib/postgresql/data" \
-p 5431:5432 \
postgres:15
```