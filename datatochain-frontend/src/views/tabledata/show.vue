<template>
  <el-dialog title="查看表结构" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="表名称">
        <span class="form-item-show">
          {{ form.tableName }}
        </span>
      </el-form-item>
      <el-form-item label="表描述">
        <span class="form-item-show">
          {{ form.tableDesc }}
        </span>
      </el-form-item>
      <el-form-item label="创建时间">
        <span class="form-item-show">
          {{ form.createTime }}
        </span>
      </el-form-item>
      <el-form-item label="更新时间">
        <span class="form-item-show">
          {{ form.updateTime }}
        </span>
      </el-form-item>
      <el-form-item label="创建者ID">
        <span class="form-item-show">
          {{ form.creatorId }}
        </span>
      </el-form-item>
      <el-form-item label="是否创建">
        <span class="form-item-show">
          {{ form.isCreate | findEnumLabel(enums.isCreate) }}
        </span>
      </el-form-item>
      <el-form-item label="表空间名称">
        <span class="form-item-show">
          {{ form.tablespaceName }}
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
import tablestructureInfoApi from '@/api/tablestructureInfo'
import enums from '@/utils/enums'

export default {
  name: 'TablestructureInfoShow',
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        isCreate: enums.getIsCreate()
      },
      form: {
        id: null,
        tableName: null,
        tableDesc: null,
        createTime: null,
        updateTime: null,
        creatorId: null,
        isCreate: null
      },
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(id) {
      tablestructureInfoApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
