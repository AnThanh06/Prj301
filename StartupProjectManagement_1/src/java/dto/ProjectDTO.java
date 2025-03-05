
package dto;


import java.sql.Date;
import java.time.LocalDate;


public class ProjectDTO {
    private int id;
    private String projectName;
    private String description;
    private String status;
    private Date estimatedLaunch;

    public ProjectDTO(int id, String projectName, String description, String status, Date estimatedLaunch) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimatedLaunch = estimatedLaunch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
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

    public Date getEstimatedLaunch() {
        return estimatedLaunch;
    }

    public void setEstimatedLaunch(Date estimatedLaunch) {
        this.estimatedLaunch = estimatedLaunch;
    }

    
   
    
    
    
}
