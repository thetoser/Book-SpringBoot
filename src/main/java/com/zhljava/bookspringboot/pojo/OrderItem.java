package com.zhljava.bookspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    /*CREATE TABLE t_order_item (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(100),
	`count` INT,
	price DECIMAL(11, 2),
	`total_money`  DECIMAL(11, 2),
	`order_id`  VARCHAR(50),
	FOREIGN KEY (`order_id`) REFERENCES t_order(`order_id`)
);*/

    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalMoney;
    private String orderId;

}
