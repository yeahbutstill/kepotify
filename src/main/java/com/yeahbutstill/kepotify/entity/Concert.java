package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "concerts")
@SQLDelete(sql = "UPDATE concerts SET status_record='INACTIVE' WHERE id=?")
@SQLRestriction(value = "status_record='ACTIVE'")
public class Concert extends AuditTableEntity<UUID> {

    @NotEmpty
    @NotBlank
    private String location;

    private Double lon;
    private Double lat;

    @Column(name = "event_at")
    private LocalDateTime eventAt;

    @NotEmpty
    @NotBlank
    private String url;

    @ManyToMany(mappedBy = "performs")
    @ToString.Exclude
    private Set<Artist> artists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Concert concert = (Concert) o;
        return getId() != null && Objects.equals(getId(), concert.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
