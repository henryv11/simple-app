package ee.ttu.tarkvaratehnika.simpleapp.data.model.board;


import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Thread;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "Board")
public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "time_stamp")
    private String timeStamp;

    @OneToOne(fetch = FetchType.LAZY)
    private BoardConfiguration boardConfiguration;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Board_Threads",
            joinColumns = @JoinColumn(name = "thread_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    private Set<Thread> threads;
}
