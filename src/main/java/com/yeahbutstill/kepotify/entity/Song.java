package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "songs")
@SQLDelete(sql = "UPDATE songs SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Song extends AuditTableEntity<UUID> {

    private String title;
    private Integer duration;

    @ManyToMany(mappedBy = "singSong")
    @ToString.Exclude
    private Set<Artist> artists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "album_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private Album album;

    @ManyToMany(mappedBy = "containSong")
    @ToString.Exclude
    private Set<Playlist> songPlaylist;

    @ManyToMany(mappedBy = "likes")
    @ToString.Exclude
    private Set<Users> likedBy;

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
