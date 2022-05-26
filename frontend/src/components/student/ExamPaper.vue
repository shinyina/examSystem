<template>
  <div>
    <el-row><StudentMain /></el-row>
    <el-row>
      <el-col v-for="item in tableData" :key="item.id">
        <el-card>
          <div class="msg">
            <ul>
              <li style="width:150px"><b>考试名称</b></li>
              <li>{{ item.name }}</li>
            </ul>
            <ul style="width:400px">
              <li><b>考试描述</b></li>
              <li style="color:gray">{{ item.description }}</li>
            </ul>
            <ul>
              <li style="width:100px"><b>考试时间</b></li>
              <li>{{ item.examTime }}分钟</li>
            </ul>
            <ul>
              <li style="width:70px"><b>开考时间</b></li>
              <li>{{ item.time }}</li>
            </ul>
            <ul>
              <li style="width:70px"><b>试卷总分</b></li>
              <li>{{ item.totalScore }}分</li>
            </ul>
            <el-button type="primary" @click="startExam(item.id)">开始考试</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import StudentMain from "./StudentMain.vue";
export default {
  data() {
    return {
      tableData: [
        {
          id: 1,
          name: "考试考试",
          description: "teteteteteteetst",
          time: "2022-5-15",
          examTime: 90,
          totalScore: 100,
          type:1,
        },
         {
          id: 2,
          name: "考试考试sdadas",
          description: "teteteteteteets哇哇哇",
          examTime: 90,
          totalScore: 100,
          type:2,
        },
      ],
    };
  },
  methods:{
    startExam(id){
      console.log(1);
      this.$router.push({path:'/PaperDetail',query:{id:id}})
    },
    getPaper(){
      this.axios.get('http://43.142.18.70:9090/ExamPaper').then(res=>{
        this.tableData=res.data.data
      })
    }
  },
  mounted(){
    this.getPaper()
  },
  components: {
    StudentMain,
  },
};
</script>

<style scoped>
.el-col {
  position: relative;
  width: 70% !important;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 40px;
}
.el-col li {
  margin: 20px 20px;
}
.msg{
    display: flex;
    justify-content: space-around;
    align-items: center;
}
.msg .el-button{
    height: 40px;
}
.el-col ul {
  margin: 0 20px;
  display: inline-block;
}
</style>