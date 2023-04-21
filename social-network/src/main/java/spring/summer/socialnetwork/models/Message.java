package spring.summer.socialnetwork.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;

import java.time.LocalDateTime;
import java.util.function.ToDoubleBiFunction;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    private User sender;

    private String message;

    //TODO
    //update dokumentacji po dodaniu pola datetime
    private LocalDateTime addedTime;

}
