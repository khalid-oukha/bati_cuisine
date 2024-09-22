package entities;

import enums.ComponentType;

public class Labor extends Component {
    private double hourlyRate;
    private double workingHours;
    private double workerProductivity;

    public Labor(int id, String name, ComponentType componentType, double vatRate, double hourlyRate, double workingHours, double workerProductivity, Project project) {
        super(id, name, componentType, vatRate, project);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
        this.workerProductivity = workerProductivity;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

}
