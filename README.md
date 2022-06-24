# 考试系统
仓库地址:https://github.com/shinyina/examSystem

### 参考资料

已部署文件:http://124.220.24.49:8080

**前端基于vue2,element-ui**

**后端基于springboot**

### 命名规范

* exam

* examPaper
* ExamPaper

### 项目结构

|
|---Login
|	|
|	|---Student
|	|		|
|	|		|---ExamPaper
|	|		|		|
|	|		|		|---PaperDetail
|	|		|		|		|
|	|		|		|		|---StudentScore
|	|		|---AllScore

|    |---Teacher
|	|
|	|---ExamAdd
|	|
|    |---SubjectAll

|	|			|

|	|			|---SubjectAdd

### 		API

* Login:登录接口
* ExamPaper:获取所有考试
* PaperDetail:根据id获取当前考试试题
* Student:获取该同学本考试成绩
* AllScore获取该同学所有考试成绩
* ExamAdd获取所有考试信息，添加考试信息
* SubjectAll获取所有题库中所有题目
* SubjectAdd向题库中添加题目

