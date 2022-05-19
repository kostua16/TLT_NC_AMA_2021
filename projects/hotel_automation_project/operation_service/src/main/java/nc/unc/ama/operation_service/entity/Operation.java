package nc.unc.ama.operation_service.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "operations")

public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idOperation;

    @Column(name = "operationname")
    private String operationName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Builder
    public Operation(Long idOperation, String operationName, String description, Double price) {
        this.idOperation = idOperation;
        this.operationName = operationName;
        this.description = description;
        this.price = price;
    }
}
