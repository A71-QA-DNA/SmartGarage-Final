package com.smartgarage.api.models;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @SuppressWarnings("id")
    private int id;
    @SuppressWarnings("name")
    private String name;
}
