package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查组控制层管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    /**
     * 注入检查组服务接口
     */
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 新增检查组方法
     * @return 返回结果 xxx
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup) {
        try {
            checkGroupService.add(checkitemIds,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.pageQuery(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }

    /**
     * 根据检查组id查询检查组信息
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 根据检查组id 查询中间表 检查项ids
     */
    @RequestMapping(value = "/findCheckItemIdsByCheckGroupId", method = RequestMethod.GET)
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            Integer[] ids = checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * 编辑检查组
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result edit(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup) {
        try {
            checkGroupService.edit(checkitemIds,checkGroup);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    /**
     * 查询所有检查组数据
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        try {
            List<CheckGroup> checkGroupList = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

}
