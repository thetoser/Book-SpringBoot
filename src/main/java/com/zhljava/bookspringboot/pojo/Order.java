package com.zhljava.bookspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /*CREATE TABLE t_order (
	`order_id`  VARCHAR(50) PRIMARY KEY,
	`create_time`  DATETIME,
	`total_money`  DECIMAL(11, 2),
	`status` INT NOT NULL DEFAULT 0,
	`user_id`  INT,
	FOREIGN KEY (`user_id`) REFERENCES t_user(`id`)
);*/

    private String orderId;
    private LocalDateTime createTime;
    private BigDecimal totalMoney;
    private Integer status;
    private Integer userId;

}
