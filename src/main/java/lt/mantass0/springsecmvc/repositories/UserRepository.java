package lt.mantass0.springsecmvc.repositories;

import lt.mantass0.springsecmvc.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    User getUserById(int id);

    User getUserByUserName(String userName);

    Page<User> findAll(Pageable pageable);
}
