package com.yimin.carlayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimin.carlayui.entity.News;
import com.yimin.carlayui.mapper.NewsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("newsService")
@Transactional
public class NewsServiceImpl extends ServiceImpl<NewsMapper,News> implements NewsService {

}
