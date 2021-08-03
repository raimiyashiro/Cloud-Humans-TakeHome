package com.raimiyashiro.cloudHumans.data;

import com.raimiyashiro.cloudHumans.model.Project;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProjectDataSource {
    public List<Project> listAvailableProjects() {
        return Arrays.asList(
                new Project("Calculate the Dark Matter of the universe for Nasa", 10),
                new Project("Determine if the Schrodinger's cat is alive", 5),
                new Project("Attend to users support for a YXZ Company", 3),
                new Project("Collect specific people information from their social media for XPTO Company", 2)
        );
    }
}
