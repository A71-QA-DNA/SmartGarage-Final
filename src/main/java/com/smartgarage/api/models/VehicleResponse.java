package com.smartgarage.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("brand")
    private Brand brand;

    @SerializedName("model")
    private Model model;

    @SerializedName("year")
    private Year year;

    @SerializedName("engineType")
    private EngineType engineType;

    @SerializedName("deleted")
    private boolean deleted;
}
