package com.yzs.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzs.demo1.entity.Paperscore;
import com.yzs.demo1.mapper.PaperscoreMapper;
import com.yzs.demo1.service.IPaperscoreService;
import org.springframework.stereotype.Service;

@Service
public class PaperscoreImpl extends ServiceImpl<PaperscoreMapper, Paperscore> implements IPaperscoreService {
}
