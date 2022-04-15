package nc.unc.ama.guest_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBills;

    private int idGuest;

    private int idBooking;

    private int idGuestOperation;

    @Builder
    public Bills(int idBills, int idGuest, int idBooking, int idGuestOperation) {
        this.idBills = idBills;
        this.idGuest = idGuest;
        this.idBooking = idBooking;
        this.idGuestOperation = idGuestOperation;
    }
}
