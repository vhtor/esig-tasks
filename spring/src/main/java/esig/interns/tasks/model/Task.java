package esig.interns.tasks.model;

import esig.interns.tasks.enumeration.Priority;
import esig.interns.tasks.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "O número da tarefa não pode estar vazio ou nulo")
    private String number;
    private String title;
    private String owner;
    private String deadline;
    private Status status;
    private Priority priority;
}
