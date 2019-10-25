package com.tom.spider.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * ClassName: Relic
 * Description:
 *
 * @date 2019/10/22 22:57
 * @author: Mi_dad
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Component
public class Relic implements Serializable {
    private Integer id;
    private String name;
    private String image;
    private String dynasty;
    private String size;
    private String year;
    private String desc;
}
