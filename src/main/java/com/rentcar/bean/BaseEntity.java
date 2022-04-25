package com.rentcar.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lzj
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
  @TableLogic private Integer deleted;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime modifyTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Integer modifyId;

  @TableField(fill = FieldFill.INSERT)
  private Integer creatorId;

  @Version private Integer fversion;
}
