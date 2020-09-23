package cn.tll.service;

import cn.tll.NotFoundException;
import cn.tll.dao.TagsDao;
import cn.tll.pojo.Tag;
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
 * @create 2020/9/23 15:25
 */
@Service
public class TagsServiceImpl implements TagsService{

    /**
     * 自动注入tagsDao
     */
    @Autowired
    private TagsDao tagsDao;

    /**
     * 新增标签
     * @param tag
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag saveTag(Tag tag) {
        return tagsDao.save(tag);
    }

    /**
     * 删除标签
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(Long id) {
        tagsDao.deleteById(id);
    }

    /**
     * 更新标签
     * @param id
     * @param tag
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = new Tag();
        t.setId(id);
        Example<Tag> example = Example.of(t);
        Optional<Tag> one = tagsDao.findOne(example);
        Tag tt = one.get();
        if (tt == null){
            throw new NotFoundException("标签不存在");
        }
        BeanUtils.copyProperties(tag,tt);
        return tagsDao.save(tt);
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @Override
    public Tag getTag(Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        Example<Tag> example =Example.of(tag);
        Optional<Tag> one = tagsDao.findOne(example);
        return one.get();
    }

    /**
     * 分页查询标签
     * @param pageable
     * @return
     */
    @Override
    public Page<Tag> listPage(Pageable pageable) {
        return tagsDao.findAll(pageable);
    }

    /**
     * 根据name查询标签
     * @param name
     * @return
     */
    @Override
    public Tag findByName(String name) {
        return tagsDao.findByName(name);
    }
}
