package cn.tll.dao;

import cn.tll.pojo.Tag;
import cn.tll.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tll
 * @create 2020/9/23 15:27
 */
public interface TagsDao extends JpaRepository<Tag,Long> {

    /**
     * 通过name查询Tag
     * @param name
     * @return
     */
    Tag findByName(String name);
}
