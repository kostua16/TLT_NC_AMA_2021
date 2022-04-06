package nc.unc.ama.guest_service.entity;

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
    private int idOperation;

    @Column(name = "operationname")
    private String operationName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Builder
    public Operation(int idOperation, String operationName, String description, int price) {
        this.idOperation = idOperation;
        this.operationName = operationName;
        this.description = description;
        this.price = price;
    }
}
