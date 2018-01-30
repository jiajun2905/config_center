package com.jiajun.config.controller;

import com.jiajun.config.model.Result;
import com.jiajun.config.service.ConfigService;
import com.jiajun.config.vo.MetaConfigVo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SUMMERS on 2018/1/27.
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

    @Resource
    private ConfigService configService;

    @RequestMapping("/add")
    public Result add(MetaConfigVo metaConfigVo){
        return null;
    }

    @RequestMapping("/update")
    public Result update(MetaConfigVo metaConfigVo){
        return null;
    }

    @RequestMapping("/delete")
    public Result delete(MetaConfigVo metaConfigVo){
        return null;
    }

    @RequestMapping("getData")
    public Result<String> getData(String path){
        return null;
    }

    @RequestMapping("getChilds")
    public Result<List<String>> getChilds(String path){
        return null;
    }

}
