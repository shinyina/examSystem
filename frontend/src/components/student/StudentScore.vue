<template>
	<div>
		<el-row>
			<StudentMain />
		</el-row>
		<div id="appp">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span style=" font:20pt Courier; font-weight: 900;">
						{{StudentScore.examName}}
					</span>
					<el-button id="back" type="primary">
						返回
					</el-button>
				</div>
				<p id="score">{{StudentScore.score}}分</p>
				<div id="tishiyu">
					<p v-if="StudentScore.score>=StudentScore.score*0.6">
						恭喜你通过本次考试！
					</p>
					<p v-else>
						加油，你能行的！
					</p>
				</div>
				<div class="table_box" :data="StudentScore">
					<table class="tables">
						<tr>
							<th>姓名</th>
							<th>用时</th>
						</tr>
				  <tr>
							<td>{{StudentScore.name}}</td>
							<td>{{StudentScore.elapsedTime}}</td>
						</tr>
					</table>
				</div>
			</el-card>

		</div>
	</div>
</template>

<script>
	import StudentMain from "./StudentMain.vue";
	export default {
		data() {
			return {
				StudentScore: {
					name: " 陈益章",
					examName: "计算机导论",
					score: 80,
					elapsedTime: "66分钟",
					examTime: 90,
					totalScore: 100,
				},
			};
		},
		components: {
			StudentMain,
		},
	};
</script>

<style scoped>
	* {
		padding: 0;
		margin: 0;
	}

	#back {
		float: right;
		padding: 8px 10px
	}

	.text {
		font-size: 14px;
	}

	.item {
		margin-bottom: 18px;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}

	.box-card {
		/* display: flex; */
		width: 480px;
		/* display: flex; */
	}

	.box-card {
		min-height: 100%;
		height: 100%;
	}

	#appp {
		position: relative;
		width: 100%;
		height: 400px;
		display: flex;
		justify-content: center;
		align-items: center;
		padding-top: 200px;
	}

	#score {
		font-size: 60px;
		text-align: center;
		font-style: italic;
		color: red;
		padding: 5px;
	}

	#tishiyu {
		font-size: 20px;
		text-align: center;
		font-weight: 900;
		padding: 10px;
	}

	.table_box {
		width: 60%;
		height: 500px;
		margin: 0 auto;
	}

	table {
		border: 1px solid gray;
		border-radius: 10px;
		border-spacing: 1;
		box-shadow: 2px 2px 2px #ccc;
		border-spacing: 0;
		width: 100%;
	}

	table tr {
		text-align: center;
	}

	table td,
	table th {
		border-right: 1px solid gray;
		/*设置表格单元格外边框，只设置右下边框*/
		border-bottom: 1px solid gray;
		padding: 10px;
		/*设置单元格内边距，单元格内容显得不拥挤*/
		text-align: center;
	}

	tr:last-child td:first-child {
		border-radius: 0 0 0 10px;
	}

	tr:last-child td:last-child {
		border-radius: 0 0 10px 0;
	}

	tr:last-child td {
		border-bottom: none;
	}

	tr td:last-child,
	tr th:last-child {
		border-right: none;
	}
</style>
