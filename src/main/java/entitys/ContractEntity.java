package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contract", schema = "apartment", catalog = "")
public class ContractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UsersEntity idUser;
    @Basic
    @Column(name = "dateCreate")
    private Date dateCreate;
    @Basic
    @Column(name = "dateEnd")
    private Date dateEnd;
    @ManyToOne
    @JoinColumn(name = "idRoom")
    private RoomEntity idRoom;
    @Basic
    @Column(name = "price")
    private Double price;
}
