package cn.cloud.account.dao;


import cn.cloud.account.domain.Menu;
import cn.cloud.account.mapper.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface MenuMapper extends MyMapper<Menu> {
    Menu get(Long menuId);

    List<Menu> getList(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(Menu menu);

    int update(Menu menu);

    int remove(Long menuId);

    int batchRemove(Long[] menuIds);

    List<Menu> listMenuByUserId(Long id);

    List<String> listUserPerms(Long id);
}
