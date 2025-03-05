
package dto;

import java.time.LocalDate;


public class ProjectDTO {
    private String projectName;
    private String description;
    private String status;
    private LocalDate estimatedLaunch;
    

    public String getProjectName() {
        return projectName;
    }

    public ProjectDTO(String projectName, String description, String status, LocalDate estimatedLaunch) {
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimatedLaunch = estimatedLaunch;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEstimatedLaunch() {
        return estimatedLaunch;
    }

    public void setEstimatedLaunch(LocalDate estimatedLaunch) {
        this.estimatedLaunch = estimatedLaunch;
    }
    
    
    
}
