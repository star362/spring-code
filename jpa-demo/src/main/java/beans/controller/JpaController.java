package beans.controller;

import beans.config.SpringBeanUtils;
import beans.entity.StarUser;
import beans.repository.StarUserRepository;
import net.bytebuddy.matcher.TypeSortMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyu
 * @date: 2020-05-08 18:43
 *
 * <p>
 */
@RestController
public class JpaController {


    @Autowired
    private StarUserRepository starUserRepository;


    @GetMapping("getall")
    public List<StarUser> getAll(){
        return starUserRepository.findAll();
    }

    @GetMapping("geton")
    public StarUser geton(){
        return starUserRepository.findFirstById(1, Sort.by("userName").descending()).get(0);
    }

    @GetMapping("findFirst3ByUserName")
    public List<StarUser> findFirst3ByUserName(){
        return starUserRepository.findFirst3ByUserName("bb",Sort.by("id").descending());
    }

    @GetMapping("update")
    public Integer updat(){
        StarUser starUser = new StarUser();
        starUser.setId(1);
        starUser.setUserAge(1);
        starUser.setUserName("上三");
        starUser.setUserSex("男");
        Integer uptable = starUserRepository.uptable(starUser, 1);
        return uptable;

    }
    @GetMapping("findUname")
    public StarUser findUname(){

        return starUserRepository.findUname("张三").get();
    }



    /*@GetMapping("fingiter")
    public List<StarUser> fingiter(){

        starUserRepository.findAll(starUserRepository.findFirst3ByUserName("bb",Sort.by("id").descending()));

        return starUserRepository.findUname("张三").get();
    }*/

    @GetMapping("getbean")
    public String[] fingiter2(){


        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }












}
