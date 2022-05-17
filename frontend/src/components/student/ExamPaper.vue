<template>
  <div>
    <el-row><StudentMain /></el-row>
    <el-row>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="考试名称" width="867px">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.date }}</span>
          </template>
        </el-table-column>
      </el-table></el-row
    >
  </div>
</template>

<script>
import StudentMain from "./StudentMain.vue";
export default {
  components: {
    StudentMain,
  },
};
</script>

<style scoped>
.el-table{
    width: 60% !important;
    left: 50%;
    transform: translateX(-50%);
    margin-top: 40px;
}
</style>