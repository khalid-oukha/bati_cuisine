package services;

import entities.Component;
import entities.Project;
import enums.ComponentType;
import repositories.component.ComponentRepository;
import repositories.component.ComponentRepositoryImpl;

import java.util.Optional;

public class ComponentService {
    private final ComponentRepository componentRepository;

    public ComponentService() {
        this.componentRepository = new ComponentRepositoryImpl();
    }

    public Component createComponent(String name, ComponentType componentType, double vatRate, Project project) {
        Component component = new Component(name, componentType, vatRate, project);
        if (componentRepository.create(component)) {
            return component;
        }
        return null;
    }

    public Optional<Component> findById(int id, Project project) {
        return componentRepository.findById(id, project);
    }

    public boolean updateComponent(Component component, Project project) {
        return componentRepository.update(component);
    }

    public boolean deleteComponent(Component component) {
        return componentRepository.delete(component);
    }
}
