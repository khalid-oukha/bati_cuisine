package repositories.project;

import entities.Project;

import java.util.HashMap;
import java.util.Optional;

public interface ProjectRepository {
    boolean createProject(Project project);

    Optional<Project> findByProjectId(int id);

    Project findByName(String name);

    Project updateProject(Project project);

    HashMap<Integer, Project> findAllProjects();
}
