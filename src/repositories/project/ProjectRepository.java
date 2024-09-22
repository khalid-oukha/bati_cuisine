package repositories.project;

import entities.Project;

public interface ProjectRepository {
    boolean createProject(Project project);

    Project findByProjectId(int id);

    Project findByName(String name);

    Project updateProject(Project project);
}
