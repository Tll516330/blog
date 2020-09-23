package cn.tll.service;

import cn.tll.pojo.User;

/**
 * @author tll
 * @create 2020/9/16 16:11
 */
public interface UserService {
    /**
     * 检验用户
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);
}
