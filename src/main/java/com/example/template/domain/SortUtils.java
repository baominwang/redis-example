package com.example.template.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class SortUtils {
    private static final Order DEFAULT_ORDER = new Order(Direction.DESC, "id");

    private static final Sort DEFAULT_SORT = Sort.by(DEFAULT_ORDER);

    public static Order getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    public static Sort getDefaultSort() {
        return DEFAULT_SORT;
    }
}
