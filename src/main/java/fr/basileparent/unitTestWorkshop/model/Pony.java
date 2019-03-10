package fr.basileparent.unitTestWorkshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Pony {

    @Id
    public Integer id;

    public String sex;

    public String name;

    public Integer age;

    public LocalDateTime arrivalDate;

}
