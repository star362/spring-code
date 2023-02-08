package beans.controller;

import beans.entity.StarUser;
import beans.service.IStarUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StarUser)表控制层
 *
 * @author star
 * @since 2023-02-08 17:39:49
 */
@RestController
@RequestMapping("starUser")
public class StarUserController {
    /**
     * 服务对象
     */
    @Resource
    private IStarUserService starUserService;

    /**
     * 查询全部
     *
     * @return 全部数据
     */
    @GetMapping("/findAll")
    public List<StarUser> getAll() {
        return this.starUserService.getAll();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public StarUser selectOne(Integer id) {
        return this.starUserService.queryById(id);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @GetMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.starUserService.deleteById(id));
    }

    /**
     * 新增数据
     *
     * @param starUser 实体
     * @return 新增结果
     */
    @PostMapping("/save")
    public ResponseEntity<StarUser> add(StarUser starUser) {
        return ResponseEntity.ok(this.starUserService.insert(starUser));
    }

    /**
     * 编辑数据
     *
     * @param starUser 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public ResponseEntity<StarUser> edit(StarUser starUser) {
        return ResponseEntity.ok(this.starUserService.update(starUser));
    }

}

