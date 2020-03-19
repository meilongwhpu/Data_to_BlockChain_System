<template>
  <el-dialog title="编辑表空间" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="表空间名称" prop="tablespaceName">
        <el-input v-model="form.tablespaceName"/>
      </el-form-item>
      <el-form-item label="表空间描述" prop="tablespaceDesc">
        <el-input v-model="form.tablespaceDesc"/>
      </el-form-item>
      <el-form-item label="编码格式" prop="codeMode">
        <el-select v-model="form.codeMode"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in enums.codeMode"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="加密模式" prop="encryptionMode">
        <el-select v-model="form.encryptionMode"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in enums.encryptionMode"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上链位置" prop="toChain">
        <el-select v-model="form.toChain"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in enums.chainType"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="form.createTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
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
                 @click="doUpdate()">
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import tablespaceInfoApi from '@/api/tablespaceInfo'
import enums from '@/utils/enums'

function initFormBean() {
  const formBean = {
    id: null,
    tablespaceName: null,
    tablespaceDesc: null,
    codeMode: null,
    encryptionMode: null,
    toChain: null,
    createTime: null,
    creatorId: null
  }
  return formBean
}

export default {
  name: 'TablespaceInfoEdit',
  data() {
    return {
      enums: {
        codeMode: enums.getCodeMode(),
        encryptionMode: enums.getEncryptionMode(),
        chainType: enums.getChainType()
      },
      old: initFormBean(),
      form: initFormBean(),
      formVisible: false,
      formRules: {
        tablespaceName: [
          { required: true, message: '请输入表空间名称', trigger: 'blur' },
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        tablespaceDesc: [
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        codeMode: [
          { required: true, message: '请输入编码格式', trigger: 'change' }
        ],
        encryptionMode: [
          { required: true, message: '请输入加密模式', trigger: 'change' }
        ],
        toChain: [
          { required: true, message: '请输入上链位置', trigger: 'change' }
        ],
        createTime: [
          { required: true, message: '请输入创建时间', trigger: 'change' }
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
      for (const key in initFormBean()) {
        this.form[key] = this.old[key]
      }
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(id) {
      tablespaceInfoApi.fetchById(id)
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
        .then(() => tablespaceInfoApi.update(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '修改成功')
          this.$emit('updated', data)
        })
    }
  }
}
</script>
