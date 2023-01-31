package transactiiondemo.service;

import transactiiondemo.entity.EmailInfo;
import java.util.List;

/**
 * (EmailInfo)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 09:59:45
 */
public interface EmailInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmailInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<EmailInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param emailInfo 实例对象
     * @return 实例对象
     */
    EmailInfo insert(EmailInfo emailInfo);

    /**
     * 修改数据
     *
     * @param emailInfo 实例对象
     * @return 实例对象
     */
    EmailInfo update(EmailInfo emailInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}