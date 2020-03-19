<template>
  <el-dialog title="查看表空间" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="表空间名称">
        <span class="form-item-show">
          {{ form.tablespaceName }}
        </span>
      </el-form-item>
      <el-form-item label="表空间描述">
        <span class="form-item-show">
          {{ form.tablespaceDesc }}
        </span>
      </el-form-item>
      <el-form-item label="编码格式">
        <span class="form-item-show">
          {{ form.codeMode | findEnumLabel(enums.codeMode) }}
        </span>
      </el-form-item>
      <el-form-item label="加密模式">
        <span class="form-item-show">
          {{ form.encryptionMode | findEnumLabel(enums.encryptionMode) }}
        </span>
      </el-form-item>
      <el-form-item label="上链位置">
        <span class="form-item-show">
          {{ form.toChain | findEnumLabel(enums.chainType) }}
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
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import tablespaceInfoApi from '@/api/tablespaceInfo'
import enums from '@/utils/enums'

export default {
  name: 'TablespaceInfoShow',
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        codeMode: enums.getCodeMode(),
        encryptionMode: enums.getEncryptionMode(),
        chainType: enums.getChainType()
      },
      form: {
        id: null,
        tablespaceName: null,
        tablespaceDesc: null,
        codeMode: null,
        encryptionMode: null,
        toChain: null,
        createTime: null,
        updateTime: null,
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
      tablespaceInfoApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
