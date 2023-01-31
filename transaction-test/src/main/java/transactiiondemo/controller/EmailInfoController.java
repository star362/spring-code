package transactiiondemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transactiiondemo.POJO.VO.ResultVO;
import transactiiondemo.POJO.enums.BaseDataResultEnum;
import transactiiondemo.POJO.exception.BaseException;
import transactiiondemo.POJO.util.ResultVOUtil;
import transactiiondemo.config.SpringBeanUtils;
import transactiiondemo.entity.EmailInfo;
import transactiiondemo.service.EmailInfoService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (EmailInfo)表控制层
 *
 * @author makejava
 * @since 2020-05-07 09:59:47
 */
@RestController
@RequestMapping("emailInfo")
public class EmailInfoController {

    private static final Logger log = LoggerFactory.getLogger(EmailInfoController.class);

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 服务对象
     */
    @Resource
    private EmailInfoService emailInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public EmailInfo selectOne(Integer id) {
        return this.emailInfoService.queryById(id);
    }

    @Transactional()
    @GetMapping("test")
    public String test() {
        EmailInfo in = new EmailInfo();
//        in.setId(2);
        in.setDepa("2");
        in.setRemarks("2");
        in.setState(2);
        in.setSubject("2");
        in.setToEmail("4");
        emailInfoService.insert(in);
        // int i = 1 / 0;
        EmailInfo up = new EmailInfo();
        up.setId(5);
        up.setDepa("2");
        up.setRemarks("2");
        up.setState(2);
        up.setSubject("2");
        up.setToEmail("3");
        emailInfoService.update(up);


        return "success";
    }


    /**
     * 编程式事务实现
     *
     * @return
     */
    @GetMapping("test1")
    public String test1() {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        //def.setName("SomeTxName");
       // def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {

            EmailInfo in = new EmailInfo();
//        in.setId(2);
            in.setDepa("2");
            in.setRemarks("2");
            in.setState(2);
            in.setSubject("2");
            in.setToEmail("4");
            emailInfoService.insert(in);


            EmailInfo up = new EmailInfo();
            up.setId(5);
            up.setDepa("2");
            up.setRemarks("2");
            up.setState(2);
            up.setSubject("2");
            up.setToEmail("3");
            emailInfoService.update(up);
        } catch (Exception ex) {
            log.info("========事务失败");
            txManager.rollback(status);
            throw ex;
        }

        log.info("=======");
        txManager.commit(status);


        return "success";

    }


    /**
     * jdbctemplate
     *
     * @return
     */
    @GetMapping("test2")
    public String test2() {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            String sql = "update email_info set to_email='" + 7 + "' where id=" + 5;

            jdbcTemplate.update(sql);
            String sql2 = "update email_info set to_email='" + 7 + "' where id=" + 7;

            jdbcTemplate.update(sql2);


        } catch (Exception ex) {
            log.info("========事务失败");
            txManager.rollback(status);
            throw ex;
        }

        log.info("=======");
        txManager.commit(status);


        return "success";

    }


    @GetMapping("getbean")
    public ResultVO<String[]> getBeans() {

        return ResultVOUtil.success(SpringBeanUtils.getContext().getBeanDefinitionNames());
    }

    @GetMapping("getuser")
    public String getemail(@Valid EmailInfo emailInfo){

        log.info("========getusern   请求");

        return "success";
    }

    @GetMapping("getuser2")
    public String getemail( String id){
        Assert.notNull(id, "获取bean的名称id不能为空");

        log.info("========getusernid   请求");

        return "success";
    }
    @GetMapping("getuser3")
    public String[] getemail3( ){


//        throw new BaseException("错误展示信息");
//        throw new BaseException(BaseDataResultEnum.SYSTEM_ERROR,"错误展示信息!!!!!!");
//        throw new BaseException(9999,"99999","错误展示信息!!!!!!");

        return SpringBeanUtils.getContext().getBeanDefinitionNames();

//        return "success";
    }



}