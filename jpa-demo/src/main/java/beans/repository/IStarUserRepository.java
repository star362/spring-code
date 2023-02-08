package beans.repository;

import beans.entity.StarUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * (StarUser)表数据库访问层
 *
 * @author star
 * @since 2023-02-08 17:39:49
 */
public interface IStarUserRepository extends JpaRepository<StarUser, Integer>, JpaSpecificationExecutor<StarUser> {

}

