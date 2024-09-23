package repositories.Labor;

import entities.Labor;
import entities.Project;

import java.util.List;
import java.util.Optional;

public interface LaborRepository {
    boolean create(Labor labor);

    List<Labor> getAll(Project project);

    boolean update(Labor labor);

    Optional<Labor> findById(int id, Project project);
}
