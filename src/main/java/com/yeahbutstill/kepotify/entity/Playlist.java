package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @Lob
    private String description;

    @NotBlank
    @NotEmpty
    private String image;

    @ManyToMany(mappedBy = "followPlaylists")
    private Set<Users> follow;

    @ManyToMany(mappedBy = "containsPlaylists")
    private Set<Song> contains;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @NotBlank
    @NotEmpty
    private Users users;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PlaylistCategory playlistCategory;

}
