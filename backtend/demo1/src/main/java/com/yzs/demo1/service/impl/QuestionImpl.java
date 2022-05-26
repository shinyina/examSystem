package com.yzs.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzs.demo1.entity.Question;
import com.yzs.demo1.mapper.QuestionMapper;
import com.yzs.demo1.service.IQuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
}
