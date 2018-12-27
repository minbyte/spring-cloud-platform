<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="应用标识" prop="clientId">
      <el-input v-model="dataForm.clientId" placeholder="应用标识"></el-input>
    </el-form-item>
    <el-form-item label="资源限定串(逗号分割)" prop="resourceIds">
      <el-input v-model="dataForm.resourceIds" placeholder="资源限定串(逗号分割)"></el-input>
    </el-form-item>
    <el-form-item label="应用密钥(bcyt) 加密" prop="clientSecret">
      <el-input v-model="dataForm.clientSecret" placeholder="应用密钥(bcyt) 加密"></el-input>
    </el-form-item>
    <el-form-item label="应用密钥(明文)" prop="clientSecretStr">
      <el-input v-model="dataForm.clientSecretStr" placeholder="应用密钥(明文)"></el-input>
    </el-form-item>
    <el-form-item label="范围" prop="scope">
      <el-input v-model="dataForm.scope" placeholder="范围"></el-input>
    </el-form-item>
    <el-form-item label="5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)" prop="authorizedGrantTypes">
      <el-input v-model="dataForm.authorizedGrantTypes" placeholder="5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)"></el-input>
    </el-form-item>
    <el-form-item label="回调地址 " prop="webServerRedirectUri">
      <el-input v-model="dataForm.webServerRedirectUri" placeholder="回调地址 "></el-input>
    </el-form-item>
    <el-form-item label="权限" prop="authorities">
      <el-input v-model="dataForm.authorities" placeholder="权限"></el-input>
    </el-form-item>
    <el-form-item label="access_token有效期" prop="accessTokenValidity">
      <el-input v-model="dataForm.accessTokenValidity" placeholder="access_token有效期"></el-input>
    </el-form-item>
    <el-form-item label="refresh_token有效期" prop="refreshTokenValidity">
      <el-input v-model="dataForm.refreshTokenValidity" placeholder="refresh_token有效期"></el-input>
    </el-form-item>
    <el-form-item label="{}" prop="additionalInformation">
      <el-input v-model="dataForm.additionalInformation" placeholder="{}"></el-input>
    </el-form-item>
    <el-form-item label="是否自动授权 是-true" prop="autoapprove">
      <el-input v-model="dataForm.autoapprove" placeholder="是否自动授权 是-true"></el-input>
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
          id: 0,
          clientId: '',
          resourceIds: '',
          clientSecret: '',
          clientSecretStr: '',
          scope: '',
          authorizedGrantTypes: '',
          webServerRedirectUri: '',
          authorities: '',
          accessTokenValidity: '',
          refreshTokenValidity: '',
          additionalInformation: '',
          autoapprove: ''
        },
        dataRule: {
          clientId: [
            { required: true, message: '应用标识不能为空', trigger: 'blur' }
          ],
          resourceIds: [
            { required: true, message: '资源限定串(逗号分割)不能为空', trigger: 'blur' }
          ],
          clientSecret: [
            { required: true, message: '应用密钥(bcyt) 加密不能为空', trigger: 'blur' }
          ],
          clientSecretStr: [
            { required: true, message: '应用密钥(明文)不能为空', trigger: 'blur' }
          ],
          scope: [
            { required: true, message: '范围不能为空', trigger: 'blur' }
          ],
          authorizedGrantTypes: [
            { required: true, message: '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)不能为空', trigger: 'blur' }
          ],
          webServerRedirectUri: [
            { required: true, message: '回调地址 不能为空', trigger: 'blur' }
          ],
          authorities: [
            { required: true, message: '权限不能为空', trigger: 'blur' }
          ],
          accessTokenValidity: [
            { required: true, message: 'access_token有效期不能为空', trigger: 'blur' }
          ],
          refreshTokenValidity: [
            { required: true, message: 'refresh_token有效期不能为空', trigger: 'blur' }
          ],
          additionalInformation: [
            { required: true, message: '{}不能为空', trigger: 'blur' }
          ],
          autoapprove: [
            { required: true, message: '是否自动授权 是-true不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: `/security/clientdetails/info/${this.dataForm.id}`,
              method: 'get',
              params: {}
            }).then(response => {
              if (response && response.code === 0) {
                this.dataForm.clientId = data.clientdetails.clientId
                this.dataForm.resourceIds = data.clientdetails.resourceIds
                this.dataForm.clientSecret = data.clientdetails.clientSecret
                this.dataForm.clientSecretStr = data.clientdetails.clientSecretStr
                this.dataForm.scope = data.clientdetails.scope
                this.dataForm.authorizedGrantTypes = data.clientdetails.authorizedGrantTypes
                this.dataForm.webServerRedirectUri = data.clientdetails.webServerRedirectUri
                this.dataForm.authorities = data.clientdetails.authorities
                this.dataForm.accessTokenValidity = data.clientdetails.accessTokenValidity
                this.dataForm.refreshTokenValidity = data.clientdetails.refreshTokenValidity
                this.dataForm.additionalInformation = data.clientdetails.additionalInformation
                this.dataForm.autoapprove = data.clientdetails.autoapprove
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
              url: `/security/clientdetails/${!this.dataForm.id ? 'save' : 'update'}`,
              method: `${!this.dataForm.id ? 'post' : 'put'}`,
              data: {
                'id': this.dataForm.id || undefined,
                'clientId': this.dataForm.clientId,
                'resourceIds': this.dataForm.resourceIds,
                'clientSecret': this.dataForm.clientSecret,
                'clientSecretStr': this.dataForm.clientSecretStr,
                'scope': this.dataForm.scope,
                'authorizedGrantTypes': this.dataForm.authorizedGrantTypes,
                'webServerRedirectUri': this.dataForm.webServerRedirectUri,
                'authorities': this.dataForm.authorities,
                'accessTokenValidity': this.dataForm.accessTokenValidity,
                'refreshTokenValidity': this.dataForm.refreshTokenValidity,
                'additionalInformation': this.dataForm.additionalInformation,
                'autoapprove': this.dataForm.autoapprove
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
