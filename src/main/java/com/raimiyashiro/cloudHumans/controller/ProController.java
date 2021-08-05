package com.raimiyashiro.cloudHumans.controller;

import com.raimiyashiro.cloudHumans.controller.dto.EvaluationDTO;
import com.raimiyashiro.cloudHumans.controller.dto.ProDTO;
import com.raimiyashiro.cloudHumans.model.Evaluation;
import com.raimiyashiro.cloudHumans.model.Pro;
import com.raimiyashiro.cloudHumans.service.impl.ProService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/pro")
public class ProController {

    private final ProService proService;

    @Autowired
    public ProController(ProService proService) {
        this.proService = proService;
    }

    @PostMapping("/score")
    @ResponseBody
    public EvaluationDTO getScore(@RequestBody @Valid ProDTO proDTO) {
        var mapper = new ModelMapper();
        var pro = mapper.map(proDTO, Pro.class);

        Evaluation evaluation = this.proService.evaluatePro(pro);

        return null;
    }
}
