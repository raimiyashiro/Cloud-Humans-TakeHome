# Draft

> ProController.java

- @Post Request
- Mapper (DTO > Model.class)
- Next();

------
> ProService.java

```
@Autowired 
private EligibilityScoreAlgorithm algorithm; // Calculates Pro's score
@Autowired ProjectService projectService; // Calls a mock DataSource (ProjectRepository.java)

public ProEvaluation evaluate(Pro pro) {
  ProEvaluation evaluation = new ProEvaluation();
  
  evaluation.setScore(this.algorithm.calculateScore(pro));
  
  List<Project> availableProjects = this.projectService.getAvailableProjects();
  
  availableProjects.stream().forEach(project -> {
    if(pro.getScore() >= project.getRequiredScore()) {
      // See what to do next
    } else {
      evaluation.getIneligibleProjects().add(project.getName());
    }
  });
 
  return evaluation;
}

```
