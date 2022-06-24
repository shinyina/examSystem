<template>
  <div>
    <!-- <el-button @click="log()"></el-button> -->
    <el-row>
      <el-col :span="4" class="logo"
        ><i class="el-icon-s-order"> </i>
        <span class="title"> 在线考试系统 </span>
      </el-col>
      <el-col :span="11">
        <span
          style="
            width: 180px;
            font-size: 30px;
            line-height: 61px;
            margin: 0 auto;
            display: block;
          "
        >
          试题作答部分
        </span>
      </el-col>
      <el-col :span="2" class="user" offset="4"
        ><i class="el-icon-user-solid">
          {{ userName }}
        </i>
      </el-col>
    </el-row>
    <!-- 计时器功能开发中 -->
    <el-row>
      <div id="jishiqi">
        <el-card>
          <!-- 倒计时 -->
          <div class="count-time">
            <span>考试剩余时间：</span>
            <span><i class="el-icon-time"></i> {{ `${h}: ${m}: ${s}` }}</span>
            <!-- <el-button type="primary" @click="countTime">开始答题</el-button> -->
          </div>
        </el-card>
        <!-- <p style="font-size: 20px;">
					<i class="el-icon-time"></i>
					{{ `${hr}: ${min}: ${sec}` }}
				</p> -->
        <!-- <el-button v-show="isshow1" @click="begin()" round type="primary">
					开始答题
				</el-button>
				<el-button v-show="!isshow1" @click="open()" round type="danger">
					交卷
				</el-button> -->
      </div>
    </el-row>
    <!-- 计时器功能开发中 -->

    <el-row>
      <el-card class="paperMain">
        <el-row>
          <el-col span="8">
            <el-card class="left">
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
              <!-- <el-button type="primary" style="margin: 50px auto; display: block" @click="submit()"> -->
              <el-button
                type="primary"
                style="margin: 50px auto; display: block"
                @click="beforeSubmit()"
              >
                结束考试
              </el-button>
            </el-card>
          </el-col>
          <el-col span="16">
            <el-card class="right">
              <div class="questionDetail">
                <h3>第{{ nowIndex }}题.{{ nowContent }}</h3>
                <li>
                  <el-button
                    round
                    :type="selectButton[0]"
                    @click="chooseAns('A')"
                    >A</el-button
                  >
                  <span>{{ nowA }}</span>
                </li>
                <li>
                  <el-button
                    round
                    :type="selectButton[1]"
                    @click="chooseAns('B')"
                    >B</el-button
                  >
                  <span>{{ nowB }}</span>
                </li>
                <li>
                  <el-button
                    round
                    :type="selectButton[2]"
                    @click="chooseAns('C')"
                    >C</el-button
                  >
                  <span>{{ nowC }}</span>
                </li>
                <li>
                  <el-button
                    round
                    :type="selectButton[3]"
                    @click="chooseAns('D')"
                    >D</el-button
                  >
                  <span>{{ nowD }}</span>
                </li>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 计时器功能使用的
      timer: null,
      count: "",
      h: "00",
      m: "00",
      s: "00",
      // 计时器功能使用的
      temp: [],
      nowIndex: "",
      nowId: "",
      nowContent: "",
      nowA: "",
      nowB: "",
      nowC: "",
      nowD: "",
      selectButton: ["", "", "", ""],
      selectAbout: [
        // {
        //   id: 1,
        //   choice: "",
        // },
      ],
      paper: [
        // {
        //   id: 1,
        //   content: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊",
        //   choice: { A: "哈哈", B: "啊啊", C: "66", D: "233" },
        // },
      ],
    };
  },
  methods: {
    // 计时器功能使用的
    countTime() {
      // 定义考试时间的秒数，此处根据需要进行定义
      // const time = 7200;
      const time = 60*this.$route.query.examtime;
      if (!this.timer) {
        this.count = time;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= time) {
            this.count--;
            const h = parseInt(this.count / (60 * 60));
            const m = parseInt((this.count / 60) % 60);
            const s = parseInt(this.count % 60);
            if (this.count===0) {
              this.$message({
                message: "答题时间结束，将自动交卷！",
                type: "warning",
              });
              this.submit();
              clearInterval(this.timer);
            } else {
              this.h = h > 9 ? h : "0" + h;
              this.m = m > 9 ? m : "0" + m;
              this.s = s > 9 ? s : "0" + s;
            }
          } else {
            clearInterval(this.timer);
            this.timer = null;
          }
        }, 1000);
      }
    },
    // 计时器功能使用的
    bottonType(index) {
      if (this.selectAbout[index].answer !== "") return "primary";
    },
    log() {
      // console.log(this.selectAbout);
      // console.log(this.selectButton);
      console.log(this.paper[0]);
    },
    selectQuestion(index) {
      console.log(this.paper[0]);
      this.nowIndex = index + 1;
      this.nowId = this.paper[index].id;
      this.nowContent = this.paper[index].content;
      this.nowA = this.paper[index].choice.A;
      this.nowB = this.paper[index].choice.B;
      this.nowC = this.paper[index].choice.C;
      this.nowD = this.paper[index].choice.D;
      for (var i = 0; i < 4; i++) this.selectButton.splice(i, 1, "");
      if (this.selectAbout[index].answer === "A")
        this.selectButton.splice(0, 1, "primary");
      if (this.selectAbout[index].answer === "B")
        this.selectButton.splice(1, 1, "primary");
      if (this.selectAbout[index].answer === "C")
        this.selectButton.splice(2, 1, "primary");
      if (this.selectAbout[index].answer === "D")
        this.selectButton.splice(3, 1, "primary");
    },
    chooseAns(choice) {
      for (var i = 0; i < this.selectAbout.length; i++) {
        if (this.nowId === this.selectAbout[i].id) {
          this.selectAbout[i].answer = choice;
        }
      }
      for (var i = 0; i < 4; i++) this.selectButton.splice(i, 1, "");
      if (choice == "A") this.selectButton.splice(0, 1, "primary");
      if (choice == "B") this.selectButton.splice(1, 1, "primary");
      if (choice == "C") this.selectButton.splice(2, 1, "primary");
      if (choice == "D") this.selectButton.splice(3, 1, "primary");
    },
    beforeSubmit() {
      this.$confirm("您将结束考试, 是否确定?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.submit();
          this.$message({
            type: "success",
            message: "考试结束!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "继续考试",
          });
        });
    },
    submit() {
      let that=this
      let submit = {
        allanswer: this.selectAbout,
        papername: this.$route.query.name,
        id_user: sessionStorage.getItem("uid"),
      };
      console.log(JSON.stringify(submit));
      this.axios({
        url: "http://43.142.18.70:9090/StudentScore",
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(submit),
      }).then((res) => {
        console.log();
        this.$router.push({
          path: "/StudentScore",
          query: {
            score: res.data.data.score,
            total: res.data.data.total,
            exanName: this.$route.query.name,
            time:that.$route.query.examtime*60-that.count
          },
        });
      });
    },
    mountedSet() {
      this.axios
        .get(`http://43.142.18.70:9090/PaperDetail/${this.$route.query.id}`)
        .then((res) => {
          console.log(res.data);
          for (let i = 0; i < res.data.data.length; i++) {
            this.paper.push({
              id: res.data.data[i].id,
              content: res.data.data[i].content,
              choice: {
                A: res.data.data[i].choice1,
                B: res.data.data[i].choice2,
                C: res.data.data[i].choice3,
                D: res.data.data[i].choice4,
              },
            });
          }
        });
    },
    setDefault() {
      setTimeout(() => {
        this.nowIndex = 1;
        this.nowId = this.paper[0].id;
        this.nowContent = this.paper[0].content;
        this.nowA = this.paper[0].choice.A;
        this.nowB = this.paper[0].choice.B;
        this.nowC = this.paper[0].choice.C;
        this.nowD = this.paper[0].choice.D;
        for (let i = 0; i < this.paper.length; i++) {
          console.log(this.paper[i]);
          this.selectAbout.splice(i, 1, {
            id: this.paper[i].id,
            answer: "",
          });
        }
      }, 100);
    },
  },
  mounted() {
    this.mountedSet();
    this.setDefault();
    this.countTime();
  },
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

#jishiqi {
  padding: 0;
  margin: 0;
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 30px;
}
</style>