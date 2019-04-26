package ee.ttu.tarkvaratehnika.simpleapp.data.model.user;


import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Thread;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "User_Preferences")
public class UserPreferences implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Followed_Threads",
            joinColumns = @JoinColumn(name = "thread_id"),
            inverseJoinColumns = @JoinColumn(name = "user_preferences_id")
    )
    private Set<Thread> followedThreads;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Followed_Boards",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_preferences_id")
    )
    private Set<Thread> followedBoards;

}
