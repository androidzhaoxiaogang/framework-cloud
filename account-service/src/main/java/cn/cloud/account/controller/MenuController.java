package cn.cloud.account.controller;

import cn.cloud.account.domain.Menu;
import cn.cloud.account.domain.util.Tree;
import cn.cloud.account.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/")
    public List<Tree<Menu>> getInitMenuTree() {
        return menuService.getMenuTree(Long.parseLong("1161404351044"));
    }

}
