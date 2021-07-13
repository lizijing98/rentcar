package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * searchFrom
 *
 * @author zyt
 * @date 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Carousel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String imageUrl;

    private String remake;
}