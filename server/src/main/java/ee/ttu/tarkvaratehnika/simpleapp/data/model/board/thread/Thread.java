package ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.Board;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Thread")
public class Thread implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_time_stamp")
    private Date creationTimeStamp;

    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Thread_Posts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "thread_id")
    )
    private Set<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public Post addPost(Post post) {
        posts.add(post);
        return post;
    }
}
