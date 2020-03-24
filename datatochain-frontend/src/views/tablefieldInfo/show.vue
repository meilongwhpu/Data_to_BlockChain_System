<template>
  <el-dialog title="查看表字段" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="字段名称">
        <span class="form-item-show">
          {{ form.fieldName }}
        </span>
      </el-form-item>
      <el-form-item label="字段描述">
        <span class="form-item-show">
          {{ form.fieldDesc }}
        </span>
      </el-form-item>
      <el-form-item label="字段类型">
        <span class="form-item-show">
          {{ form.fieldType | findEnumLabel(enums.fieldType) }}
        </span>
      </el-form-item>
      <el-form-item label="字段长度">
        <span class="form-item-show">
          {{ form.fieldLength }}
        </span>
      </el-form-item>
      <el-form-item label="类型小数长度">
        <span class="form-item-show">
          {{ form.fieldDecimalLength }}
        </span>
      </el-form-item>
      <el-form-item label="是否主键">
        <span class="form-item-show">
          {{ form.fieldIsKey | findEnumLabel(enums.isKey) }}
        </span>
      </el-form-item>
      <el-form-item label="是否允许为空">
        <span class="form-item-show">
          {{ form.allowNull | findEnumLabel(enums.allowNull) }}
        </span>
      </el-form-item>
      <el-form-item label="默认值">
        <span class="form-item-show">
          {{ form.fieldDefaultValue }}
        </span>
      </el-form-item>
      <el-form-item label="创建者ID">
        <span class="form-item-show">
          {{ form.creatorId }}
        </span>
      </el-form-item>
      <el-form-item label="表名称">
        <span class="form-item-show">
          {{ form.tableName }}
        </span>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import tablefieldInfoApi from '@/api/tablefieldInfo'
import enums from '@/utils/enums'

export default {
  name: 'TablefieldInfoShow',
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        fieldType: enums.getFieldType(),
        isKey: enums.getIsKey(),
        allowNull: enums.getAllowNull()
      },
      form: {
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
      },
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(id) {
      tablefieldInfoApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
