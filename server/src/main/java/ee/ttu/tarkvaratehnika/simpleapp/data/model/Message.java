package ee.ttu.tarkvaratehnika.simpleapp.data.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String time;

    private String author;

    private String title;

    private String content;
}
