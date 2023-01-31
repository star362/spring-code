package transactiiondemo.dao;

import org.apache.ibatis.annotations.Mapper;
import transactiiondemo.entity.EmailInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (EmailInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-07 09:59:43
 */
@Mapper
public interface EmailInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmailInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<EmailInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param emailInfo 实例对象
     * @return 对象列表
     */
    List<EmailInfo> queryAll(EmailInfo emailInfo);

    /**
     * 新增数据
     *
     * @param emailInfo 实例对象
     * @return 影响行数
     */
    int insert(EmailInfo emailInfo);

    /**
     * 修改数据
     *
     * @param emailInfo 实例对象
     * @return 影响行数
     */
    int update(EmailInfo emailInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}