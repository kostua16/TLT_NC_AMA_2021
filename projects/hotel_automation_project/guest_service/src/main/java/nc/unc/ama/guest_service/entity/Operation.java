package nc.unc.ama.guest_service.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int idOperation;

    @Column(name = "operationname")
    private String operationName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;
}
