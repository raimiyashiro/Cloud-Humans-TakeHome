package com.raimiyashiro.cloudHumans.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternetTest {
    @JsonProperty("download_speed")
    private float downloadSpeed;
    @JsonProperty("upload_speed")
    private float uploadSpeed;
}
