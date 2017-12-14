package cn.cloud.account.dao;


import cn.cloud.account.domain.RoleMenu;
import cn.cloud.account.mapper.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface RoleMenuMapper extends MyMapper<RoleMenu> {
    RoleMenu get(Long id);

    List<RoleMenu> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(RoleMenu roleMenu);

    int update(RoleMenu roleMenu);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listMenuIdByRoleId(Long roleId);

    int removeByRoleId(Long roleId);

    int batchSave(List<RoleMenu> list);
}
