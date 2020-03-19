<template>
  <el-dialog title="新建系统参数" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="参数key值" prop="dictKey">
        <el-input v-model="form.dictKey"/>
      </el-form-item>
      <el-form-item label="参数值" prop="dictValue">
        <el-input v-model="form.dictValue"/>
      </el-form-item>
      <el-form-item label="参数描述" prop="dictDesc">
        <el-input v-model="form.dictDesc"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
      <el-button type="primary"
                 @click="doCreate()">
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import systemDictApi from '@/api/systemDict'

function initFormBean() {
  const formBean = {
    dictKey: null,
    dictValue: null,
    dictDesc: null
  }
  return formBean
}

export default {
  name: 'SystemDictAdd',
  data() {
    return {
      form: initFormBean(),
      formVisible: false,
      formRules: {
        dictKey: [
          { required: true, message: '请输入参数key值', trigger: 'blur' },
          { max: 100, message: '长度不能超过100个字符', trigger: 'blur' }
        ],
        dictValue: [
          { required: true, message: '请输入参数值', trigger: 'blur' },
          { max: 100, message: '长度不能超过100个字符', trigger: 'blur' }
        ],
        dictDesc: [
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
      this.form = initFormBean()
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.resetForm()
      this.formVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    /**
     * 执行新建操作
     */
    doCreate() {
      this.$refs['dataForm'].validate()
        .then(() => systemDictApi.create(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '创建成功')
          this.$emit('created', data)
        })
    }
  }
}
</script>
