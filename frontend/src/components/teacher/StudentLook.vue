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
				<el-table-column prop="examtime" label="考试时间" min-width="20%"></el-table-column>
				<el-table-column prop="papername" label="考试名称" min-width="20%"></el-table-column>
				<el-table-column prop="score" label="得分" min-width="20%"></el-table-column>
				<el-table-column prop="total" label="总分" min-width="20%"> </el-table-column>
				<el-table-column prop="ispass" label="及格情况" min-width="20%">
					<template slot-scope="scope">
						<!-- <el-tag :type="scope.row.ispass === '及格' ? 'success' : 'danger'">
							{{ scope.row.ispass }}
							</el-tag> -->
						<el-tag v-if="scope.row.ispass==1" type=success>及格</el-tag>
						<el-tag v-else type=danger>不及格</el-tag>
					</template>
				</el-table-column>
			</el-table>
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
				tableData: [],
			};
		},
		methods: {
			/* getQuestion() {
				this.axios
					// .get(`http://43.142.18.70:9090/QuestionAdd/${this.$route.query.id}`)
					.get(`http://43.142.18.70:9090/paperScore/16`)
					.then(res => {
						this.tableData = res.data.data
						console.log(this.tableData);
					})
			}, */
			getScore() {
				this.axios
					//.get("http://43.142.18.70:9090/paperScore/${id}")
					//使用的id为16的进行测试
					// .get("http://43.142.18.70:9090/paperScore/16")
					.get(`http://43.142.18.70:9090/paperScore/${this.$route.query.id}`)
					.then((res) => {
						//console.log(id);
						console.log(res);
						this.tableData = res.data.data;
						// this.tableData.splice(0, 1)
					});
			},
		},
		mounted() {
			// this.getQuestion()
			this.getScore();
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
