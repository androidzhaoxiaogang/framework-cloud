package cn.cloud.account.service;

import cn.cloud.account.domain.util.Tree;
import cn.cloud.account.domain.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {
	Tree<Menu> getSysMenuTree(Long id);

	List<Tree<Menu>> getMenuTree(Long id);

	Tree<Menu> getTree();

	Tree<Menu> getTree(Long id);

	List<Menu> list();

	int remove(Long id);

	int save(Menu menu);

	int update(Menu menu);

	Menu get(Long id);

	Set<String> getPerms(Long userId);
}
