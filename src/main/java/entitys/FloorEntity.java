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
@Table(name = "Floor", schema = "apartment", catalog = "")
public class FloorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameFloor")
    private String nameFloor;
    @ManyToOne
    @JoinColumn(name = "idBuilding")
    private BuildingEntity idBuilding;
    @OneToMany(mappedBy = "idFloor")
    private List<RoomEntity> roomList;
}
