package com.zh.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@TableName
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {//一个用户一个钱包

    @TableId
    private String walletId;
    private String userId;

    @DecimalMin("0.0")
    @NotNull(message="Balance cannot be null")
    private Float balance;

    @JsonIgnore
    private List<Transactions> transactions = new ArrayList<>();

}
