package com.smartgarage.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
public class ClientCarResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("vin")
    private String vin;

    @SerializedName("licensePlate")
    private String licensePlate;

    @SerializedName("vehicle")
    private VehicleResponse vehicle;

    @SerializedName("deleted")
    boolean deleted;
}
