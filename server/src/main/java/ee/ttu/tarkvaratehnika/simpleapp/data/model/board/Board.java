package ee.ttu.tarkvaratehnika.simpleapp.data.model.board;


import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Thread;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String timeStamp;

    @OneToOne(cascade = CascadeType.PERSIST)
    private BoardConfiguration boardConfiguration;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Thread> threads;
}
