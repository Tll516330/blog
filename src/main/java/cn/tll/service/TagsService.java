package cn.tll.service;

import cn.tll.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author tll
 * @create 2020/9/23 15:24
 */
public interface TagsService {

    /**
     * 新增标签
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 删除标签
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 更改标签
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id,Tag tag);

    /**
     * 根据id 查询标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Tag> listPage(Pageable pageable);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Tag findByName(String name);
}
