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
					<el-table-column prop="uid" label="ID" min-width="25%">
					</el-table-column>
					<el-table-column prop="name" label="姓名" min-width="25%">
					</el-table-column>
					<el-table-column prop="userName" label="用户名" min-width="25%">
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
					<el-dialog title="添加学生" :visible.sync="dialogFormVisible">
						<el-form :model="tableData">
							<el-form-item label="姓名:" :label-width="formLabelWidth">
								<el-input v-model="tableData.name" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="用户名:" :label-width="formLabelWidth">
								<el-input v-model="tableData.detail" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="密码:" :label-width="formLabelWidth" prop="tableData.pass">
								<el-input placeholder="请输入密码" show-password v-model="tableData.pass" autocomplete="off"
									type="password"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormVisible = false">取 消</el-button>
							<el-button type="primary"  @click="dialogFormVisible = false">确 定</el-button>
						</div>
					</el-dialog>
				</div>
				<div id="edit">
					<el-dialog title="编辑" :visible.sync="dialogFormEditVisible">
						<el-form :model="tableData">
							<el-form-item label="姓名:" :label-width="formLabelWidth">
								<el-input v-model="tableData.name" autocomplete="off">
								</el-input>
							</el-form-item>
							<el-form-item label="用户名:" :label-width="formLabelWidth">
								<el-input v-model="tableData.detail" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="密码:" :label-width="formLabelWidth" prop="tableData.pass">
								<el-input placeholder="请输入密码" show-password v-model="tableData.pass" autocomplete="off"
									type="password"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormEditVisible = false">取 消</el-button>
							<el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
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
					uid: '202112163',
					name: '陈益章',
					userName: '琪琪',
					pass: ''
				}, {
					uid: '202112165',
					name: '章益陈',
					userName: '拉拉',
					pass: ''

				}, {
					uid: '202112166',
					name: '益章陈',
					userName: '蹦蹦',
					pass: ''

				}, {
					uid: '202112168',
					name: '陈章益',
					userName: '咔咔',
					pass: ''

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
			deleteRow(index, rows) {
				rows.splice(index, 1);
			},
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
