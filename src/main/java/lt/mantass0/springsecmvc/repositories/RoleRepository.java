package lt.mantass0.springsecmvc.repositories;

import lt.mantass0.springsecmvc.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {

    Role getRoleByName(String name);
}
