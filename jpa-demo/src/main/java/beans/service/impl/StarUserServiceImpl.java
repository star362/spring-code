package beans.service.impl;

import beans.entity.StarUser;
import beans.repository.StarUserRepository;
import beans.service.IStarUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StarUser)表服务实现类
 *
 * @author star
 * @since 2023-02-08 17:41:16
 */
@Service("starUserService")
public class StarUserServiceImpl implements IStarUserService {

    /**
     * 数据库交互对象
     */
    @Resource
    private StarUserRepository starUserRepository;

    /**
     * 查询单个的方法
     *
     * @param id
     * @return 单个对象
     */
    @Override
    public StarUser queryById(Integer id) {
        return this.starUserRepository.findById(id).orElse(null);
    }

    /**
     * 查询全部的方法
     *
     * @return 全部对象的集合
     */
    @Override
    public List<StarUser> getAll() {
        return this.starUserRepository.findAll();

    }

    /**
     * 分页查询的方法
     *
     * @param cp 初始页
     * @param ps 页面数目
     * @return 页面对象组
     */
    @Override
    public Page<StarUser> queryAllByLimit(int cp, int ps) {
        return this.starUserRepository.findAll(PageRequest.of((cp - 1) * ps, ps));
    }

    /**
     * 新增的方法
     *
     * @param starUser 新增的对象
     * @return
     */
    @Override
    public StarUser insert(StarUser starUser) {

        return this.starUserRepository.save(starUser);
    }

    /**
     * 修改的方法
     *
     * @param starUser 修改的对象
     * @return
     */
    @Override
    public StarUser update(StarUser starUser) {

        return this.starUserRepository.save(starUser);
    }

    /**
     * 删除单个的方法
     *
     * @param id 删除的id
     * @return 布尔值
     */
    @Override
    public boolean deleteById(Integer id) {

        try {
            this.starUserRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}

