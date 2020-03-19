<template>
  <el-dialog title="编辑表结构" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="表名称" prop="tableName">
        <el-input v-model="form.tableName"/>
      </el-form-item>
      <el-form-item label="表描述" prop="tableDesc">
        <el-input v-model="form.tableDesc"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
      <el-button type="primary"
                 @click="doUpdate()">
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import tablestructureInfoApi from '@/api/tablestructureInfo'

function initFormBean() {
  const formBean = {
    id: null,
    tableName: null,
    tableDesc: null
  }
  return formBean
}

export default {
  name: 'TablestructureInfoEdit',
  data() {
    return {
      old: initFormBean(),
      form: initFormBean(),
      formVisible: false,
      formRules: {
        tableName: [
          { required: true, message: '请输入表名称', trigger: 'blur' },
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        tableDesc: [
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    /**
     * 重置表单
     */
    resetForm() {
      for (const key in initFormBean()) {
        this.form[key] = this.old[key]
      }
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(id) {
      tablestructureInfoApi.fetchById(id)
        .then(data => {
          this.old = data
          this.resetForm()
          this.formVisible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].clearValidate()
          })
        })
    },
    /**
     * 执行修改操作
     */
    doUpdate() {
      this.$refs['dataForm'].validate()
        .then(() => tablestructureInfoApi.update(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '修改成功')
          this.$emit('updated', data)
        })
    }
  }
}
</script>
