package cn.cloud.account.service;


import cn.cloud.account.domain.util.BuildTree;
import cn.cloud.account.domain.util.Tree;
import cn.cloud.account.domain.Menu;
import cn.cloud.account.dao.MenuMapper;
import cn.cloud.account.dao.RoleMenuMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Tree<Menu> getSysMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<>();
		List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<Menu> list() {
		List<Menu> menus = menuMapper.getList(new HashMap<>(16));
		return menus;
	}

	@Override
	public int remove(Long id) {
		int result = menuMapper.remove(id);
		return result;
	}

	@Override
	public int save(Menu menu) {
		int r = menuMapper.save(menu);
		return r;
	}

	@Override
	public int update(Menu menu) {
		int r = menuMapper.update(menu);
		return r;
	}

	@Override
	public Menu get(Long id) {
		Menu menuDO = menuMapper.get(id);
		return menuDO;
	}

	@Override
	public Tree<Menu> getTree() {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.getList(new HashMap<>(16));
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<Menu> getTree(Long id) {
		// 根据roleId查询权限
		List<Menu> menus = menuMapper.getList(new HashMap<>(16));
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
		List<Long> temp = menuIds;
		for (Menu menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.getList(new HashMap<>(16));
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Set<String> getPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<Tree<Menu>> getMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
		return list;
	}

}
