package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Set;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE users SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Users extends BaseEntity {

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

    @ManyToMany
    @JoinTable(
            name = "followArtists",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> followArtists;

    @ManyToMany
    @JoinTable(
            name = "followPlaylists",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private Set<Playlist> followPlaylists;

    @ManyToMany
    @JoinTable(
            name = "likeSongs",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> likeSongs;

    @OneToMany(mappedBy = "users")
    private Set<Playlist> createPlaylists;

    @OneToMany(mappedBy = "users")
    private Set<Podcast> createPodcasts;

}
