<template>
	<div>
		<el-row>
			<StudentMain />
		</el-row>
		<el-row>
			<el-table :data="tableData" style="width: 100%">
				<el-table-column prop="examtime" label="考试时间" width="268">
				</el-table-column>
				<el-table-column prop="papername" label="考试名称" width="268">
				</el-table-column>
				<el-table-column prop="score" label="得分" width="248">
				</el-table-column>
				<el-table-column prop="total" label="总分" width="248">
				</el-table-column>
				<el-table-column prop="ispass" label="及格情况" width="308">
					<template slot-scope="scope">
						<!-- <el-tag :type="scope.row.ispass === '及格' ? 'success' : 'danger'">
						{{ scope.row.ispass }}
						
						</el-tag> -->
						<el-tag v-if="scope.row.ispass==1" type=success>及格</el-tag>
						<el-tag v-else type=danger>不及格</el-tag>
					</template>
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
				tableData: [],
				/* 				tableData: [{
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
								], */
			};
		},
		components: {
			StudentMain
		},
		methods: {
			getScore() {
				this.axios
					//.get("http://43.142.18.70:9090/paperScore/${id}")
					//使用的id为16的进行测试
					.get(`http://43.142.18.70:9090/paperScore/${sessionStorage.getItem('uid')}`)
					.then((res) => {
						//console.log(id);
						console.log(res);
						this.tableData = res.data.data;
						// this.tableData.splice(0, 1)
					});
			},
		},
		mounted() {
			this.getScore();
		},
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
