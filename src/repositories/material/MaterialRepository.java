package repositories.material;

import entities.Material;
import entities.Project;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository {
    boolean create(Material material);

    List<Material> getAll(Project project);

    boolean update(Material material);

    Optional<Material> findById(int id, Project project);

}
