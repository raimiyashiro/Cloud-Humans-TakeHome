package com.raimiyashiro.cloudHumans.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternetTestDTO {
    @JsonProperty("download_speed")
    private float downloadSpeed;
    @JsonProperty("upload_speed")
    private float uploadSpeed;
}
