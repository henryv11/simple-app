package ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            joinColumns = {
                    @JoinColumn(
                            name = "replier",
                            referencedColumnName = "id",
                            nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "original_poster",
                            referencedColumnName = "id",
                            nullable = false)
            }
    )
    private Set<Reply> replyTo;

    @ManyToMany(mappedBy = "replyTo")
    private Set<Reply> replyBy;
}
