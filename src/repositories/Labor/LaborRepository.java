package repositories.Labor;

import entities.Labor;
import entities.Project;

import java.util.List;

public interface LaborRepository {
    boolean create(Labor labor);

    List<Labor> getAll(Project project);
}
