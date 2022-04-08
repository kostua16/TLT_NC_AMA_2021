package nc.unc.ama.guest_service.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Класс услуг со свойствами <b>idOperation</b>, <b>operationName</b>, <b>description</b> и <b>price</b> .
 * @autor Курочкин Владислав
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "operations")

public class Operation {

    /** Поле id услуги */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int idOperation;

    /** Поле название услуги */
    @Column(name = "operationname")
    private String operationName;

    /** Поле описание услуги */
    @Column(name = "description")
    private String description;

    /** Поле цена */
    @Column(name = "price")
    private int price;


    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param idOperation - id услуги
     * @param operationName - название услуги
     * @param description - описание услуги
     * @param price - цена
     */
    @Builder
    public Operation(int idOperation, String operationName, String description, int price) {
        this.idOperation = idOperation;
        this.operationName = operationName;
        this.description = description;
        this.price = price;
    }
}
