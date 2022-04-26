package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Room", schema = "apartment", catalog = "")
public class RoomEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameRoom")
    private String nameRoom;
    @ManyToOne
    @JoinColumn(name = "idFloor")
    private FloorEntity idFloor;
    @OneToMany(mappedBy = "idRoom")
    private List<ContractEntity> contractList;
}
