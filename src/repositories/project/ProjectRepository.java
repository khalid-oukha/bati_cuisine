package repositories.project;

import entities.Project;

public interface ProjectRepository {
    boolean createProject(Project project);

    Project findByProjectId(int id);
}
