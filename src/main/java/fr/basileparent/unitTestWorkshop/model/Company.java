package fr.basileparent.unitTestWorkshop.model;

import fr.basileparent.unitTestWorkshop.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company {

    @Id
    @SequenceGenerator(name = "commpanySequence", sequenceName = "COMPANY_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyySequence")
    @Column(unique = true, nullable = false)
    public Integer id;

    @Enumerated(value=EnumType.STRING)
    public CompanyType companyType;

}
