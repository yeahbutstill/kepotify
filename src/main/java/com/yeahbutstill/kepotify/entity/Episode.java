package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
//@Entity
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
