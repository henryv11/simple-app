package ee.ttu.tarkvaratehnika.simpleapp.data.service;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.Person;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.User;
import ee.ttu.tarkvaratehnika.simpleapp.data.model.user.UserPreferences;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.user.PersonRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.user.UserPreferencesRepository;
import ee.ttu.tarkvaratehnika.simpleapp.data.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    public User createUser(String userName, String password, String firstName,
                           String lastName, String email) {
        // Create user
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPosts(new HashSet<>());

        // Create person
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setUser(user);

        // Create user preferences
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setFollowedBoards(new HashSet<>());
        userPreferences.setFollowedThreads(new HashSet<>());
        userPreferences.setUser(user);

        // Make connections for user
        user.setPerson(person);
        user.setUserPreferences(userPreferences);

        // Save and return user
        return userRepository.save(user);
    }
}
