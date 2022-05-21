<template>
  <el-row>
      <el-col :span="4" class="logo"><i class="el-icon-s-order"></i><span class="title">在线考试系统</span></el-col>
      <el-col :span="11">
    <el-menu
      :default-active="$route.path"
      class="el-menu-demo"
      mode="horizontal"
      router="true"
    >
      <el-menu-item index="/ExamPaper" >我的考试</el-menu-item>
      <el-menu-item index="/AllScore">我的成绩</el-menu-item>
    </el-menu>
    </el-col>
    <el-col :span="2" class='user' offset="4"><i class="el-icon-user-solid">{{userName}}</i></el-col>
  </el-row>
</template>

<script>
export default {
    data() {
        return {
            activeIndex:'',
            userName:'ShinYina'
        }
    },
    methods:{
        
    }
};
</script>

<style scoped>
.logo{
    height:61px;
    line-height: 61px;
    font-size: 30px;
    margin-right: 150px;
}
.el-menu-item{
    margin: 0px 70px;
}
.el-menu-demo{
    display: flex;
    justify-content: space-between;
}
.user{
    height: 61px;
    font-size: 30px;
    line-height:61px;
}
</style>