<template>
	<el-main>
		<el-row>
			<TeacherHeader></TeacherHeader>
		</el-row>
		<el-row>
			<el-col span="3">
				<NavMenu>
				</NavMenu>
			</el-col>
			<el-col span="18">
				<el-table :data="tableData" :header-cell-style="{'text-align':'center',}"
					:cell-style="{'text-align':'center'}" style="width: 100%">
					<el-table-column prop="name" label="题库名称" min-width="25%">
					</el-table-column>
					<el-table-column prop="detail" label="题库详情" min-width="25%">
					</el-table-column>
					<el-table-column prop="date" label="创建时间" min-width="25%">
					</el-table-column>
					<el-table-column label="操作" min-width="25%">
						<template slot-scope="scope">
							<el-button type="primary" class="edit" @click="dialogFormEditVisible=true">编辑</el-button>
							<el-button type="danger" class="delete"
								@click.native.prevent="deleteRow(scope.$index, tableData)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
				<div id="add">
					<el-button type="warning" id="add" @click="dialogFormVisible = true">添加</el-button>
					<el-dialog title="添加题库" :visible.sync="dialogFormVisible">
						<el-form :model="form">
							<el-form-item label="题库名称:" :label-width="formLabelWidth">
								<el-input v-model="form.name" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="题库详情:" :label-width="formLabelWidth">
								<el-input v-model="form.detail" autocomplete="off"></el-input>
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
							<el-form-item label="题库名称:" :label-width="formLabelWidth">
								<el-input v-model="form.name" autocomplete="off">
								</el-input>
							</el-form-item>
							<el-form-item label="题库详情:" :label-width="formLabelWidth">
								<el-input v-model="form.detail" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormEditVisible = false">取 消</el-button>
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
	export default {
		components: {
			TeacherHeader,
			NavMenu
		},
		data() {
			return {
				tableData: [{
					detail: '计算机导论',
					name: '计算机导论',
					date: '2022-01-04',
				}, {
					detail: '计算机网络',
					name: '计算机网络',
					date: '2022-04-26',

				}, {
					detail: '高等数学',
					name: '高等数学',
					date: '2022-03-14',

				}, {
					detail: '大学物理',
					name: '大学物理',
					date: '2022-02-12',

				}],
				dialogFormVisible: false,
				dialogFormEditVisible: false,

				form: {
					name: '',
					delivery: false,
					type: [],
					detail: '',
				},
				formLabelWidth: '120px'
			}
		},
		methods: {
			run(){
				this.$router.push('/QuestionAdd')
			},
			add() {
				this.$router.push({
					path: '/QuestionAdd'
				})
			},
			deleteRow(index, rows) {
				rows.splice(index, 1);
			},
			edit() {
				this.$router.push({
					path: '/QuestionAdd'
				})
			}
		}
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
