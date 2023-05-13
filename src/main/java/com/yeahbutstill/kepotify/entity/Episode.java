package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "episodes")
@SQLDelete(sql = "UPDATE episodes SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Episode extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String title;

    @Lob
    private String description;

    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podcast_id", referencedColumnName = "id")
    @ToString.Exclude
    private Podcast podcast;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Episode episode = (Episode) o;
        return getId() != null && Objects.equals(getId(), episode.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
