package repositories.component;

import entities.Component;
import entities.Project;

public interface ComponentRepository {
    boolean create(Component component);

    Component findById(int id, Project project);

    boolean update(Component component);
}
