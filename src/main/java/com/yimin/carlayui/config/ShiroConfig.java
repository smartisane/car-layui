package com.yimin.carlayui.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yimin.carlayui.shiro.MyFormAuthenticationFilter;
import com.yimin.carlayui.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro拦截器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/", "anon");
        map.put("/login", "anon");
        map.put("/register", "anon");
        map.put("/layui/**", "anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/img/**", "anon");
        map.put("/upload/**","anon");//展示汽车图片
        map.put("/detail**","anon");//跳转到汽车详情页
        map.put("/submitMark**","anon");//访问收藏接口
        map.put("/submitSearch**","anon");//搜索
        map.put("/submitOrder**","anon");//先允许预定，再返回请登录信息
        map.put("/newsPage**","anon");//导航栏资讯
        map.put("/feedbackPage**","anon");//导航栏反馈
        map.put("/submitFeedback**","anon");//提交反馈，未登录时返回信息
        map.put("/plugins/**", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        shiroFilterFactoryBean.setLoginUrl("/");

        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 自定义Realm
     */
    @Bean
    public Realm realm() {
        return new MyRealm();
    }


    /**
     * 整合thymeleaf
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


    /**
     * 实现登陆成功后将user存放到session
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter(){
        MyFormAuthenticationFilter myFormAuthenticationFilter = new MyFormAuthenticationFilter();
        myFormAuthenticationFilter.setUsernameParam("username");
        myFormAuthenticationFilter.setPasswordParam("password");
        return myFormAuthenticationFilter;
    }
}
