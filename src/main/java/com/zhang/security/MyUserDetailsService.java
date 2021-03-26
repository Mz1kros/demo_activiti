package com.zhang.security;

import com.zhang.mapper.UserInfoBeanMapper;
import com.zhang.pojo.UserInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @author by SUNS3T
 * @Classname MyUserDetailsService
 * @Description TODO
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserInfoBeanMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoBean userInfoBean = mapper.selectByUsername(username);
        if (userInfoBean == null) {
            throw new UsernameNotFoundException("数据库中无此用户！");
        }
        return userInfoBean;
    }
}