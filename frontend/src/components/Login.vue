<template>
  <div class="main" ref="main">
    <img src="../assets/images/background.jpg" ref="img"> 
    <el-card>
      <div class="top">
          <i class="el-icon-s-order"></i><span class="title">在线考试系统</span>
        </div>
        <div class="bottom">
          <div class="container">
            <p class="title">账号登录</p>
            <el-form  label-width="80px" :model="formLabelAlign">
              <el-form-item label="用户名" >
                <el-input v-model.number="formLabelAlign.username" placeholder="请输入用户名" @focus="inputFocus()" @blur="inputBlur()"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="formLabelAlign.password" placeholder="请输入密码" type='password' @focus="inputFocus()" @blur="inputBlur()"></el-input>
              </el-form-item>
              <div class="submit">
                <el-button type="primary" class="row-login" @click="login()">登录</el-button>
              </div>
              <!-- <div class="options">
                <p class="find"><a href="javascript:;">找回密码</a></p>
                <div class="register">
                  <span>没有账号?</span>
                  <span><a href="javascript:;">去注册</a></span>
                </div>
              </div> -->
            </el-form>
          </div>
        </div>
        </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      formLabelAlign:[],
    }
  },
  methods: {
    inputFocus(){
      this.$refs.img.style.filter='Blur(5px)';
    },
    inputBlur(){
      this.$refs.img.style.filter='Blur(0px)';
    },
    login(){
      this.$router.push({path:'/StudentMain'})
    }
  },
  mounted() {
    this.$refs.main.style.height = document.documentElement.clientHeight + "px";
  },
};
</script>

<style scoped>
.main img{
  position: absolute;
  width: 100%;
  transition: .3s;
  height: 100%;
  filter:blur(0px) ;
}
.el-card{
  padding: 0;
  margin: 0;
  width: 650px;
  height: 370px;
  position: relative;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-70%);
  overflow: visible;

}
.bottom{
  width: 600px;
  height: 200px;
}
.bottom .submit .row-login {
  width: 100%;
  background-color: #04468b;
  border-color: #04468b;
  margin: 20px 0px 10px 0px;
  padding: 15px 20px;
}
.bottom .submit {
  display: flex;
  justify-content: center;
}
.container .title{
  display: block;
  width: 140px;
  margin: 30px auto;
  font-size: 24px;
}
.top{
  font-size: 28px;
  transform: translate(180px,0px);
}
</style>