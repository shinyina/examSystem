<template>
<el-main>
  <el-row class="tac">
    <el-col>
      <el-menu
        :default-active="$route.path"
        class="el-menu-vertical-demo"
        background-color="#11427f"
        text-color="#fff"
        router="true"
      >
        <el-menu-item index="ExamAdd">
          <i class="el-icon-location"></i>
          <span slot="title"> 考试管理</span>
        </el-menu-item>

        <el-menu-item index="QuestionBank">
          <i class="el-icon-menu"></i>
          <span slot="title"> 题库管理</span>
        </el-menu-item>

        <el-menu-item index="StudentAdd">
          <i class="el-icon-setting"></i>
          <span slot="title"> 学生管理</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
  </el-main>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
.el-main{
  padding: 0;
}
.el-menu {
  width: 160px;
  height: 90vh;
}
.el-menu-item{
  font-size: 17px;
  height: 80px;
}
</style>