package com.daiwei.common.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * 多个sort排序
 * @author  david:
 * @date 创建时间：2017年8月31日 下午10:53:20
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
public class SortBuilder {
	public static Sort generateSort(String... fields) {
        List<Order> orders = new ArrayList<Order>();
        for(String f:fields) {
            orders.add(generateOrder(f));
        }
        return new Sort(orders);
    }

    private static Order generateOrder(String f) {
        Order order = null;
        String[] ff = f.split(" ");
        if(ff.length>=2) {
            if(ff[1].equals("desc")) {
                order = new Order(Direction.DESC,ff[0]);
            } else {
                order = new Order(Direction.ASC,ff[0]);
            }
            return order;
        }
        order = new Order(f);
        return order;
    }

}
