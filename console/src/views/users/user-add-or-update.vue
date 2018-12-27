<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户账号" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户账号"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="电子邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="电子邮箱"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="头像" prop="avatar">
      <el-input v-model="dataForm.avatar" placeholder="头像"></el-input>
    </el-form-item>
    <el-form-item label="真实姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="真实姓名"></el-input>
    </el-form-item>
    <el-form-item label="身份证" prop="idcard">
      <el-input v-model="dataForm.idcard" placeholder="身份证"></el-input>
    </el-form-item>
    <el-form-item label="性别 0未知，1男，2女" prop="sex">
      <el-input v-model="dataForm.sex" placeholder="性别 0未知，1男，2女"></el-input>
    </el-form-item>
    <el-form-item label="省" prop="province">
      <el-input v-model="dataForm.province" placeholder="省"></el-input>
    </el-form-item>
    <el-form-item label="市" prop="city">
      <el-input v-model="dataForm.city" placeholder="市"></el-input>
    </el-form-item>
    <el-form-item label="县" prop="county">
      <el-input v-model="dataForm.county" placeholder="县"></el-input>
    </el-form-item>
    <el-form-item label="详细地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="详细地址"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          userId: 0,
          username: '',
          mobile: '',
          email: '',
          password: '',
          nickname: '',
          avatar: '',
          name: '',
          idcard: '',
          sex: '',
          province: '',
          city: '',
          county: '',
          address: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户账号不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '电子邮箱不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          avatar: [
            { required: true, message: '头像不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '真实姓名不能为空', trigger: 'blur' }
          ],
          idcard: [
            { required: true, message: '身份证不能为空', trigger: 'blur' }
          ],
          sex: [
            { required: true, message: '性别 0未知，1男，2女不能为空', trigger: 'blur' }
          ],
          province: [
            { required: true, message: '省不能为空', trigger: 'blur' }
          ],
          city: [
            { required: true, message: '市不能为空', trigger: 'blur' }
          ],
          county: [
            { required: true, message: '县不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '详细地址不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: `/users/user/info/${this.dataForm.userId}`,
              method: 'get',
              params: {}
            }).then(response => {
              if (response && response.code === 0) {
                this.dataForm.username = data.user.username
                this.dataForm.mobile = data.user.mobile
                this.dataForm.email = data.user.email
                this.dataForm.password = data.user.password
                this.dataForm.nickname = data.user.nickname
                this.dataForm.avatar = data.user.avatar
                this.dataForm.name = data.user.name
                this.dataForm.idcard = data.user.idcard
                this.dataForm.sex = data.user.sex
                this.dataForm.province = data.user.province
                this.dataForm.city = data.user.city
                this.dataForm.county = data.user.county
                this.dataForm.address = data.user.address
                this.dataForm.createTime = data.user.createTime
                this.dataForm.updateTime = data.user.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: `/users/user/${!this.dataForm.userId ? 'save' : 'update'}`,
              method: `${!this.dataForm.userId ? 'post' : 'put'}`,
              data: {
                'userId': this.dataForm.userId || undefined,
                'username': this.dataForm.username,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'password': this.dataForm.password,
                'nickname': this.dataForm.nickname,
                'avatar': this.dataForm.avatar,
                'name': this.dataForm.name,
                'idcard': this.dataForm.idcard,
                'sex': this.dataForm.sex,
                'province': this.dataForm.province,
                'city': this.dataForm.city,
                'county': this.dataForm.county,
                'address': this.dataForm.address,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              }
            }).then(response => {
              if (response && response.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
