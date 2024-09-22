package repositories.material;

import entities.Material;
import entities.Project;

import java.util.List;

public interface MaterialRepository {
    boolean create(Material material);

    List<Material> getAll(Project project);

}
