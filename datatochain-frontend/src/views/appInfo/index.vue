<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.appName" placeholder="应用名称"
                style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleQuery"/>
      <el-button class="filter-item" icon="el-icon-search" type="primary"
                 @click="handleQuery">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="success"
                 icon="el-icon-edit" @click="handleCreate">
        新建
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="danger"
                 icon="el-icon-delete" @click="handleDeleteBatch">
        删除
      </el-button>
    </div>

    <el-table v-loading="listLoading" :data="list"
              @selection-change="selectionChange"
              border stripe style="width: 100%;">
      <el-table-column type="selection" width="50" />
      <el-table-column label="主键"
                       prop="id"
                       align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="应用名称"
                       prop="appName"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.appName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="应用介绍"
                       prop="appDesc"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.appDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column label="应用key"
                       prop="appKey"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.appKey }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间"
                       prop="createTime"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间"
                       prop="updateTime"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者ID"
                       prop="creatorId"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.creatorId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230">
        <template slot-scope="{row}">
          <el-button size="mini"
                     @click="handleShow(row)" class="table-inner-button">查看</el-button>
          <el-button type="primary" size="mini"
                     @click="handleUpdate(row)" class="table-inner-button">编辑</el-button>
          <el-button type="danger" size="mini"
                     @click="handleDeleteSingle(row)" class="table-inner-button">删除</el-button>
          <el-button type="success" size="mini"
                     @click="handleTablespaceInfoAddRemove(row)" class="table-inner-button">配置表空间</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="query.page"
                :limit.sync="query.limit" @pagination="doQueryList"/>
    <!-- 新建表单 -->
    <appInfo-add ref="appInfoAdd" @created="doQueryList({ page: 1 })"/>
    <!-- 编辑表单 -->
    <appInfo-edit ref="appInfoEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <appInfo-show ref="appInfoShow"/>
    <!-- 添加移除表空间 -->
    <tablespaceInfo-add-remove ref="tablespaceInfoAddRemove" @updated="doQueryList({})"/>
  </div>
</template>

<script>
import appInfoAdd from './add'
import appInfoEdit from './edit'
import appInfoShow from './show'
import tablespaceInfoAddRemove from './tablespaceInfoAddRemove'
import appInfoApi from '@/api/appInfo'
import Pagination from '@/components/Pagination'

export default {
  name: 'AppInfoTable',
  components: {
    Pagination,
    appInfoAdd,
    appInfoEdit,
    appInfoShow,
    tablespaceInfoAddRemove
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      query: {
        page: 1,
        limit: 10,
        appName: null
      },
      selectItems: []
    }
  },
  created() {
    this.doQueryList({ page: 1 })
  },
  methods: {
    /**
     * 选择框变化
     */
    selectionChange(val) {
      this.selectItems = val
    },
    /**
     * 触发搜索操作
     */
    handleQuery() {
      this.doQueryList({ page: 1 })
    },
    /**
     * 执行列表查询
     */
    doQueryList({ page, limit }) {
      if (page) {
        this.query.page = page
      }
      if (limit) {
        this.query.limit = limit
      }
      this.listLoading = true
      return appInfoApi.fetchList(this.query)
        .then(data => {
          this.list = data.list
          this.total = data.total
        })
        .finally(() => {
          this.listLoading = false
        })
    },
    /**
     * 删除单条记录
     */
    handleDeleteSingle(row) {
      return this.$common.confirm('是否确认删除')
        .then(() => appInfoApi.deleteById(row.id))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
    /**
     * 批量删除记录
     */
    handleDeleteBatch() {
      if (this.selectItems.length <= 0) {
        this.$common.showMsg('warning', '请选择应用')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => appInfoApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.appInfoAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.appInfoShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.appInfoEdit.handleUpdate(row.id)
    },
    /**
     * 打开添加移除表空间表单
     */
    handleTablespaceInfoAddRemove(row) {
      this.$refs.tablespaceInfoAddRemove.handleShow(row.id)
    }
  }
}
</script>
