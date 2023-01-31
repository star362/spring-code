package com.nacos.repistory;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Alias("staruser")
public class StarUser implements Serializable {

	private static final long serialVersionUID = 1578990167506L;

	@ApiModelProperty(value = "",name = "id")
	private Integer id;

	@ApiModelProperty(value = "",name = "user_name")
	private String userName;

	@ApiModelProperty(value = "",name = "user_age")
	private Integer userAge;

	@ApiModelProperty(value = "",name = "user_sex")
	private String userSex;

}