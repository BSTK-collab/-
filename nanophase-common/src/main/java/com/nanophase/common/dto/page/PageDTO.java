package com.nanophase.common.dto.page;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.util.List;

/**
 * @author zhj
 * @apiNote 接收分页参数
 * @since 2021-03-17
 */
@Data
public class PageDTO {

    /**
     * 当前页
     */
    private int current;
    /**
     * 每页展示条数
     */
    private int size;

    /**
     * 排序字段与排序方式
     */
    private List<OrderItem> orderItems;

    public int getSize() {
        if (size == 0) {
            return size = 10;
        }
        return size;
    }

    public int getCurrent() {
        if (current == 0) {
            return current = 1;
        }
        return current;
    }
}
