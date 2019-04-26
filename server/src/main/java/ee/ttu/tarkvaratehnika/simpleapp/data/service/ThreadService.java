package ee.ttu.tarkvaratehnika.simpleapp.data.service;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.Board;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Post;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.thread.Thread;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.BoardRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.thread.PostRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.thread.ThreadRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ThreadService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Thread createThread(Long boardId, Long userId, String title, String timeStamp) {
        // Check if user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            // Check if board exists
            Optional<Board> boardOptional = boardRepository.findById(boardId);
            if (boardOptional.isPresent()) {

                // Set required objects
                Board board = boardOptional.get();

                // Create new thread
                Thread newThread = new Thread();
                newThread.setBoard(board);
                newThread.setPosts(new HashSet<>());
                newThread.setTimeStamp(timeStamp);
                newThread.setTitle(title);

                // Save thread
                final Thread thread = threadRepository.save(newThread);

                // Make connections
                board.getThreads().add(thread);

                // Save connections
                boardRepository.save(board);

                return thread;
            }
        }
        return null;
    }

    public Post createPost(Long threadId, Long userId, String timeStamp, String content, Boolean isOriginal, List<Long> replyToIds) {
        // Check if user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            // Check if thread exists
            Optional<Thread> threadOptional = threadRepository.findById(threadId);
            if (threadOptional.isPresent()) {

                // Set required objects
                User user = userOptional.get();
                Thread thread = threadOptional.get();
                List<Post> replyTo = postRepository.findAllById(replyToIds);

                // Create new post
                Post newPost = new Post();
                newPost.setThread(thread);
                newPost.setReplyTo(new HashSet<>(replyTo));
                newPost.setAuthor(user);
                newPost.setContent(content);
                newPost.setTimeStamp(timeStamp);
                newPost.setIsOriginal(isOriginal);

                // Save post
                final Post post = postRepository.save(newPost);

                // Make connections
                replyTo.forEach(p -> p.getReplyBy().add(post));
                thread.getPosts().add(post);
                user.getPosts().add(post);

                // Save Connections
                postRepository.saveAll(replyTo);
                threadRepository.save(thread);
                userRepository.save(user);

                return post;
            }
        }
        return null;
    }

}
