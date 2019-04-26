package ee.ttu.tarkvaratehnika.simpleapp.data.service;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.Board;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.board.BoardConfiguration;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.BoardConfigurationRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.BoardRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.thread.PostRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.board.thread.ThreadRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardConfigurationRepository boardConfigurationRepository;

    public Board createBoard(Long userId, String title, String description, Boolean isPrivate) {
        // Check if user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {

            // Set required objects
            User user = userOptional.get();

            // Create board configuration
            BoardConfiguration newBoardConfiguration = new BoardConfiguration();
            newBoardConfiguration.setIsPrivate(isPrivate);
            newBoardConfiguration.setAdministrator(user);
            newBoardConfiguration.setAllowedUsers(new HashSet<>());
            newBoardConfiguration.setBlockedUsers(new HashSet<>());
            newBoardConfiguration.setModerators(new HashSet<>());

            // Create board
            Board newBoard = new Board();
            newBoard.setDescription(description);
            newBoard.setTitle(title);
            newBoard.setThreads(new HashSet<>());
            newBoard.setBoardConfiguration(newBoardConfiguration);

            // Make connections
            newBoard.setBoardConfiguration(newBoardConfiguration);
            newBoardConfiguration.setBoard(newBoard);

            // Save and return board
            return boardRepository.save(newBoard);
        }
        return null;
    }

    public BoardConfiguration editBoardConfiguration(Long boardConfigurationId, Boolean isPrivate, Long administratorId,
                                          List<Long> allowedUserIds, List<Long> blockedUserIds,
                                          List<Long> moderatorIds) {
        // Check if administrator exists
        Optional<User> administratorOptional = userRepository.findById(administratorId);
        if (administratorOptional.isPresent()) {
            // Check if board configuration exists
            Optional<BoardConfiguration> boardConfigurationOptional =
                    boardConfigurationRepository.findById(boardConfigurationId);
            if (boardConfigurationOptional.isPresent()) {
                // Set required objects
                BoardConfiguration boardConfiguration = boardConfigurationOptional.get();
                User administrator = administratorOptional.get();
                List<User> moderators = userRepository.findAllById(moderatorIds);
                List<User> allowedUsers = userRepository.findAllById(allowedUserIds);
                List<User> blockedUsers = userRepository.findAllById(blockedUserIds);

                // Make changes
                boardConfiguration.setIsPrivate(isPrivate);
                boardConfiguration.setAdministrator(administrator);
                boardConfiguration.setModerators(new HashSet<>(moderators));
                boardConfiguration.setAllowedUsers(new HashSet<>(allowedUsers));
                boardConfiguration.setBlockedUsers(new HashSet<>(blockedUsers));

                // Save and return board configuration
                return boardConfigurationRepository.save(boardConfiguration);
            }
        }
        return null;
    }
}
