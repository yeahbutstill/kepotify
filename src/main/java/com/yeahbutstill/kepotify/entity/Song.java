package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "songs")
public class Song extends BaseEntity {

    private String title;

    private Integer duration;

//    @ManyToMany
//    @JoinTable(
//            name = "contains",
//            joinColumns = @JoinColumn(name = "song_id"),
//            inverseJoinColumns = @JoinColumn(name = "playlist_id")
//    )
//    @ToString.Exclude
//    private Set<Playlist> containsPlaylists;

//    @ManyToMany
//    @JoinTable(
//            name = "sing",
//            joinColumns = @JoinColumn(name = "song_id"),
//            inverseJoinColumns = @JoinColumn(name = "artist_id")
//    )
//    @ToString.Exclude
//    private Set<Artist> singArtists;

//    @ManyToMany(mappedBy = "likeSongs")
//    @ToString.Exclude
//    private Set<Users> users;

    @ManyToOne
    @JoinColumn(
            name = "album_id",
            referencedColumnName = "id"
    )
    private Album album;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Song song = (Song) o;
        return getId() != null && Objects.equals(getId(), song.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
