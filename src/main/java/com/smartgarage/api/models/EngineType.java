package com.smartgarage.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineType {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
}
