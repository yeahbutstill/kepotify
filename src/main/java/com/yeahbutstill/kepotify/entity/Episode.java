package com.yeahbutstill.kepotify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Episode extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String title;

    @Lob
    private String description;

    private LocalDate publishedAt;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    @NotBlank
    @NotEmpty
    private Podcast podcast;

}
