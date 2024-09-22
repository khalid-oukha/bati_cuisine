package entities;

import java.time.LocalDate;

public class Quote {
    private int id;
    private double estimatedAmount;
    private LocalDate issueDate;
    private LocalDate validityDate;
    private boolean accepted;
    private Project project;

    public Quote(int id, double estimatedAmount, LocalDate issueDate, LocalDate validityDate, Project project) {
        this.id = id;
        this.estimatedAmount = estimatedAmount;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.accepted = false;
        this.project = project;
    }

    public void acceptQuote() {
        this.accepted = true;
    }

}
