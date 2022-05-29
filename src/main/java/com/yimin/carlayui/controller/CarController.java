package com.yimin.carlayui.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimin.carlayui.common.CarStatus;
import com.yimin.carlayui.common.Result;
import com.yimin.carlayui.common.SearchVo;
import com.yimin.carlayui.entity.Car;
import com.yimin.carlayui.entity.Mark;
import com.yimin.carlayui.entity.User;
import com.yimin.carlayui.service.CarService;
import com.yimin.carlayui.service.MarkService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private MarkService markService;

    Logger logger = LoggerFactory.getLogger(CarController.class);

    /**
     * 跳转到车辆管理页面
     * 分页：默认第一页，每页展示6条记录
     */
    @GetMapping("/carManage")
    public String carManage(@RequestParam(value = "cur", required = false) Long cur, HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        IPage<Car> carPage;
        Page<Car> page;
        //如果没有传参，默认为第一页
        if (cur == null) {
            page = new Page<>(1, 6);//分页
        } else {
            page = new Page<>(cur, 6);
        }

        if ("admin".equals(user.getRole())) {
            carPage = carService.page(page);
        } else {
            QueryWrapper<Car> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", user.getId());
            carPage = carService.page(page, wrapper);
        }
        model.addAttribute("carPage", carPage);
        logger.debug("carPage.getSize()" + carPage.getSize());
        logger.debug("carPage.getPages()=" + carPage.getPages());
        logger.debug("carPage.getTotal()" + carPage.getTotal());
        logger.debug("carPage.getCurrent()" + carPage.getCurrent());
        logger.debug("carPage.getRecords()" + carPage.getRecords());
        //用于将选项默认选中
        model.addAttribute("isManagePage", true);
        return "car_manage";
    }


    @PostMapping(value = "/publishCar", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result publishCar(@RequestBody Car car, HttpSession session) {
        Long curId = car.getId();
        logger.debug("curId==>" + car.getId());
        car.setCreateTime(new Date());
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        car.setUserId(id);
        car.setStatus(1);

        try {
            // key即为图片集合
            List<String> imgs = (List<String>) session.getAttribute(car.getKey());

            logger.debug("imgs=>" + imgs);

            if (imgs != null && imgs.size() > 0) {

                car.setThumbnailUrl(imgs.get(0));
                car.setSlideUrl(JSON.toJSONString(imgs));

            }
            logger.info("publishCar=>" + car.toString());
            //这里使用saveOrUpdate，当有id时为更新操作
            carService.saveOrUpdate(car);

            if (curId == null) {
                return Result.success("发布成功");
            } else {
                return Result.success("修改成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (car.getId() == null) {
                return Result.error("发布失败");
            } else {
                return Result.error("修改失败");
            }
        }


    }

    /**
     * 修改时先通过id查询，保存到session中，再填充到表单中
     *
     * @param listId
     * @param session
     * @return
     */
    @PostMapping(value = "/getModifyCar", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getModifyCar(@RequestBody String listId, HttpSession session) {
        logger.debug("listId==>" + listId);
        Car car = carService.getById(listId);
        if (car != null && car.getStatus() != 0) {
            logger.debug("当前要修改的car==>" + car.toString());
            // 将当前的车辆信息返回给前端，使用jquery进行表单赋值
            return Result.success("已查询到当前车辆信息", car);
        }

        return Result.error("未查询到车辆或已出租，暂时无法修改");

    }

    /**
     * 下架车辆
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/downCar", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result downCar(@RequestBody String id) {
        Car car = carService.getById(id);
        if (car != null && car.getStatus() == 1) {
            //设置状态为下架
            car.setStatus(-1);
            carService.updateById(car);
            return Result.success("下架成功");
        }
        return Result.error("该车已出租或已下架,无法执行下架操作");
    }

    /**
     * 上架车辆
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/upCar", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result upCar(@RequestBody String id) {
        Car car = carService.getById(id);
        if (car != null && car.getStatus() == -1) {
            //设置状态为上架
            car.setStatus(1);
            try {
                carService.updateById(car);
                return Result.success("上架成功");
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("发生错误，上架失败");
            }

        }
        return Result.error("该车不是已下架状态,不能重复上架操作");
    }

    /**
     * 删除车辆
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteCar", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result deleteCar(@RequestBody String id) {
        Car car = carService.getById(id);
        //只有未出租和下架状态才能删除，已出租时不能删除
        if (car != null && (car.getStatus() == 1 || car.getStatus() == -1)) {
            carService.removeById(id);//逻辑删除
            return Result.success("删除成功");
        }
        return Result.error("当前状态不允许删除");
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id, Model model, HttpSession session) {
        Car car = carService.getById(id);
        if (car != null) {
            List<String> slideList = JSON.parseArray(car.getSlideUrl(), String.class);
            logger.debug("slideList==>" + slideList);
            car.setSlideList(slideList);
            model.addAttribute("car", car);

            //查询当前用户是否收藏，用于初始化收藏按钮显示的文字
            //2022年4月17日22点47分
            User user = (User) session.getAttribute("user");
            if (user == null) {
                model.addAttribute("isMarked", false);
            }else{
                boolean result = markService.isMarked(user.getId(), car.getId());
                if (result) {
                    model.addAttribute("isMarked", true);
                } else {
                    model.addAttribute("isMarked", false);
                }
            }

            //当前在详情页，点亮导航栏【详情】
            model.addAttribute("isDetailPage",true);
            return "detail";
        } else {
            return "/error/404";
        }
    }


    @PostMapping(value = "/submitMark", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result submitMark(@RequestBody String carId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        logger.debug("submitMark方法" + user);
        if (user != null) {
            Result result = markService.submitMark(user.getId(), Long.parseLong(carId));
            return result;
        } else {
            logger.debug("未登录用户点击了收藏");
            return Result.error("请先登录，之后可收藏");
        }

    }


    @GetMapping("/submitSearch")
    public String submitSearch(@RequestParam(value = "address", required = false) String address,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "rent", required = false) String rent,
                               @RequestParam(value = "cur", required = false, defaultValue = "1") Long cur,
                               Model model) {

        logger.debug("submitSearch...");
        logger.debug("address=" + address);
        logger.debug("type=" + type);
        logger.debug("rent=" + rent);
        logger.debug("cur=" + cur);

        IPage<Car> carPage;

        //如果没有传参，默认为第一页
        Page<Car> page = new Page<>(cur, 6);//分页

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(address)) {
            wrapper.like("address", address);
        }
        if (StringUtils.isNotEmpty(type)) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotEmpty(rent)) {
            switch (rent) {
                case "0":
                    wrapper.gt("rent", 0);
                    wrapper.lt("rent", 99);
                    break;
                case "1":
                    wrapper.gt("rent", 100);
                    wrapper.lt("rent", 199);
                    break;
                case "2":
                    wrapper.gt("rent", 200);
                    wrapper.lt("rent", 299);
                    break;
                case "3":
                    wrapper.gt("rent", 300);
                    wrapper.lt("rent", 399);
                    break;
                case "4":
                    wrapper.gt("rent", 400);
                    wrapper.lt("rent", 9999);
                    break;
                default:
                    break;
            }
        }
        wrapper.eq("status", CarStatus.NOT_RENT);
        wrapper.orderByDesc("create_time");
        carPage = carService.page(page, wrapper);
        model.addAttribute("carPage", carPage);

        //将搜条件携带
        model.addAttribute("address", address);
        model.addAttribute("type", type);
        model.addAttribute("rent", rent);

        //展示导航栏搜索
        model.addAttribute("isSearchPage",true);
        return "car_list";
    }


    /**
     * 管理员 车辆管理页面的搜索
     * 搜索后返回携带数据carManage页
     * @param address
     * @param type
     * @param rent
     * @param cur
     * @param model
     * @return
     */
    @GetMapping("/submitSearch2")
    public String submitSearch2(@RequestParam(value = "address", required = false) String address,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "rent", required = false) String rent,
                               @RequestParam(value = "cur", required = false, defaultValue = "1") Long cur,
                               Model model) {

        logger.debug("submitSearch...");
        logger.debug("address=" + address);
        logger.debug("type=" + type);
        logger.debug("rent=" + rent);
        logger.debug("cur=" + cur);



        //如果没有传参，默认为第一页
        Page<Car> page = new Page<>(cur, 6);//分页

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(address)) {
            wrapper.like("address", address);
        }
        if (StringUtils.isNotEmpty(type)) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotEmpty(rent)) {
            switch (rent) {
                case "0":
                    wrapper.gt("rent", 0);
                    wrapper.lt("rent", 99);
                    break;
                case "1":
                    wrapper.gt("rent", 100);
                    wrapper.lt("rent", 199);
                    break;
                case "2":
                    wrapper.gt("rent", 200);
                    wrapper.lt("rent", 299);
                    break;
                case "3":
                    wrapper.gt("rent", 300);
                    wrapper.lt("rent", 399);
                    break;
                case "4":
                    wrapper.gt("rent", 400);
                    wrapper.lt("rent", 9999);
                    break;
                default:
                    break;
            }
        }
        IPage<Car> carPage = carService.page(page, wrapper);
        model.addAttribute("carPage", carPage);

        //将搜条件携带
        model.addAttribute("address", address);
        model.addAttribute("type", type);
        model.addAttribute("rent", rent);

        //用于将选项默认选中
        model.addAttribute("isManagePage", true);
        return "car_manage";
    }

}
