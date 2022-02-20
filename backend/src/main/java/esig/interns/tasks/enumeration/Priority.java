package esig.interns.tasks.enumeration;

public enum Priority {
    LOW("Baixa"),
    MEDIUM("Media"),
    HIGH("Alta");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return this.priority;
    }
}
