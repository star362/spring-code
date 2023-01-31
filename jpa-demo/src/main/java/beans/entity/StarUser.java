package beans.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 *  star_user
 * @author star 2020-05-08
 */
@Entity
@Data
@Table(name="star_user")
//@ApiModel("star_user")
public class StarUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
     * id
     */
//    @ApiModelProperty("id")
//    @Column("id")
    private Integer id;

    /**
     * user_name
     */
//    @ApiModelProperty("user_name")
//    @Column("user_name")
    private String userName;

    /**
     * user_age
     */
//    @ApiModelProperty("user_age")
//    @Column("user_age")
    private Integer userAge;

    /**
     * user_sex
     */
//    @ApiModelProperty("user_sex")
//    @Column("user_sex")
    private String userSex;


}
