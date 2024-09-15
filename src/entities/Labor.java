package entities;

public class Labor extends Component{
    private double hourlyRate;
    private double workingHours;
    private double workerProductivity;

    public Labor(int id, String name, String componentType, double vatRate, double hourlyRate, double workingHours, double workerProductivity) {
        super(id, name, componentType, vatRate);
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
