package cn.cloud.auth.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Administrator on 2017/10/6.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
