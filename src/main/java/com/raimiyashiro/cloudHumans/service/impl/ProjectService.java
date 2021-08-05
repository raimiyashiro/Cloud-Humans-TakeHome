package com.raimiyashiro.cloudHumans.service.impl;

import com.raimiyashiro.cloudHumans.model.Project;
import com.raimiyashiro.cloudHumans.repository.ProjectRepository;
import com.raimiyashiro.cloudHumans.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAvailableProjects() {
        return this.projectRepository.getAll();
    }
}
