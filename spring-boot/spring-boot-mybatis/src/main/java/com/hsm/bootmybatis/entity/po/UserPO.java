package com.hsm.bootmybatis.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/4 15:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {
    private Long id;

    private String name;
    private Integer age;
}
