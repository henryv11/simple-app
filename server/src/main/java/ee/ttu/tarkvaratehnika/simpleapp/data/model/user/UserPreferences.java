package ee.ttu.tarkvaratehnika.simpleapp.data.model.user;


import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Thread;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class UserPreferences implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Thread> followedThreads;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Thread> followedBoards;

}
