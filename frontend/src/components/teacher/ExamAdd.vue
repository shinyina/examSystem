<template>
  <el-main>
    <el-row>
      <TeacherHeader></TeacherHeader>
    </el-row>
    <el-row>
      <el-col span="3">
        <NavMenu> </NavMenu>
      </el-col>
      <el-col span="18">
        <el-table
          :data="tableData"
          :header-cell-style="{ 'text-align': 'center' }"
          :cell-style="{ 'text-align': 'center' }"
          style="width: 100%"
        >
          <el-table-column prop="name" label="试卷名称" min-width="14.2%">
          </el-table-column>
          <el-table-column prop="detail" label="试卷详情" min-width="14.2%">
          </el-table-column>
          <el-table-column prop="time" label="考试时间" min-width="14.2%">
          </el-table-column>
          <el-table-column prop="score" label="试卷总分" min-width="14.2%">
          </el-table-column>
          <el-table-column prop="status" label="试卷状态" min-width="14.2%">
          </el-table-column>
          <el-table-column prop="bank" label="选用题库" min-width="14.2%">
          </el-table-column>
          <el-table-column label="操作" min-width="14.2%">
            <template slot-scope="scope">
              <el-button
                type="primary"
                class="edit"
                @click="dialogFormEditVisible = true"
                >编辑</el-button
              >
              <el-button
                type="danger"
                class="delete"
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <div id="add">
          <el-button type="warning" id="add" @click="dialogFormVisible = true"
            >添加</el-button
          >
          <el-dialog title="添加考试" :visible.sync="dialogFormVisible">
            <el-form :model="tableDate">
              <el-form-item label="试卷名称:" :label-width="formLabelWidth">
                <el-input v-model="name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="试卷详情:" :label-width="formLabelWidth">
                <el-input v-model="detail" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="考试开始时间:" :label-width="formLabelWidth">
                <el-input v-model="time" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="试卷总分:" :label-width="formLabelWidth">
                <el-input v-model="score" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选用题库" :label-width="formLabelWidth">
                <el-select v-model="form.region" style="width:100%">
                  <el-option label="题库1" value="shanghai"></el-option>
                  <el-option label="题库2" value="beijing"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="add()">确 定</el-button>
            </div>
          </el-dialog>
        </div>
        <div id="edit">
          <el-dialog title="编辑" :visible.sync="dialogFormEditVisible">
            <el-form :model="form">
              <el-form-item label="试卷名称:" :label-width="formLabelWidth">
                <el-input v-model="name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="试卷详情:" :label-width="formLabelWidth">
                <el-input v-model="detail" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="考试开始时间:" :label-width="formLabelWidth">
                <el-input v-model="time" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="试卷总分:" :label-width="formLabelWidth">
                <el-input v-model="score" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选用题库" :label-width="formLabelWidth">
                <el-select v-model="form.region" style="width:100%">
                  <el-option label="题库1" value="shanghai"></el-option>
                  <el-option label="题库2" value="beijing"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormEditVisible = false"
                >取 消</el-button
              >
              <el-button type="primary" @click="add()">确 定</el-button>
            </div>
          </el-dialog>
        </div>
      </el-col>
    </el-row>
  </el-main>
</template>

<script>
import TeacherHeader from "./TeacherHeader.vue";
import NavMenu from "./NavMenu.vue";
import { TimePicker } from "element-ui";
export default {
  components: {
    TeacherHeader,
    NavMenu,
  },
  data() {
    return {
      tableData: [
        {
          name: "计算机导论",
          detail: "导论",
          time: "2022-5-20 12:00-14:00",
          score: 100,
          status: "未开始",
          bank: "计算机导论",
        },
        {
          name: "计算机导论",
          detail: "导论",
          time: "2022-5-20 12:00-14:00",
          score: 100,
          status: "未开始",
          bank: "计算机导论",
        },
        {
          name: "计算机导论",
          detail: "导论",
          time: "2022-5-20 12:00-14:00",
          score: 100,
          status: "未开始",
          bank: "计算机导论",
        },
      ],
      dialogFormVisible: false,
      dialogFormEditVisible: false,

      form: {
        name: "",
        delivery: false,
        type: [],
        detail: "",
      },
      formLabelWidth: "120px",
    };
  },
  methods: {
    add() {
      this.$router.push({
        path: "/EaxmAdd",
      });
    },
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    edit() {
      this.$router.push({
        path: "/ExamAdd",
      });
    },
  },
};
</script>

<style scoped>
.el-main {
  padding: 0;
}

#caozuo {
  width: 20px;
}

#add,
#edit {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 10px;
}
</style>
