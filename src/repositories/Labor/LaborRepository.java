package repositories.Labor;

import entities.Labor;
import entities.Project;

import java.util.List;

public interface LaborRepository {
    boolean create(Labor labor);

    List<Labor> getAll(Project project);

    boolean update(Labor labor);

    Labor findById(int id, Project project);
}
