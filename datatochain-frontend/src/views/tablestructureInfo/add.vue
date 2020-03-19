<template>
  <el-dialog title="新建表结构" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="表空间名称" prop="tablespaceId">
        <el-select v-model="form.tablespaceId"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in options.tablespaceInfo"
                     :key="item.key"
                     :label="item.value"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="表名称" prop="tableName">
        <el-input v-model="form.tableName"/>
      </el-form-item>
      <el-form-item label="表描述" prop="tableDesc">
        <el-input v-model="form.tableDesc"/>
      </el-form-item>
      <el-form-item label="创建者ID" prop="creatorId">
        <el-input v-model="form.creatorId"/>
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
import tablestructureInfoApi from '@/api/tablestructureInfo'
import tablespaceInfoApi from '@/api/tablespaceInfo'

function initFormBean() {
  const formBean = {
    tablespaceId: null,
    tableName: null,
    tableDesc: null,
    creatorId: null
  }
  return formBean
}

export default {
  name: 'TablestructureInfoAdd',
  data() {
    return {
      options: {
        tablespaceInfo: []
      },
      form: initFormBean(),
      formVisible: false,
      formRules: {
        tablespaceId: [
          { required: true, message: '请输入表空间名称', trigger: 'change' }
        ],
        tableName: [
          { required: true, message: '请输入表名称', trigger: 'blur' },
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        tableDesc: [
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        creatorId: [
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
      tablespaceInfoApi.findOptions().then(data => { this.options.tablespaceInfo = data })
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
        .then(() => tablestructureInfoApi.create(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '创建成功')
          this.$emit('created', data)
        })
    }
  }
}
</script>
