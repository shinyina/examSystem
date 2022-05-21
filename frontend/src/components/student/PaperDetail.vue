<template>
  <div>
    <el-row>
      <el-col :span="4" class="logo"
        ><i class="el-icon-s-order"></i
        ><span class="title">在线考试系统</span></el-col
      >
      <el-col :span="11">
        <span
          style="
            width: 180px;
            font-size: 30px;
            line-height: 61px;
            margin: 0 auto;
            display: block;
          "
          >试题作答部分</span
        >
      </el-col>
      <el-col :span="2" class="user" offset="4"
        ><i class="el-icon-user-solid">{{ userName }}</i></el-col
      >
    </el-row>
    <el-row>
      <el-card class="paperMain">
        <el-row>
          <el-col span="8"
            ><el-card class="left">
              <b>题目作答情况</b>
              <div class="aboutAns">
                <el-button
                  round
                  v-for="(item, index) in paper"
                  :key="index"
                  style="margin: 5px"
                  @click="selectQuestion(index)"
                  :type="bottonType(index)"
                  >{{ index + 1 }}</el-button
                >
              </div>
              <el-button type="primary" style="margin:50px auto;display: block;" @click="submit()">结束考试</el-button>
            </el-card></el-col
          >
          <el-col span="16"
            ><el-card class="right">
              <div class="questionDetail">
                <h3>第{{nowIndex}}题.{{ nowContent }}</h3>
                <li>
                  <el-button
                    round
                    :type="selectButton[0]"
                    @click="chooseAns('A')"
                    >A</el-button
                  ><span>{{ nowA }}</span>
                </li>
                <li>
                  <el-button
                    round
                    :type="selectButton[1]"
                    @click="chooseAns('B')"
                    >B</el-button
                  ><span>{{ nowB }}</span>
                </li>
                <li>
                  <el-button
                    round
                    :type="selectButton[2]"
                    @click="chooseAns('C')"
                    >C</el-button
                  ><span>{{ nowC }}</span>
                </li>
                <li>
                  <el-button
                    round
                    :type="selectButton[3]"
                    @click="chooseAns('D')"
                    >D</el-button
                  ><span>{{ nowD }}</span>
                </li>
              </div>
            </el-card></el-col
          >
        </el-row>
      </el-card>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
        nowIndex:'',
      nowId: "",
      nowContent: "",
      nowA: "",
      nowB: "",
      nowC: "",
      nowD: "",
      selectButton: ["", "", "", ""],
      selectAbout: [
        {
          id: 1,
          choice: "",
        },
        {
          id: 2,
          choice: "",
        },
        {
          id: 3,
          choice: "",
        },
        {
          id: 4,
          choice: "",
        },
        {
          id: 5,
          choice: "",
        },
        {
          id: 6,
          choice: "",
        },
        {
          id: 7,
          choice: "",
        },
        {
          id: 8,
          choice: "",
        },
        {
          id: 9,
          choice: "",
        },
      ],
      paper: [
        {
          id: 1,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 2,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 3,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 4,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 5,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 6,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 7,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 8,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
        {
          id: 9,
          content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
          choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        },
      ],
    };
  },
  methods: {
    bottonType(index) {
      if(this.selectAbout[index].choice!=='')
      return 'primary'
    },
    log() {
      console.log(this.selectAbout);
      console.log(this.selectButton);
    },
    selectQuestion(index) {
        this.nowIndex=index+1;
      this.nowId = this.paper[index].id;
      this.nowContent = this.paper[index].content;
      this.nowA = this.paper[index].choice.A;
      this.nowB = this.paper[index].choice.B;
      this.nowC = this.paper[index].choice.C;
      this.nowD = this.paper[index].choice.D;
      for (var i=0;i<4;i++)
      this.selectButton.splice(i,1,'')
      if (this.selectAbout[index].choice === "A")
        this.selectButton.splice(0, 1, "primary");
      if (this.selectAbout[index].choice === "B")
        this.selectButton.splice(1, 1, "primary");
      if (this.selectAbout[index].choice === "C")
        this.selectButton.splice(2, 1, "primary");
      if (this.selectAbout[index].choice === "D")
        this.selectButton.splice(3, 1, "primary");
    },
    chooseAns(choice) {
      for (var i = 0; i < this.selectAbout.length; i++) {
        if (this.nowId === this.selectAbout[i].id) {
          this.selectAbout[i].choice = choice;
        }
      }
      for (var i=0;i<4;i++)
      this.selectButton.splice(i,1,'')
      if(choice=='A')
      this.selectButton.splice(0,1,'primary')
      if(choice=='B')
      this.selectButton.splice(1,1,'primary')
      if(choice=='C')
      this.selectButton.splice(2,1,'primary')
      if(choice=='D')
      this.selectButton.splice(3,1,'primary')
    },
    submit(){
      this.$router.push('/StudentScore')
    }
  },
  mounted(){
    this.nowIndex=1;
      this.nowId = this.paper[0].id;
      this.nowContent = this.paper[0].content;
      this.nowA = this.paper[0].choice.A;
      this.nowB = this.paper[0].choice.B;
      this.nowC = this.paper[0].choice.C;
      this.nowD = this.paper[0].choice.D;
  }
};
</script>

<style scoped>
.el-row {
  border-bottom: 1px soild gray !important;
}
.logo {
  height: 61px;
  line-height: 61px;
  font-size: 30px;
  margin-right: 150px;
}
.el-menu-item {
  margin: 0px 70px;
}
.el-menu-demo {
  display: flex;
  justify-content: space-between;
}
.user {
  height: 61px;
  font-size: 30px;
  line-height: 61px;
}
.paperMain {
  margin-top: 5vh;
  width: 70%;
  height: 80vh;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
}
.left {
  width: 90%;
  height: 400px;
}
.right {
  width: 100%;
  height: 700px;
}
.aboutAns {
  margin-top: 15px;
}
.questionDetail {
  width: 600px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 50px;
}
.questionDetail li {
  margin: 20px 0;
}
</style>