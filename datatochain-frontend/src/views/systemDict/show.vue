<template>
  <el-dialog title="查看系统参数" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="参数key值">
        <span class="form-item-show">
          {{ form.dictKey }}
        </span>
      </el-form-item>
      <el-form-item label="参数值">
        <span class="form-item-show">
          {{ form.dictValue }}
        </span>
      </el-form-item>
      <el-form-item label="参数描述">
        <span class="form-item-show">
          {{ form.dictDesc }}
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
import systemDictApi from '@/api/systemDict'

export default {
  name: 'SystemDictShow',
  data() {
    return {
      form: {
        id: null,
        dictKey: null,
        dictValue: null,
        dictDesc: null
      },
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(id) {
      systemDictApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
