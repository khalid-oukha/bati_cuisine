package repositories.component;

import entities.Component;
import entities.Project;

import java.util.Optional;

public interface ComponentRepository {
    boolean create(Component component);

    Optional<Component> findById(int id, Project project);

    boolean update(Component component);

    boolean delete(Component component);
}
