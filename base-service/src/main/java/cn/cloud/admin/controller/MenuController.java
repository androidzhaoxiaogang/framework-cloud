package cn.cloud.admin.controller;

import cn.cloud.admin.domain.Menu;
import cn.cloud.admin.domain.util.Tree;
import cn.cloud.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/menus")
    public List<Tree<Menu>> getInitMenuTree() {
        return menuService.getMenuTree(Long.parseLong("1161404351044"));
    }

}
