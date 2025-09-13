package com.smartgarage.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCarRequest {

    @SerializedName("vin")
    private String vin;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("brandName")
    private String brandName;
    @SerializedName("modelName")
    private String modelName;
    @SerializedName("year")
    private int year;
    @SerializedName("engineType")
    private String engineType;
}
