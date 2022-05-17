<template>
  <div>
    <el-row>
      <StudentMain />
    </el-row>
    <el-row>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="time" label="考试时间" width="268">
        </el-table-column>
        <el-table-column prop="name" label="考试名称" width="268">
        </el-table-column>
        <el-table-column prop="getScore" label="得分" width="248">
        </el-table-column>
        <el-table-column prop="totalScore" label="总分" width="248">
        </el-table-column>

        <el-table-column prop="isPass" label="及格情况" width="308">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.isPass === '及格' ? 'success' : 'danger'"
              >{{ scope.row.isPass }}</el-tag
            ></template
          >
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
import StudentMain from "./StudentMain.vue";
export default {
  data() {
    return {
      tableData: [
        {
          name: "teste",
          time: "2022-5-17",
          getScore: 88,
          totalScore: 100,
          isPass: "及格",
        },
        {
          name: "测试测试",
          time: "2022-5-17",
          getScore: 48,
          totalScore: 100,
          isPass: "不及格",
        },
      ],
    };
  },
  components: { StudentMain },
};
</script>

<style scoped>
.el-table {
  position: relative;
  width: 70% !important;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 40px;
}
</style>