package com.rentcar.bean.search;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rentcar.bean.Notice;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Author: zyt
 */
@Data
public class NoticeSearchFrom {
    private Integer pageSize;
    private Integer pageNum;
    private String title;

    public Page<Notice> getPage() {
        return new Page<>(pageNum, pageSize);
    }

    public QueryWrapper<Notice> queryWrapper() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper();
        queryWrapper.eq("notice.deleted", "0");
        queryWrapper.like(!StringUtils.isEmpty(title), "title", title);
        return queryWrapper;
    }
}
