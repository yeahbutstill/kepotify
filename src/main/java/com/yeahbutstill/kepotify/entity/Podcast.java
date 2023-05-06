package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
public class Podcast extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @Lob
    private String about;

    private String image;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @NotBlank
    @NotEmpty
    private Users users;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PodcastCategory podcastCategory;

    @OneToMany(mappedBy = "podcast")
    private Set<Episode> containsEpisode;

}
