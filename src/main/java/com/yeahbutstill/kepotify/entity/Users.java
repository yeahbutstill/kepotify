package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;
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
@Table(name = "users")
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("BASIC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SQLDelete(sql = "UPDATE users SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Users extends AuditTableEntity<UUID> {

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    private LocalDate birthday;

    @NotBlank
    @NotEmpty
    private String password;

    @Transient
    private String userDetails;

    @OneToMany(mappedBy = "userPlaylists")
    @ToString.Exclude
    private List<Playlist> createPlaylist;

    @OneToMany(mappedBy = "userPodcasts")
    @ToString.Exclude
    private List<Podcast> createPodcast;

    @PostLoad
    public void postLoadUserDetails() {
        this.userDetails = this.name + ", " + this.email + ", " + this.birthday;
    }

    @ManyToMany
    @JoinTable(
            name = "follow_artists",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Artist> followArtist;

    @ManyToMany
    @JoinTable(
            name = "follow_playlists",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Playlist> followPlaylist;

    @ManyToMany
    @JoinTable(
            name = "users_like_songs",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Song> likes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Users users = (Users) o;
        return getId() != null && Objects.equals(getId(), users.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
