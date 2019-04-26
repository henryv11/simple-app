package ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread;


import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Thread thread;

    private String timeStamp;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @OneToOne
    private Reply reply;
}
