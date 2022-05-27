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
					<el-table-column prop="id" label="ID" min-width="20%"></el-table-column>
					<el-table-column prop="nickname" label="姓名" min-width="20%"></el-table-column>
					<el-table-column prop="username" label="用户名" min-width="20%"></el-table-column>
					<el-table-column prop="password" label="密码" min-width="20%">
						<template slot-scope="scope">
							<el-input v-model="scope.row.password" show-password readonly></el-input>
						</template>
						</el-table-column>
					<el-table-column label="操作" min-width="20%">
						<template slot-scope="scope">
							<el-button type="success" @click="look(scope.row.id)">
								查 看
							</el-button>
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
					<el-dialog title="添加学生" :visible.sync="dialogFormVisible">
						<el-form :model="newStudent">
							<el-form-item label="姓名:" :label-width="formLabelWidth">
								<el-input v-model="newStudent.nickname" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="用户名:" :label-width="formLabelWidth">
								<el-input v-model="newStudent.username" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="密码:" :label-width="formLabelWidth" prop="tableData.pass">
								<el-input placeholder="请输入密码" show-password v-model="newStudent.password"
									autocomplete="off" type="password"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormVisible = false">取 消</el-button>
							<el-button type="primary" @click="studentAdd()">确 定</el-button>
						</div>
					</el-dialog>
				</div>
				<div id="edit">
					<el-dialog title="编辑" :visible.sync="dialogFormEditVisible">
						<el-form :model="nowEdit">
							<el-form-item label="姓名:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.nickname" autocomplete="off"> </el-input>
							</el-form-item>
							<el-form-item label="用户名:" :label-width="formLabelWidth">
								<el-input v-model="nowEdit.username" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="密码:" :label-width="formLabelWidth" prop="password">
								<el-input placeholder="请输入密码" show-password v-model="nowEdit.password" 
									autocomplete="off" type="password"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormEditVisible = false">取 消</el-button>
							<el-button type="primary" @click="studentEdit()">确 定</el-button>
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
				nowEdit: {},
				tableData: [],
				dialogFormVisible: false,
				dialogFormEditVisible: false,
				newStudent: {
					nickname: "",
					username: "",
					password: ""
				},
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
			look(id) {
				this.$router.push({
					path: '/StudentLook',
					query: {
						id
					}
				});
			},
			handlerEdit(row) {
				console.log(row);
				this.dialogFormEditVisible = true
				this.nowEdit = row
			},
			studentEdit() {
				this.dialogFormEditVisible = false;
				console.log(111);
				this.axios({
					url: "http://43.142.18.70:9090/StudentAdd",
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.nowEdit),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success('添加成功')
						setTimeout(() => {}, 500);
					} else {

						this.$message.error('添加失败')
					}
				});
			},
			studentAdd() {
				console.log(this.newStudent);
				this.dialogFormVisible = false;
				this.axios({
					url: "http://43.142.18.70:9090/StudentAdd",
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					data: JSON.stringify(this.newStudent),
				}).then((res) => {
					console.log(res);
					if (res.data.code == 200) {
						this.$message.success('添加成功')
						this.getStudent()
					} else {
						this.$message.error('添加失败')
					}
				});
			},
			beforeDelete(id){
				this.$confirm('此操作将删除该学生信息, 是否继续?', '提示', {
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
					.delete(`http://43.142.18.70:9090/studentDel/${id}`)
					.then((res) => {
						if (res.data.code == 200) {
							this.$message.success("删除成功");
							this.getStudent()
						} else this.$message.error("删除失败");
					});
			},
			getStudent() {
				this.axios.get("http://43.142.18.70:9090/Student").then((res) => {
					this.tableData = res.data.data;
					this.tableData.splice(0, 1)
				});
			},
		},
		mounted() {
			this.getStudent();
		},
	};
</script>

<style scoped>
.el-input__inner{
	border: none !important;
}
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
