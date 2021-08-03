package com.raimiyashiro.cloudHumans.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiError {
    private boolean success;
    private Map<String, String> errors = new HashMap<>();
}
