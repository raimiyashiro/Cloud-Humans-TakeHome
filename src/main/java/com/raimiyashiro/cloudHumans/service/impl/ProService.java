package com.raimiyashiro.cloudHumans.service.impl;

import com.raimiyashiro.cloudHumans.model.Evaluation;
import com.raimiyashiro.cloudHumans.model.Pro;
import com.raimiyashiro.cloudHumans.service.IProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProService implements IProService {

    @Autowired
    public ProService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private final ProjectService projectService;

    @Override
    public Evaluation evaluatePro(Pro pro) {
        return null;
    }
}
