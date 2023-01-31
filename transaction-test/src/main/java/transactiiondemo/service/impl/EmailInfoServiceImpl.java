package transactiiondemo.service.impl;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Transactional;
import transactiiondemo.entity.EmailInfo;
import transactiiondemo.dao.EmailInfoDao;
import transactiiondemo.service.EmailInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EmailInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 09:59:46
 */
@Service("emailInfoService")
public class EmailInfoServiceImpl implements EmailInfoService, BeanPostProcessor {
    @Resource
    private EmailInfoDao emailInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EmailInfo queryById(Integer id) {
        return this.emailInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<EmailInfo> queryAllByLimit(int offset, int limit) {
        return this.emailInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param emailInfo 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public EmailInfo insert(EmailInfo emailInfo) {
        this.emailInfoDao.insert(emailInfo);
        return emailInfo;
    }

    /**
     * 修改数据
     *
     * @param emailInfo 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public EmailInfo update(EmailInfo emailInfo) {
        this.emailInfoDao.update(emailInfo);
        return null;
//        return this.queryById(emailInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        return this.emailInfoDao.deleteById(id) > 0;
    }
}