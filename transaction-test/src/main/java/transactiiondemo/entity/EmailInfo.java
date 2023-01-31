package transactiiondemo.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * (EmailInfo)实体类
 *
 * @author makejava
 * @since 2020-05-07 09:59:41
 */
public class EmailInfo implements Serializable {
    private static final long serialVersionUID = 126495353085603468L;
    /**
    * id
    */
    @NotNull(message = "用户id不能为空")
    private Integer id;
    /**
    * 邮件
    */
    private String toEmail;
    /**
    * 备注
    */
    private String remarks;
    /**
    * 状态
    */
    private Integer state;
    /**
    * 预留字段
    */
    private String depa;
    
    private String subject;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDepa() {
        return depa;
    }

    public void setDepa(String depa) {
        this.depa = depa;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}