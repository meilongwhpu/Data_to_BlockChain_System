<template>
  <div class="app-container">
    <div class="filter-container">
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
      <el-table-column label="参数key值"
                       prop="dictKey"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.dictKey }}</span>
        </template>
      </el-table-column>
      <el-table-column label="参数值"
                       prop="dictValue"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.dictValue }}</span>
        </template>
      </el-table-column>
      <el-table-column label="参数描述"
                       prop="dictDesc"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.dictDesc }}</span>
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
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="query.page"
                :limit.sync="query.limit" @pagination="doQueryList"/>
    <!-- 新建表单 -->
    <systemDict-add ref="systemDictAdd" @created="doQueryList({ page: 1 })"/>
    <!-- 编辑表单 -->
    <systemDict-edit ref="systemDictEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <systemDict-show ref="systemDictShow"/>
  </div>
</template>

<script>
import systemDictAdd from './add'
import systemDictEdit from './edit'
import systemDictShow from './show'
import systemDictApi from '@/api/systemDict'
import Pagination from '@/components/Pagination'

export default {
  name: 'SystemDictTable',
  components: {
    Pagination,
    systemDictAdd,
    systemDictEdit,
    systemDictShow
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      query: {
        page: 1,
        limit: 10
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
      return systemDictApi.fetchList(this.query)
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
        .then(() => systemDictApi.deleteById(row.id))
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
        this.$common.showMsg('warning', '请选择系统参数')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => systemDictApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.systemDictAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.systemDictShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.systemDictEdit.handleUpdate(row.id)
    }
  }
}
</script>
