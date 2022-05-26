package com.yzs.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzs.demo1.entity.Bank;
import com.yzs.demo1.mapper.BankMapper;
import com.yzs.demo1.service.IBankService;
import org.springframework.stereotype.Service;

@Service
public class BankImpl extends ServiceImpl<BankMapper, Bank> implements IBankService {
}
