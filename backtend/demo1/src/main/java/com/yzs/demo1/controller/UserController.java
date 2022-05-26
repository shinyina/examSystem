package com.yzs.demo1.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzs.demo1.common.Constants;
import com.yzs.demo1.common.Result;
import com.yzs.demo1.controller.dto.UserDTO;
import com.yzs.demo1.entity.*;
import com.yzs.demo1.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzs
 * @since 2022-05-24
 */
@RestController
@RequestMapping("")
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private IPaperService paperService;
    @Resource
    private IPaperscoreService paperscoreService;
    @Resource
    private IQuestionService questionService;
    @Resource
    private IBankService bankService;

    @PostMapping("/Login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        UserDTO userDTO = new UserDTO();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(user);
        return Result.success(dto);
    }

    @GetMapping("/paperScore/{id_user}")
    public Result findScoreByUser(@PathVariable Integer id_user) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("id_user",id_user);
        return Result.success(paperscoreService.listByMap(columnMap));
    }

    @GetMapping("/ExamPaper")
    public Result findAllPaper() {
        return Result.success(paperService.list());
    }

    @GetMapping("/QuestionAdd/{id_bank}")
    public Result findQuesByBank(@PathVariable Integer id_bank){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("id_bank",id_bank);
        return Result.success(questionService.listByMap(columnMap));
    }

    @GetMapping("PaperDetail/{id}")
    public Result findPaperById(@PathVariable Integer id){
        Paper paper = paperService.getById(id);

        QueryWrapper<Bank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",paper.getBankname());
        Bank one = bankService.getOne(queryWrapper);

        Integer total = one.getTotal();
        Integer need = paper.getQuesnum();
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("id_bank",one.getId());
        List<Question> ques = questionService.listByMap(columnMap);

        List intRandom = new ArrayList();
        List mylist = new ArrayList();//生成数据集，用来保存随即生成数，并用于判断
        Random rd = new Random();
        while(mylist.size() < need){
            int num = rd.nextInt(total);if( !mylist.contains(num)) {
                mylist.add(num); //往集合里面添加数据。
            }
        }

        for(int i = 0;i <mylist.size();i++) {
            intRandom.add((Integer)mylist.get(i));
        }

        for (int i = 0; i < need; i++) {
            intRandom.set(i,ques.get((Integer)intRandom.get(i)).getId());
        }
        return Result.success(intRandom);
    }

    @PostMapping("/StudentScore")
    public Result studentscore(@RequestBody zhenghe two){
        Paperscore paperscore = new Paperscore();
        paperscore.setTotal(two.getAllanswer().size() * 5);
        int length = two.getAllanswer().size();
        int sum = 0;
        for (int i = 0; i < length; i++) {
            Question ques = questionService.getById(two.getAllanswer().get(i).getId());
            if(two.getAllanswer().get(i).getAnswer().equals(ques.getAnswer()) ) {
                sum += 5;
            }
        }
        paperscore.setScore(sum);
        paperscore.setExamtime(new Date());
        if (sum*5 >= length *3*5) paperscore.setIspass(1);
        else paperscore.setIspass(0);
        paperscore.setId_user(two.getId_user());
        paperscore.setPapername(two.getPapername());
        paperscoreService.saveOrUpdate(paperscore);
        return Result.success(paperscore);
    }

    @PostMapping("/ExamAdd")
    public Result updateOrAddExam(@RequestBody Paper paper){
        return Result.success(paperService.saveOrUpdate(paper));
    }

    @DeleteMapping("/ExamDel/{id}")
    public Result deleteExam(@PathVariable Integer id){
        return Result.success(paperService.removeById(id));
    }

    @GetMapping("/QuestionBank")
    public Result getAllBank(){
        return Result.success(bankService.list());
    }

    @PostMapping("/QuestionBankAdd")
    public Result upOrAddBank(@RequestBody Bank bank){
        return Result.success(bankService.saveOrUpdate(bank));
    }

    @DeleteMapping("/QuestionBankDel/{id}")
    public Result delBank(@PathVariable Integer id){
        return Result.success(bankService.removeById(id));
    }

    @GetMapping("/Question/{id}")
    public Result getQuesById(@PathVariable Integer id){
        return Result.success(questionService.getById(id));
    }


    @PostMapping("/QuestionAdd/{id_bank}")
    public Result upOrAddQues(@RequestBody Question question , @PathVariable Integer id_bank){
        question.setId_bank(id_bank);
        Bank bank = bankService.getById(id_bank);
        bank.setTotal(bank.getTotal() + 1);
        bankService.updateById(bank);
        return Result.success(questionService.saveOrUpdate(question));
    }

    @DeleteMapping("/QuestionDel/{id_bank}/{id}")
    public Result delQues(@PathVariable Integer id , @PathVariable Integer id_bank){
        Bank bank = bankService.getById(id_bank);
        bank.setTotal(bank.getTotal() - 1);
        bankService.updateById(bank);
        return Result.success(questionService.removeById(id));
    }
    @DeleteMapping("/studentDel/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }


    @GetMapping("/Student")
    public Result findAllstu() {
        return Result.success(userService.list());
    }

    @PostMapping("/StudentAdd")
    public Result upOrAddStu(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
    }

//    @GetMapping("/{id}")
//    public Result findOne(@PathVariable Integer id) {
//        return Result.success(userService.getById(id));
//    }
//
//    @GetMapping("/username/{username}")
//    public Result findOne(@PathVariable String username) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        return Result.success(userService.getOne(queryWrapper));
//    }

//    @GetMapping("/page")
//    public Result findPage(@RequestParam Integer pageNum,
//    @RequestParam Integer pageSize) {
//        return Result.success(userService.page(new Page<>(pageNum, pageSize)));
//    }

}

