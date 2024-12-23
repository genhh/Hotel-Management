package com.zh.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName
public class Transactions {
    @TableId
    private String transactionId;

    private String walletId;
//    @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime transactionDate;

    @DecimalMin("0.0")
    private Float amount;

    private Float CurrentBalance;

    private TransactionType type;
}
