package fr.basileparent.unitTestWorkshop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pony")
public class Pony {

    @Id
    @SequenceGenerator(name = "ponySequence", sequenceName = "PONY_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ponySequence")
    @Column(unique = true, nullable = false)
    public Integer id;

    public String sex;

    public String name;

    public Integer age;

    public LocalDateTime arrivalDate;

}
