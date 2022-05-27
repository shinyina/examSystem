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
					:cell-style="{ 'text-align': 'center' }" style="width: 100%">
					<el-table-column prop="id" label="题目编号" min-width="10%">
					</el-table-column>
					<el-table-column prop="content" label="题目内容" min-width="25%">
					</el-table-column>
					<el-table-column label="题目选项">
						<el-table-column prop="choice1" label="A" min-width="20%"></el-table-column>
						<el-table-column prop="choice2" label="B" min-width="20%"></el-table-column>
						<el-table-column prop="choice3" label="C" min-width="20%"></el-table-column>
						<el-table-column prop="choice4" label="D" min-width="20%"></el-table-column>
					</el-table-column>
					<el-table-column prop="answer" label="题目答案" min-width="10%">
					</el-table-column>
					<el-table-column label="操作" min-width="25%">
						<template slot-scope="scope">
							<el-button type="primary" class="edit" @click="
								dialogFormEditVisible = true;
								showEidtView(scope.row);">
								编辑
							</el-button>
							<!-- <el-button type="danger" class="delete" @click.native.prevent="deleteRow(scope.row.id)"> -->
							<el-button type="danger" class="delete" @click.native.prevent="beforeDelete(scope.row.id)">
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>

				<div id="add">
					<el-button type="warning" id="add" @click="dialogFormVisible = true">添加</el-button>
					<el-dialog title="添加题目" :visible.sync="dialogFormVisible">
						<el-form>
							<el-form-item label="题目内容:" :label-width="formLabelWidth">
								<el-input v-model="questionAdd.content" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项A:" :label-width="formLabelWidth">
								<el-input v-model="questionAdd.choice1" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项B:" :label-width="formLabelWidth">
								<el-input v-model="questionAdd.choice2" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项C:" :label-width="formLabelWidth">
								<el-input v-model="questionAdd.choice3" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项D:" :label-width="formLabelWidth">
								<el-input v-model="questionAdd.choice4" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="题目答案:" :label-width="formLabelWidth">
								<el-input v-model="questionAdd.answer" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormVisible = false">取 消</el-button>
							<el-button type="primary" @click="
                  dialogFormVisible = false;
                  add();
                ">确 定</el-button>
						</div>
					</el-dialog>
				</div>
				<div id="edit">
					<el-dialog title="编辑题目" :visible.sync="dialogFormEditVisible">
						<el-form>
							<el-form-item label="题目内容:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.content" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项A:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.choice1" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项B:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.choice2" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项C:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.choice3" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="选项D:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.choice4" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="题目答案:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.answer" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormEditVisible = false">取 消</el-button>
							<el-button type="primary" @click="
                  dialogFormEditVisible = false;
                  edit();
                ">确 定</el-button>
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
				questionAdd: {},
				nowEdit: {},
				tableData: [],
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
				this.dialogFormVisible = false;
				this.axios({
					url: `http://43.142.18.70:9090/QuestionAdd/${this.$route.query.id}`,
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.questionAdd),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success("添加成功");
						this.getQuestion();
					} else {
						this.$message.error("添加失败");
					}
				});
			},
			beforeDelete(id){
							this.$confirm('此操作将删除该题目信息, 是否继续?', '提示', {
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
					.delete(
						`http://43.142.18.70:9090/QuestionDel/${this.$route.query.id}/${id}`
					)
					.then((res) => {
						if (res.data.code == 200) {
							this.$message.success("删除成功");
							this.getQuestion();
						} else this.$message.error("删除失败");
					});
			},

			showEidtView(data) {
				this.nowEdit = data;
			},

			edit() {
				this.axios({
					url: `http://43.142.18.70:9090/QuestionAdd/${this.$route.query.id}`,
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.nowEdit),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success("修改成功");
						this.getQuestion();
					} else {
						this.$message.error("修改失败");
					}
				});
			},
			getQuestion() {
				this.axios
					.get(`http://43.142.18.70:9090/QuestionAdd/${this.$route.query.id}`)
					.then((res) => {
						this.tableData = res.data.data;
						console.log(this.tableData);
					});
			},
		},
		mounted() {
			this.getQuestion();
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
