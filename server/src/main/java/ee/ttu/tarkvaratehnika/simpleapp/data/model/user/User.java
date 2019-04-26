package ee.ttu.tarkvaratehnika.simpleapp.data.model.user;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Post;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "time_stamp")
    private String timeStamp;

    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    @OneToOne(fetch = FetchType.LAZY)
    private UserPreferences userPreferences;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Posts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Post> posts;

}
