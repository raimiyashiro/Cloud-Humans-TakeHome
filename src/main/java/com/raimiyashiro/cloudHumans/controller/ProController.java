package com.raimiyashiro.cloudHumans.controller;

import com.raimiyashiro.cloudHumans.controller.dto.ProDTO;
import com.raimiyashiro.cloudHumans.model.EligibilityScore;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/pro")
public class ProController {

    @PostMapping("/score")
    @ResponseBody
    public EligibilityScore getScore(@RequestBody @Valid ProDTO proDTO) {
        return null;
    }
}
