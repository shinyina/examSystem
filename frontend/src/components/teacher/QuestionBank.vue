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
					<el-table-column prop="id" label="id" min-width="10%">
					</el-table-column>
					<el-table-column prop="name" label="题库名称" min-width="25%">
					</el-table-column>

					<el-table-column prop="description" label="题库详情" min-width="25%">
					</el-table-column>
					<el-table-column prop="total" label="题目数量" min-width="25%">
					</el-table-column>
					<el-table-column prop="buildtime" label="创建时间" min-width="25%">
					</el-table-column>
					<el-table-column label="操作" min-width="25%">
						<template slot-scope="scope">
							<el-button type="primary" class="edit" @click="handlerEdit(scope.row)">
								编 辑
							</el-button>
							<el-button type="success" @click="run(scope.row.id)">
								查 看
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
					<el-dialog title="添加题库" :visible.sync="dialogFormVisible">
						<el-form>
							<el-form-item label="题库名称:" :label-width="formLabelWidth">
								<el-input v-model="bankAdd.name" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="题库详情:" :label-width="formLabelWidth">
								<el-input v-model="bankAdd.description" autocomplete="off"></el-input>
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
						<el-form>
							<el-form-item label="题库名称:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.name" autocomplete="off"> </el-input>
							</el-form-item>
							<el-form-item label="题库详情:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.description" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormEditVisible = false">取 消</el-button>
							<el-button type="primary" @click="bankEdit()">确 定</el-button>
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
				bankAdd: {
					name: '',
					description: ""
				},
				nowEdit: {},
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
				this.dialogFormEditVisible = true
				this.nowEdit = row
			},
			bankEdit() {
				this.dialogFormEditVisible = false;
				this.axios({
					url: "http://43.142.18.70:9090/QuestionBankAdd",
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.nowEdit),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success('修改成功')
						this.getBank()
					} else {
						this.$message.error('修改失败')
					}
				});
			},
			run(id) {
				this.$router.push({
					path: '/QuestionAdd',
					query: {
						id
					}
				});
			},
			add() {
				console.log(this.bankAdd);
				this.dialogFormVisible = false;
				this.axios({
					url: "http://43.142.18.70:9090/QuestionBankAdd",
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.bankAdd),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {

						this.$message.success('添加成功')
						this.getBank()
					} else {
						this.$message.error('添加失败')
					}
				});
			},
			beforeDelete(id) {
				this.$confirm('此操作将删除该题库, 是否继续?', '提示', {
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


				console.log(id);
				this.axios
					.delete(`http://43.142.18.70:9090/QuestionBankDel/${id}`)
					.then((res) => {
						if (res.data.code == 200) {
							this.$message.success("删除成功");
							this.getBank()
						} else this.$message.error("删除失败");
					});
			},
			edit() {
				this.$router.push({
					path: "/QuestionAdd",
				});
			},
			getBank() {
				this.axios.get("http://43.142.18.70:9090/QuestionBank").then((res) => {
					console.log(res.data);
					this.tableData = res.data.data;
				});
			},
		},
		mounted() {
			this.getBank()
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
