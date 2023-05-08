package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "artists")
//@SQLDelete(sql = "UPDATE artist SET status_record='INACTIVE' WHERE id=?")
//@Where(clause = "status_record='ACTIVE'")
public class Artist extends BaseEntity {

    @NotEmpty
    @NotBlank
    private String name;

    private String instagram;
    private String facebook;
    private String twitter;
    private String wikipedia;

    @Lob
    private String bio;

    @ManyToMany
    @JoinTable(
            name = "has_artist_albums",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Album> hasAlbums;

    @ManyToMany
    @JoinTable(
            name = "sings",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Song> singSong;

    @ManyToMany
    @JoinTable(
            name = "performs",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "concert_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Concert> performs;

    @ManyToMany(mappedBy = "followArtist")
    private Set<Users> userFollows;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;
        return getId() != null && Objects.equals(getId(), artist.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}