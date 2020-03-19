<template>
  <el-dialog title="查看应用" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="主键">
        <span class="form-item-show">
          {{ form.id }}
        </span>
      </el-form-item>
      <el-form-item label="应用名称">
        <span class="form-item-show">
          {{ form.appName }}
        </span>
      </el-form-item>
      <el-form-item label="应用介绍">
        <span class="form-item-show">
          {{ form.appDesc }}
        </span>
      </el-form-item>
      <el-form-item label="应用key">
        <span class="form-item-show">
          {{ form.appKey }}
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
import appInfoApi from '@/api/appInfo'

export default {
  name: 'AppInfoShow',
  data() {
    return {
      form: {
        id: null,
        appName: null,
        appDesc: null,
        appKey: null,
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
      appInfoApi.fetchById(id)
        .then(data => {
          this.form = data
          this.formVisible = true
        })
    }
  }
}
</script>
