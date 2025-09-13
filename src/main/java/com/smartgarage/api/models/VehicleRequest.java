package com.smartgarage.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    @SerializedName("brandName")
    private String brandName;
    @SerializedName("modelName")
    private String modelName;
    @SerializedName("year")
    private int year;
    @SerializedName("engineType")
    private String engineType;
}
