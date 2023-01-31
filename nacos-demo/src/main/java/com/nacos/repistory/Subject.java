package com.nacos.repistory;

import java.math.BigDecimal;
import java.util.*;
import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;

@Data
@Alias("subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = 1575538674824L;

	@ApiModelProperty(value = "id",name = "id2")
	private Integer id2;

	@ApiModelProperty(value = "名字",name = "name")
	private String name;

	@ApiModelProperty(value = "年级",name = "age")
	private Integer age;

	@ApiModelProperty(value = "高",name = "height")
	private Integer height;

	@ApiModelProperty(value = "款",name = "weight")
	private Integer weight;

	@ApiModelProperty(value = "1",name = "active")
	private BigDecimal active;

	@ApiModelProperty(value = "时间",name = "dt")
	private Date dt;

}