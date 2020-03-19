<template>
  <el-dialog title="新建应用" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="应用介绍" prop="appDesc">
        <el-input v-model="form.appDesc"/>
      </el-form-item>
      <el-form-item label="应用key" prop="appKey">
        <el-input v-model="form.appKey"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="form.createTime" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker v-model="form.updateTime" type="date" placeholder="选择日期"></el-date-picker>
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
import appInfoApi from '@/api/appInfo'

function initFormBean() {
  const formBean = {
    appDesc: null,
    appKey: null,
    createTime: null,
    updateTime: null
  }
  return formBean
}

export default {
  name: 'AppInfoAdd',
  data() {
    return {
      form: initFormBean(),
      formVisible: false,
      formRules: {
        appDesc: [
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        appKey: [
          { required: true, message: '请输入应用key', trigger: 'blur' },
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        createTime: [
        ],
        updateTime: [
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
        .then(() => appInfoApi.create(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '创建成功')
          this.$emit('created', data)
        })
    }
  }
}
</script>
