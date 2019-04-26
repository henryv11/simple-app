package ee.ttu.tarkvaratehnika.simpleapp.data.model.user;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Post;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Person person;

    private String userName;

    private String password;

    private String timeStamp;

    private UserPreferences userPreferences;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Post> posts;

}
