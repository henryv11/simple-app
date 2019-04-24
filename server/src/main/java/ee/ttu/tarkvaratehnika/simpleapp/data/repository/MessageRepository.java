package ee.ttu.tarkvaratehnika.simpleapp.data.repository;

import ee.ttu.tarkvaratehnika.simpleapp.data.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long>, PagingAndSortingRepository<Message, Long> {

}
