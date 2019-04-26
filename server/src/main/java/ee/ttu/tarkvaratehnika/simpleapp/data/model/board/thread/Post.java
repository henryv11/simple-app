package ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread;


import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Thread thread;

    private Boolean isOriginal;

    private String timeStamp;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToMany
    @JoinTable(
            joinColumns = {
                    @JoinColumn(
                            name = "replier_id",
                            referencedColumnName = "id",
                            nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "original_poster_id",
                            referencedColumnName = "id",
                            nullable = false)
            }
    )
    private Set<Post> replyTo;

    @ManyToMany(mappedBy = "replyTo")
    private Set<Post> replyBy;
}
