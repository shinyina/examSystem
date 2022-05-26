package com.yzs.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzs.demo1.entity.Paper;
import com.yzs.demo1.mapper.PaperMapper;
import com.yzs.demo1.service.IPaperService;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl  extends ServiceImpl<PaperMapper, Paper> implements IPaperService {
}
