<template>
  <el-dialog title="编辑表字段" :visible.sync="formVisible">
    <el-form ref="dataForm" :rules="formRules" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="表结构名称" prop="tableId">
        <el-select v-model="form.tableId"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in options.tablestructureInfo"
                     :key="item.key"
                     :label="item.value"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="字段名称" prop="fieldName">
        <el-input v-model="form.fieldName"/>
      </el-form-item>
      <el-form-item label="字段描述" prop="fieldDesc">
        <el-input v-model="form.fieldDesc"/>
      </el-form-item>
      <el-form-item label="字段类型" prop="fieldType">
        <el-select v-model="form.fieldType"
                   style="width:100%;" placeholder="请选择"
                   filterable clearable>
          <el-option v-for="item in enums.fieldType"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="字段长度" prop="fieldLength">
        <el-input-number v-model="form.fieldLength" style="width:100%;" controls-position="right"></el-input-number>
      </el-form-item>
      <el-form-item label="类型小数长度" prop="fieldDecimalLength">
        <el-input-number v-model="form.fieldDecimalLength" style="width:100%;" controls-position="right"></el-input-number>
      </el-form-item>
      <el-form-item label="是否主键" prop="fieldIsKey">
        <el-radio-group v-model="form.fieldIsKey">
          <el-radio v-for="item in enums.isKey"
                    :key="item.value"
                    :label="item.value">{{ item.label }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否允许为空" prop="allowNull">
        <el-radio-group v-model="form.allowNull">
          <el-radio v-for="item in enums.allowNull"
                    :key="item.value"
                    :label="item.value">{{ item.label }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="默认值" prop="fieldDefaultValue">
        <el-input v-model="form.fieldDefaultValue"/>
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
import tablefieldInfoApi from '@/api/tablefieldInfo'
import tablestructureInfoApi from '@/api/tablestructureInfo'
import enums from '@/utils/enums'

function initFormBean() {
  const formBean = {
    id: null,
    tableId: null,
    fieldName: null,
    fieldDesc: null,
    fieldType: null,
    fieldLength: null,
    fieldDecimalLength: null,
    fieldIsKey: null,
    allowNull: null,
    fieldDefaultValue: null,
    creatorId: null
  }
  return formBean
}

export default {
  name: 'TablefieldInfoEdit',
  data() {
    return {
      enums: {
        fieldType: enums.getFieldType(),
        isKey: enums.getIsKey(),
        allowNull: enums.getAllowNull()
      },
      options: {
        tablestructureInfo: []
      },
      old: initFormBean(),
      form: initFormBean(),
      formVisible: false,
      formRules: {
        tableId: [
          { required: true, message: '请输入表结构名称', trigger: 'change' }
        ],
        fieldName: [
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        fieldDesc: [
          { max: 50, message: '长度不能超过50个字符', trigger: 'blur' }
        ],
        fieldType: [
          { required: true, message: '请输入字段类型', trigger: 'change' }
        ],
        fieldLength: [
          { required: true, message: '请输入字段长度', trigger: 'blur' }
        ],
        fieldDecimalLength: [
        ],
        fieldIsKey: [
        ],
        allowNull: [
          { required: true, message: '请输入是否允许为空', trigger: 'change' }
        ],
        fieldDefaultValue: [
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
      for (const key in initFormBean()) {
        this.form[key] = this.old[key]
      }
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(id) {
      tablestructureInfoApi.findOptions().then(data => { this.options.tablestructureInfo = data })
      tablefieldInfoApi.fetchById(id)
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
        .then(() => tablefieldInfoApi.update(this.form))
        .then(data => {
          this.formVisible = false
          this.$common.showMsg('success', '修改成功')
          this.$emit('updated', data)
        })
    }
  }
}
</script>
