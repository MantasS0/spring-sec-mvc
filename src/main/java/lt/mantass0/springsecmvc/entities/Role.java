package lt.mantass0.springsecmvc.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "id_roles"),
            inverseJoinColumns = @JoinColumn(name = "id_privileges")
    )
    private Set<Privilege> privileges;

}
