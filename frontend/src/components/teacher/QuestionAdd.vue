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
          <el-table-column prop="name" label="题目编号" min-width="10%">
          </el-table-column>
          <el-table-column prop="question" label="题目内容" min-width="25%">
          </el-table-column>
          <el-table-column label="题目选项">
			<el-table-column prop="optionA" label="A" min-width="20%"></el-table-column>
		    <el-table-column prop="optionB" label="B" min-width="20%"></el-table-column>
		    <el-table-column prop="optionC" label="C" min-width="20%"></el-table-column>
		  	<el-table-column prop="optionD" label="D" min-width="20%"></el-table-column>
          </el-table-column>
          <el-table-column prop="answer" label="题目答案" min-width="10%">
          </el-table-column>
          <el-table-column label="操作" min-width="25%">
            <template slot-scope="scope">
              <el-button type="primary" class="edit" @click="dialogFormEditVisible=true; showEidtView(scope.row)">编辑</el-button>
              <el-button type="danger" class="delete" @click.native.prevent="deleteRow(scope.$index, tableData)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
		
        <div id="add">
          <el-button type="warning" id="add" @click="dialogFormVisible = true">添加</el-button>
          <el-dialog title="添加题目" :visible.sync="dialogFormVisible">
            <el-form :model="form">
              <el-form-item label="题目内容:" :label-width="formLabelWidth">
                <el-input v-model="form.question" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项A:" :label-width="formLabelWidth">
                <el-input v-model="form.optionA" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项B:" :label-width="formLabelWidth">
                <el-input v-model="form.optionB" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项C:" :label-width="formLabelWidth">
                <el-input v-model="form.optionC" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项D:" :label-width="formLabelWidth">
                <el-input v-model="form.optionD" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="题目答案:" :label-width="formLabelWidth">
                <el-input v-model="form.answer" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogFormVisible=false ;add()">确 定</el-button>
            </div>
          </el-dialog>
        </div>
        <div id="edit">
          <el-dialog title="编辑题目" :visible.sync="dialogFormEditVisible">
            <el-form :model="form">
              <el-form-item label="题目内容:" :label-width="formLabelWidth">
                <el-input v-model="form.question" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项A:" :label-width="formLabelWidth">
                <el-input v-model="form.optionA" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项B:" :label-width="formLabelWidth">
                <el-input v-model="form.optionB" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项C:" :label-width="formLabelWidth">
                <el-input v-model="form.optionC" autocomplete="off"></el-input>
              </el-form-item>
			  <el-form-item label="选项D:" :label-width="formLabelWidth">
                <el-input v-model="form.optionD" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="题目答案:" :label-width="formLabelWidth">
                <el-input v-model="form.answer" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormEditVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogFormEditVisible = false; edit()">确 定</el-button>
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
export default {
  components: {
    TeacherHeader,
    NavMenu,
  },

  data() {
    return {
      tableData: [
        {
          name: "00-001",
          question: "1935年1月15日至17日,中共中央政治局在遵义召开扩大会议,会议着力解决了",
          optionA: "党的政治路线问题",
		  optionB: "红军的前进方向问题",
		  optionC: "当时具有决定意义的军事和组织问题",
		  optionD: "土地革命的政策问题",
          answer: "C",
        },
        {
          name: "00-002",
          question: "20世纪30年代前期、中期,中国共产党内屡次出现严重的左倾教条主义错误,主要原因是",
		  optionA: "党内一直存在着的浓厚的左倾情绪",
		  optionB: "共产国际队的错误干预",
		  optionC: "不理解中国历史和革命特点",
		  optionD: "不善于把马克思列宁主义与中国实际结合起来",
          answer: "D",
        },
        {
          name: "00-003",
          question: "中共在新民主主义革命历史阶段制定土地改革政策的根本出发点是根据",
		  optionA: "不同时期的斗争策略",
		  optionB: "国内主要矛盾的变化",
		  optionC: "不同地区农民的状况",
		  optionD: "反对国民党斗争的需要",
          answer: "B",
        },
        {
          name: "00-004",
          question: "土地革命战争时期中国共产党最伟大的历史贡献是",
		  optionA: "发动、领导了著名的秋收起义",
		  optionB: "开辟了农村包围城市、武装夺取政权的道路",
		  optionC: "领导中国人民推翻了三座大山",
		  optionD: "推动了抗日民族统一战线的建立",
          answer: "B",
        },
      ],
      dialogFormVisible: false,
      dialogFormEditVisible: false,

      form: {
        name: "",
        question: "",
		optionA: "",
		optionB: "",
		optionC: "",
		optionD: "",
		answer: "",
        delivery: false,
        type: [],
      },
      formLabelWidth: "120px",
    };
  },
  methods: {

    add() {

    },

    deleteRow(index, rows) {
      rows.splice(index, 1);
    },

	showEidtView(data) {
		Object.assign(this.form,data);
	},

    edit() {
        
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
