package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.OneToMany;
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
public class PlaylistCategory extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String icon;

    @NotBlank
    @NotEmpty
    private String image;

    @OneToMany(mappedBy = "playlistCategory")
    private Set<Playlist> containsPlaylists;

}
