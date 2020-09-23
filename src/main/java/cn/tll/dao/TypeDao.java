package cn.tll.dao;

import cn.tll.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tll
 * @create 2020/9/18 14:31
 */
public interface TypeDao extends JpaRepository<Type,Long> {

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    Type findByName(String name);
}
