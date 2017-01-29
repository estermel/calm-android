package com.ester.calm.response;

import com.ester.calm.model.Order;

/**
 * Created by Ester on 30/11/2016.
 */

public class OrderResponse {

    private String error;

    private Order[] order;

    public String getError() {
        return error;
    }

    public Order[] getOrder() {
        return order;
    }
}
