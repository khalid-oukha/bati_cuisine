package services;

import entities.Component;
import entities.Project;
import enums.ComponentType;
import repositories.component.ComponentRepository;
import repositories.component.ComponentRepositoryImpl;

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

    public Component findById(int id, Project project) {
        return componentRepository.findById(id, project);
    }

}
