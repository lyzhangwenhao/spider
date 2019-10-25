package com.tom.spider.mapper;

import com.tom.spider.pojo.Relic;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: RelicMapper
 * Description:
 *
 * @date 2019/10/22 23:00
 * @author: Mi_dad
 */
public interface RelicMapper {
    /**
     * 通过id查找文物
     * @param id
     * @return
     */
    Relic getRelicById(Integer id);

    /**
     * 插入Relic
     * @param relic
     * @return
     */
    int addRelic(@Param("relic") Relic relic);
}
