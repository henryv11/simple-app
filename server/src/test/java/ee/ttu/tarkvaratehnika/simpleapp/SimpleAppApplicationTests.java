package ee.ttu.tarkvaratehnika.simpleapp;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.Board;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Post;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Thread;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.BoardRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.thread.PostRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.service.BoardService;
import ee.ttu.tarkvaratehnika.simpleapp.data.service.ThreadService;
import ee.ttu.tarkvaratehnika.simpleapp.data.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleAppApplicationTests {

    @Autowired
    private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
    private BoardService boardService;

	@Autowired
    private ThreadService threadService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserCreations() {
        User user = userService.createUser("", "", "", "", "");
		System.out.println(user);
		System.out.println(user.getPerson());
		System.out.println(user.getUserPreferences());
		System.out.println(user.getUserPreferences().getUser());
		System.out.println(user.getPerson().getUser());
	}

	@Test
    public void testBoardCreation() {
        User user = userService.createUser("", "", "", "", "");
	    Board board = boardService.createBoard(user.getId(), "", "",  false);
	    System.out.println(board.getCreationTimeStamp());
	    System.out.println(board.getBoardConfiguration());
	    System.out.println(board.getBoardConfiguration().getBoard());
    }

    @Test
    public void testThreadCreation() {
        User user = userService.createUser("", "", "", "", "");
        Board board = boardService.createBoard(user.getId(), "", "",  false);
        Thread thread = threadService.createThread(board.getId(), user.getId(), "");
        System.out.println(thread.getBoard().getThreads().size());
        System.out.println(board.getThreads().size());
    }

    @Test
    @Transactional
    public void testPostCreation() {
        User user = userService.createUser("", "", "", "", "");
        Board board = boardService.createBoard(user.getId(), "", "",  false);
        Thread thread = threadService.createThread(board.getId(), user.getId(), "");
        Post post1 = threadService.createPost(thread.getId(), user.getId(), "", true, new ArrayList<>());
        Post post2 = threadService.createPost(thread.getId(), user.getId(), "", false, new ArrayList<>());
        Post post3 = threadService.createPost(thread.getId(), user.getId(), "", false, new ArrayList<>());

        List<Long> replyTo = new ArrayList<>(Arrays.asList(post1.getId(), post2.getId(), post3.getId()));

        Post post4 = threadService.createPost(thread.getId(), user.getId(), "", false, replyTo);

        List<Post> posts = postRepository.findAll();

        for (Post p : posts) {
            System.out.println(p.getCreationTimeStamp());
            System.out.println(p.getReplyBy().size());
            System.out.println(p.getReplyTo().size());
        }

    }
}
