<template>
  <el-dialog title="配置表空间" :visible.sync="formVisible" @close="handleClose">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <el-form :model="form" :inline="true"
                 label-position="right" size="small"
                 label-width="120px">
          <el-form-item label="表空间:">
            <el-select v-model="form.tablespaceInfoList"
                       style="width:400px;" placeholder="请输入关键词"
                       remote :remote-method="findOptions"
                       :loading="optionsLoading"
                       filterable clearable multiple>
              <el-option v-for="item in options.tablespaceInfo"
                         :key="item.key"
                         :label="item.value"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleAddTablespaceInfo">添加</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table :data="form.tablespaceInfoListRaw" style="width: 100%;"
                size="mini" border>
        <el-table-column label="表空间名称" prop="tablespaceName"></el-table-column>
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="{row}">
            <el-button type="danger" size="mini"
                       @click="handleDeleteSingle(row)" class="table-inner-button">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </el-dialog>
</template>

<script>
import appInfoApi from '@/api/appInfo'
import tablespaceInfoApi from '@/api/tablespaceInfo'

export default {
  name: 'TablespaceInfoSetting',
  data() {
    return {
      optionsLoading: false,
      options: {
        tablespaceInfo: []
      },
      form: {
        id: null,
        tablespaceInfoList: [],
        tablespaceInfoListRaw: []
      },
      formUpdated: false,
      formVisible: false
    }
  },
  methods: {
    /**
     * 显示表单
     */
    handleShow(id) {
      this.form.id = id
      this.form.tablespaceInfoList = []
      this.fetchList()
        .then(() => {
          this.formVisible = true
        })
    },
    findOptions(matchValue) {
      // 输入长度小于2，则不加载
      if (!matchValue || matchValue.length < 2) {
        return
      }
      this.optionsLoading = true
      tablespaceInfoApi.findOptions({ matchValue })
        .then(data => { this.options.tablespaceInfo = data })
        .finally(() => {
          this.optionsLoading = false
        })
    },
    /**
     * 查询表格数据
     */
    fetchList() {
      return appInfoApi.fetchTablespaceInfoList(this.form.id)
        .then(data => {
          this.form.tablespaceInfoListRaw = data
        })
    },
    /**
     * 执行添加操作
     */
    handleAddTablespaceInfo() {
      if (!this.form.tablespaceInfoList.length) {
        return
      }
      appInfoApi.addTablespaceInfo(this.form.id, this.form.tablespaceInfoList)
        .then(data => {
          this.$common.showMsg('success', '添加成功')
          this.formUpdated = true
          this.form.tablespaceInfoList = []
          this.fetchList()
        })
    },
    /**
     * 移除单个
     */
    handleDeleteSingle(row) {
      return this.$common.confirm('是否确认移除')
        .then(() => appInfoApi.removeTablespaceInfo(this.form.id, [row.id]))
        .then(() => {
          this.$common.showMsg('success', '移除成功')
          this.formUpdated = true
          this.fetchList()
        })
    },
    /**
     * 表单关闭时触发
     */
    handleClose() {
      if (this.formUpdated) {
        this.$emit('updated')
      }
    }
  }
}
</script>

