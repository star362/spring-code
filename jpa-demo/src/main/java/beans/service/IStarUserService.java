package beans.service;

import beans.entity.StarUser;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * (StarUser)表服务接口
 *
 * @author star
 * @since 2023-02-08 17:39:49
 */
public interface IStarUserService {
    StarUser queryById(Integer id);

    Page<StarUser> queryAllByLimit(int offset, int limit);

    StarUser insert(StarUser starUser);

    StarUser update(StarUser starUser);

    boolean deleteById(Integer id);

    List<StarUser> getAll();
}

