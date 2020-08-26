package cl.com.nisum.challenge.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private Integer number;

    @Column(nullable = false, length = 20)
    private Integer cityCode;

    @Column(nullable = false, length = 20)
    private Integer countryCode;

    @OneToMany(mappedBy = "phones")
    private Set<User> user;
}
