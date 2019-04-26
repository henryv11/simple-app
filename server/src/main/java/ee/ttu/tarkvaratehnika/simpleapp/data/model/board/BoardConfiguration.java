package ee.ttu.tarkvaratehnika.simpleapp.data.model.board;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "Board_Configuration")
public class BoardConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @OneToOne(fetch = FetchType.LAZY)
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    private User administrator;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Board_Moderators",
            joinColumns = @JoinColumn(name = "moderator_id"),
            inverseJoinColumns = @JoinColumn(name="board_configuration_id")
    )
    private Set<User> moderators;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Board_Allowed_Users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="board_configuration_id")
    )
    private Set<User> allowedUsers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Board_Blocked_Users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="board_configuration_id")
    )
    private Set<User> blockedUsers;

}
