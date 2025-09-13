package com.smartgarage.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Year {

    @SerializedName("id")
    private int id;
    @SerializedName("year")
    private int year;
}
