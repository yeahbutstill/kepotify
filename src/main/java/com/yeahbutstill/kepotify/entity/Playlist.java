package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "playlists")
@SQLDelete(sql = "UPDATE playlists SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Playlist extends AuditTableEntity<UUID> {

    @NotBlank
    @NotEmpty
    private String name;

    @Lob
    private String description;

    @NotBlank
    @NotEmpty
    @Lob
    private String image;

    @ManyToMany(mappedBy = "followPlaylist")
    @ToString.Exclude
    private Set<Users> follow;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userPlaylists;

    @ManyToOne
    @JoinColumn(name = "playlist_categories_id", referencedColumnName = "id")
    private PlaylistCategorie playlistCategories;

    @ManyToMany
    @JoinTable(
            name = "contain_playlists",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Song> containSong;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Playlist playlist = (Playlist) o;
        return getId() != null && Objects.equals(getId(), playlist.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
