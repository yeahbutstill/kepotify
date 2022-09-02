package com.yeahbutstill.kepotify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Concert extends BaseEntity {

    @NotEmpty
    @NotBlank
    private String location;

    private Double lon;
    private Double lat;

    @NotEmpty
    @NotBlank
    private LocalDateTime eventAt;

    @NotEmpty
    @NotBlank
    private String url;

    @ManyToMany(mappedBy = "performs")
    private Set<Artist> artists;

}
