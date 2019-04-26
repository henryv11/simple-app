package ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.Board;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "Thread")
public class Thread implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "time_stamp")
    private String timeStamp;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Thread_Posts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "thread_id")
    )
    private Set<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
