package ee.ttu.tarkvaratehnika.simpleapp.data.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String time;

    private String author;

    private String title;

    private String content;
}
