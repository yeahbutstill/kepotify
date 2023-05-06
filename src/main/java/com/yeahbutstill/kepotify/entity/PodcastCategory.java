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
public class PodcastCategory extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @NotEmpty
    @NotBlank
    private String icon;

    @NotEmpty
    @NotBlank
    private String image;

    @OneToMany(mappedBy = "podcastCategory")
    private Set<Podcast> containsPodcasts;

}
