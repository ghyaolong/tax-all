package com.chinasoft.tax.controller;

import com.chinasoft.tax.vo.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/menu")
public class MenuController {

    //@Autowired
    //private MenuService menuService;
    /**
     * 获取该用户所拥有的菜单
     * @return
     */
    @GetMapping("/getMenuList/{userId}")
    public Message getAllMenu(@PathVariable String userId){

        return null;
    }

    /**
     * 添加菜单
     * @return
     */
    public Message addMenu(){
        return null;
    }

    /**
     * 删除菜单
     */

    /**
     * 分配页面元素给菜单
     */

}


