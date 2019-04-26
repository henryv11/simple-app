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

    public User createUser(User user) {
        if (user.getId() == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public UserPreferences updateUserPreferences(UserPreferences userPreferences) {
        Optional<UserPreferences> userPreferencesOptional = userPreferencesRepository.findById(userPreferences.getId());
        if (userPreferencesOptional.isPresent()) {
            return userPreferencesRepository.save(userPreferences);
        }
        return null;
    }


}
