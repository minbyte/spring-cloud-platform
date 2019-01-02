<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="数据值" prop="value">
      <el-input v-model="dataForm.value" placeholder="数据值"></el-input>
    </el-form-item>
    <el-form-item label="标签名" prop="label">
      <el-input v-model="dataForm.label" placeholder="标签名"></el-input>
    </el-form-item>
    <el-form-item label="类型" prop="type">
      <el-input v-model="dataForm.type" placeholder="类型"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="描述"></el-input>
    </el-form-item>
    <el-form-item label="排序（升序）" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序（升序）"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="备注信息" prop="remarks">
      <el-input v-model="dataForm.remarks" placeholder="备注信息"></el-input>
    </el-form-item>
    <el-form-item label="状态，1正常 0禁用" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态，1正常 0禁用"></el-input>
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
          value: '',
          label: '',
          type: '',
          description: '',
          sort: '',
          createTime: '',
          updateTime: '',
          remarks: '',
          status: ''
        },
        dataRule: {
          value: [
            { required: true, message: '数据值不能为空', trigger: 'blur' }
          ],
          label: [
            { required: true, message: '标签名不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序（升序）不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          remarks: [
            { required: true, message: '备注信息不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态，1正常 0禁用不能为空', trigger: 'blur' }
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
              url: `/admins/dict/info/${this.dataForm.id}`,
              method: 'get',
              params: {}
            }).then(response => {
              if (response && response.code === 0) {
                this.dataForm.value = data.dict.value
                this.dataForm.label = data.dict.label
                this.dataForm.type = data.dict.type
                this.dataForm.description = data.dict.description
                this.dataForm.sort = data.dict.sort
                this.dataForm.createTime = data.dict.createTime
                this.dataForm.updateTime = data.dict.updateTime
                this.dataForm.remarks = data.dict.remarks
                this.dataForm.status = data.dict.status
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
              url: `/admins/dict/${!this.dataForm.id ? 'save' : 'update'}`,
              method: `${!this.dataForm.id ? 'post' : 'put'}`,
              data: {
                'id': this.dataForm.id || undefined,
                'value': this.dataForm.value,
                'label': this.dataForm.label,
                'type': this.dataForm.type,
                'description': this.dataForm.description,
                'sort': this.dataForm.sort,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'remarks': this.dataForm.remarks,
                'status': this.dataForm.status
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
