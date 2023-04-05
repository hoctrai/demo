package com.demo.demo.conf;

import com.demo.demo.dto.Order;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class OrderMapper implements FieldSetMapper<Order> {

    @Override
    public Order mapFieldSet(FieldSet fieldSet) throws BindException {
        Order order = new Order();
        order.setId(fieldSet.readLong("id"));
        order.setCustomer(fieldSet.readString("customer"));
        order.setDate(fieldSet.readDate("date", "yyyy-MM-dd"));
        order.setAmount(fieldSet.readBigDecimal("amount"));
        return order;
    }
}
