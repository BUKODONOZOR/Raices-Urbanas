package Contest.Project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "sender", nullable = false)
    private int sender;

    @Column(name = "recipient", nullable = false)
    private int recipient;

    public Message() {}

    public Message(String body, LocalDateTime date, int sender, int recipient) {
        this.body = body;
        this.date = date;
        this.sender = sender;
        this.recipient = recipient;
    }

    // Getters and Setters

}
