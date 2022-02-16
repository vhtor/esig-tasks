package esig.interns.tasks.enumeration;

public enum Status {
    UNFINISHED("Não Concluída"),
    FINISHED("Concluída");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
