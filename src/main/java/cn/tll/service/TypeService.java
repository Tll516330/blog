package cn.tll.service;

import cn.tll.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author tll
 * @create 2020/9/18 14:25
 */
public interface TypeService {

    /**
     * 新增类型
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 删除类型
     * @param id
     */
    void deleteType(Long id);

    /**
     * 更新类型
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 查询类型
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页查询类型
     * @param pageable
     * @return
     */
    Page<Type> listPage(Pageable pageable);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Type findByName(String name);
}
