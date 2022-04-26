package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "apartment", catalog = "")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "passwordUser")
    private String passwordUser;
    @Basic
    @Column(name = "numberPhone")
    private String numberPhone;
    @Basic
    @Column(name = "sex")
    private Integer sex;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "isAdmin")
    private Integer isAdmin;
    @Basic
    @Column(name = "status")
    private Byte status;
    @OneToMany(mappedBy = "idUser")
    private List<ContractEntity> contractList;

}
