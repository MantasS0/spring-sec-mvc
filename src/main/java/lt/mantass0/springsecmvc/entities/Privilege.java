package lt.mantass0.springsecmvc.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "privileges")
@Data
public class Privilege {
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

}
