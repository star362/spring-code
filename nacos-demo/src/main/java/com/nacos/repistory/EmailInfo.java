package com.nacos.repistory;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: EmailInfo2
 * @Description:
 * @date: 2019-10-19 19:35
 * @describe:
 */
@Data
@Alias("emailinfo")
public  class EmailInfo implements Serializable {

    private static final long serialVersionUID = 2239364235996575833L;

    @ApiModelProperty(value = "id",example = "1")
    private Integer id;
    @ApiModelProperty(value ="邮件接口人" ,example = "wangyu")
    private String toEmail;
    @ApiModelProperty(value ="邮件内容" ,example = "长度255")
    private String remarks;
    @ApiModelProperty(value ="邮件主题" ,example = "长度255")
    private String subject;
    @ApiModelProperty(value ="状态 1 默认生效" ,example = "1")
    private Integer state;
    @ApiModelProperty(value ="所属部门" ,example = "billing")
    private String depa;





}
