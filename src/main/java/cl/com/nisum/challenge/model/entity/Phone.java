package cl.com.nisum.challenge.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Phone")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private Integer number;

    @Column(nullable = false, length = 20)
    private Integer cityCode;

    @Column(nullable = false, length = 20)
    private Integer countryCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private User user;

}