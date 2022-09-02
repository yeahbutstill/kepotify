package com.yeahbutstill.kepotify.entity;

import com.yeahbutstill.kepotify.helpers.YearAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Year;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String title;

    @NotBlank
    @NotEmpty
    private String image;

    @NotBlank
    @NotEmpty
    @Convert(converter = YearAttributeConverter.class)
    private Year release;

    @ManyToMany(mappedBy = "hasAlbums")
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album")
    private Set<Song> containsSongs;

}