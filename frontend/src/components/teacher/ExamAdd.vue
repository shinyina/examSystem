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
				<el-table :data="tableData" :header-cell-style="{ 'text-align': 'center' }"
					:cell-style="{ 'text-align': 'center' }" style="width: 100%;height:85vh;overflow:auto">
					<el-table-column prop="id" label="id" width="60px"></el-table-column>
					<el-table-column prop="name" label="试卷名称" min-width="14.2%">
					</el-table-column>
					<el-table-column prop="description" label="试卷详情" min-width="14.2%">
					</el-table-column>
					<el-table-column prop="quesnum" label="题目数量" min-width="14.2%">
					</el-table-column>
          <el-table-column  label="考试时长(分钟)" prop="examtime" min-width="14.2%">
            
					</el-table-column>
					<el-table-column prop="bankname" label="选用题库" min-width="14.2%">
					</el-table-column>
					<el-table-column label="操作" min-width="14.2%">
						<template slot-scope="scope">
							<el-button type="primary" class="edit" @click="handlerEdit(scope.row)">
								编 辑
								</el-button>
							<!-- <el-button type="danger" class="delete" @click.native.prevent="deleteRow(scope.row.id)"> -->
							<el-button type="danger" class="delete" @click.native.prevent="beforeDelete(scope.row.id)">
								删 除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
				<div id="add">
					<el-button type="warning" id="add" @click="dialogFormVisible = true">添加</el-button>
					<el-dialog title="添加考试" :visible.sync="dialogFormVisible">
						<el-form :model="examAdd">
							<el-form-item label="试卷名称:" :label-width="formLabelWidth">
								<el-input v-model="examAdd.name" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="试卷详情:" :label-width="formLabelWidth">
								<el-input v-model="examAdd.description" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="题目数量" :label-width="formLabelWidth">
								<el-input v-model="examAdd.quesnum" placeholder="千万不能写超我懒得写判断了"></el-input>
							</el-form-item>
              <el-form-item label="考试时长(分钟)" :label-width="formLabelWidth">
								<el-input v-model="examAdd.examtime"></el-input>
							</el-form-item>
							<el-form-item label="选用题库" :label-width="formLabelWidth">
								<el-select v-model="examAdd.bankname" style="width: 100%">
									<el-option v-for="item in allBank" :key="item.id" :label="item.name"
										:value="item.name"></el-option>
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
						<el-form :model="nowEdit">
							<el-form-item label="试卷名称:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.name" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="试卷详情:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.description" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="题目数量" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.quesnum" placeholder="千万不能写超我懒得写判断了"></el-input>
							</el-form-item>
              <el-form-item label="考试时长(分钟)" :label-width="formLabelWidth">
								<el-input v-model="examAdd.examtime"></el-input>
							</el-form-item>
							<el-form-item label="选用题库" :label-width="formLabelWidth">
								<el-select v-model="nowEdit.bankname" style="width: 100%">
									<el-option v-for="item in allBank" :key="item.id" :label="item.name"
										:value="item.name"></el-option>
								</el-select>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormEditVisible = false">取 消</el-button>
							<el-button type="primary" @click="edit()">确 定</el-button>
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
	import {
		TimePicker
	} from "element-ui";
	export default {
		components: {
			TeacherHeader,
			NavMenu,
		},
		data() {
			return {
				allBank: [],
				nowEdit: {},
				examAdd: {},
				tableData: [],
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
			handlerEdit(row) {
				console.log(row);
				this.dialogFormEditVisible = true
				this.nowEdit = row
			},
			add() {
				this.dialogFormVisible = false;
				this.axios({
					url: `http://43.142.18.70:9090/ExamAdd`,
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.examAdd),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success("添加成功");
						this.getExam();
					} else {
						this.$message.error("添加失败");
					}
				});
			},
			beforeDelete(id){
				this.$confirm('此操作将删除该考试信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.deleteRow(id);
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			deleteRow(id) {
				this.axios
					.delete(`http://43.142.18.70:9090/ExamDel/${id}`)
					.then((res) => {
						if (res.data.code == 200) {
							this.$message.success("删除成功");
							this.getExam()
						} else this.$message.error("删除失败");
					});
			},
			edit() {
				this.dialogFormEditVisible = false;
				this.axios({
					url: "http://43.142.18.70:9090/ExamAdd",
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.nowEdit),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success('修改成功')
						this.getExam()
					} else {
						this.$message.error('修改失败')
					}
				});
			},
			getExam() {
				this.axios.get("http://43.142.18.70:9090/ExamPaper").then((res) => {
					console.log(res.data);
					this.tableData = res.data.data;
				});
			},
		},
		mounted() {
			this.getExam();
			this.axios.get("http://43.142.18.70:9090/QuestionBank").then((res) => {
				this.allBank = res.data.data;
				console.log(this.allBank);
			});
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
