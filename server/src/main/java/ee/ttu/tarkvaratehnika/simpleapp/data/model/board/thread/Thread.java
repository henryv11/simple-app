package ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.Board;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Thread implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String createdAtTimeStamp;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
