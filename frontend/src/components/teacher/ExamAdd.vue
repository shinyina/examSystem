<template>
  <el-main>
    <el-row><TeacherHeader></TeacherHeader></el-row>
    <el-row>
      <el-col span="3">
        <NavMenu></NavMenu>
      </el-col>
      考试管理
      <el-col span="18">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="date" label="日期" width="180">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="180">
          </el-table-column>
          <el-table-column prop="address" label="地址"> </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </el-main>
</template>

<script>
import TeacherHeader from "./TeacherHeader.vue";
import NavMenu from "./NavMenu.vue";
export default {
  components: { TeacherHeader, NavMenu },
  data() {
        return {
          tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
        }
      }
};
</script>

<style scoped>
.el-main {
  padding: 0;
}
</style>