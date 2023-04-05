package com.demo.demo.csv;

import com.demo.demo.conf.OrderMapper;
import com.demo.demo.dto.Order;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class CsvOrderReader extends FlatFileItemReader<Order> {
    public CsvOrderReader(String filename){
        setResource(new FileSystemResource(filename));
        setLineMapper(new DefaultLineMapper<Order>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"id", "customer", "date", "amount"});
            }});
            setFieldSetMapper(new OrderMapper());
        }});
    }
}
