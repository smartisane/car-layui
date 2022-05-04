package com.yimin.carlayui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.entity.News;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 管理员的资讯管理页
     * @param model
     * @return
     */
    @GetMapping("/newsMPage")
    public String newsMPage(Model model,HttpSession session){

        User user = (User) session.getAttribute("user");
        //只有管理员才能访问
        if(user==null || !"admin".equals(user.getRole())){
            return "error/404";
        }

        List<News> newsList = newsService.list();
        model.addAttribute("newsList",newsList);
        //高亮菜单
        model.addAttribute("isNewsMPage",true);
        return "news_manage";
    }


    /**
     * 管理员
     * 发布资讯
     * @param news
     * @return
     */
    @PostMapping(value = "/submitNews",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result submitNews(@RequestBody News news, HttpSession session){
        User user = (User) session.getAttribute("user");
        //接口测试
        if(user==null || !"admin".equals(user.getRole())){
            return Result.error("当前用户未登录或没有权限");
        }
        log.debug("news="+news);//ok

        //补充创建时间信息
        news.setCreateTime(new Date());
        //插入信息（发布资讯）
        try {
            newsService.save(news);
            return Result.success("发布成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("发布失败");
        }

    }


    /**
     * 编辑资讯，获取资讯信息
     */
    @PostMapping(value = "/getEditNews",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getEditNews(@RequestBody String newsId, HttpSession session){
        User user = (User) session.getAttribute("user");
        //接口测试
        if(user==null || !"admin".equals(user.getRole())){
            return Result.error("当前用户未登录或没有权限");
        }

        try {
            News news = newsService.getById(newsId);
            return Result.success("已查询到内容",news);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("发生错误");
        }

    }

    /**
     * 管理员
     * 修改资讯提交
     * @param news
     * @return
     */
    @PostMapping(value = "/updateNews",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result updateNews(@RequestBody News news, HttpSession session){
        User user = (User) session.getAttribute("user");
        //接口测试
        if(user==null || !"admin".equals(user.getRole())){
            return Result.error("当前用户未登录或没有权限");
        }

        //修改时间
        news.setCreateTime(new Date());
        //修改资讯
        try {
            //这里表单传递了id，为更新操作
            newsService.saveOrUpdate(news);
            return Result.success("已提交修改");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改失败");
        }

    }


    /**
     * 删除资讯
     */
    @PostMapping(value = "/deleteNews",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result deleteNews(@RequestBody String newsId, HttpSession session){
        User user = (User) session.getAttribute("user");
        //接口测试
        if(user==null || !"admin".equals(user.getRole())){
            return Result.error("当前用户未登录或没有权限");
        }

        //删除资讯
        try {
            newsService.removeById(newsId);
            return Result.success("已删除");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }

    }


    @GetMapping("/newsPage")
    public String newsPage(Model model){
        //降序排列
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<News> newsList = newsService.list(wrapper);
        model.addAttribute("newsList",newsList);
        //导航栏资讯高亮
        model.addAttribute("isNewsPage",true);
        return "news";
    }


}
