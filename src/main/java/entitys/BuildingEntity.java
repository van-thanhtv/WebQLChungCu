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
@Table(name = "Building", schema = "apartment", catalog = "")
public class BuildingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "naemBuilding")
    private String naemBuilding;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "idUser")
    private Integer idUser;
    @Basic
    @Column(name = "status")
    private Integer status;
    @OneToMany(mappedBy = "idBuilding")
    private List<FloorEntity> floorList;
}
