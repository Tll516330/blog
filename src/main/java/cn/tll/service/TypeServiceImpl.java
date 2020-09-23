package cn.tll.service;

import cn.tll.NotFoundException;
import cn.tll.dao.TypeDao;
import cn.tll.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author tll
 * @create 2020/9/18 14:29
 */
@Service
public class TypeServiceImpl implements TypeService{

    /**
     * 自动注入
     */
    @Autowired
    private TypeDao typeDao;

    /**
     * 新增类型
     * @param type
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    /**
     * 根据id删除类型
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);
    }

    /**
     * 更新类型
     * @param id
     * @param type
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type updateType(Long id, Type type) {
        Type t = new Type();
        t.setId(id);
        Example<Type> example = Example.of(t);
        Optional<Type> one = typeDao.findOne(example);
        Type tt = one.get();
        if (tt==null){
            throw new NotFoundException("Type不存在");
        }
        BeanUtils.copyProperties(type,tt);
        return typeDao.save(tt);

    }

    /**
     * 根据id查询类型
     * @param id
     * @return
     */
    @Override
    public Type getType(Long id) {
        Type t = new Type();
        t.setId(id);
        Example<Type> example = Example.of(t);
        Optional<Type> one = typeDao.findOne(example);
        return one.get();

    }

    /**
     * 根据条件分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<Type> listPage(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    /**
     * 根据名字查询Type
     * @param name
     * @return
     */
    @Override
    public Type findByName(String name) {
        return null;
    }
}
