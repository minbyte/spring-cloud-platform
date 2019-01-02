<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="键" prop="key">
      <el-input v-model="dataForm.key" placeholder="键"></el-input>
    </el-form-item>
    <el-form-item label="值" prop="value">
      <el-input v-model="dataForm.value" placeholder="值"></el-input>
    </el-form-item>
    <el-form-item label="状态   0：隐藏   1：显示" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态   0：隐藏   1：显示"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remarks">
      <el-input v-model="dataForm.remarks" placeholder="备注"></el-input>
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
          key: '',
          value: '',
          status: '',
          remarks: ''
        },
        dataRule: {
          key: [
            { required: true, message: '键不能为空', trigger: 'blur' }
          ],
          value: [
            { required: true, message: '值不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态   0：隐藏   1：显示不能为空', trigger: 'blur' }
          ],
          remarks: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
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
              url: `/admins/config/info/${this.dataForm.id}`,
              method: 'get',
              params: {}
            }).then(response => {
              if (response && response.code === 0) {
                this.dataForm.key = data.config.key
                this.dataForm.value = data.config.value
                this.dataForm.status = data.config.status
                this.dataForm.remarks = data.config.remarks
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
              url: `/admins/config/${!this.dataForm.id ? 'save' : 'update'}`,
              method: `${!this.dataForm.id ? 'post' : 'put'}`,
              data: {
                'id': this.dataForm.id || undefined,
                'key': this.dataForm.key,
                'value': this.dataForm.value,
                'status': this.dataForm.status,
                'remarks': this.dataForm.remarks
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
