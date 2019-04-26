package ee.ttu.tarkvaratehnika.simpleapp.data.model.board;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class BoardConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Boolean isPrivate;

    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    private User administrator;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> moderators;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> allowedUsers;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> blockedUsers;

}
