package com.yeahbutstill.kepotify.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends BaseEntity {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String author;

}
