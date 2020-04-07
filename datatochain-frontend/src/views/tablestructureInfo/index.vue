<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.tablespaceName" placeholder="表空间名称"
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
      <el-table-column label="表名称"
                       prop="tableName"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.tableName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表描述"
                       prop="tableDesc"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.tableDesc }}</span>
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
      <el-table-column label="是否创建"
                       prop="isCreate"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.isCreate | findEnumLabel(enums.isCreate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表空间名称"
                       prop="tablespaceName"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.tablespaceName }}</span>
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
                     @click="handleTablestructureInfoAdd(row)" class="table-inner-button" :disabled="row.isCreate==1?true:false">创建表结构</el-button>
          <el-button type="success" size="mini"
                     @click="handleTableDataShow(row)" class="table-inner-button">查看表数据</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="query.page"
                :limit.sync="query.limit" @pagination="doQueryList"/>
    <!-- 新建表单 -->
    <tablestructureInfo-add ref="tablestructureInfoAdd" @created="doQueryList({ page: 1 })"/>
    <!-- 编辑表单 -->
    <tablestructureInfo-edit ref="tablestructureInfoEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <tablestructureInfo-show ref="tablestructureInfoShow"/>

  </div>
</template>

<script>
import tablestructureInfoAdd from './add'
import tablestructureInfoEdit from './edit'
import tablestructureInfoShow from './show'
import tablestructureInfoApi from '@/api/tablestructureInfo'
import enums from '@/utils/enums'
import Pagination from '@/components/Pagination'
import tabledataShow from './tabledatashow'

export default {
  name: 'TablestructureInfoTable',
  components: {
    Pagination,
    tablestructureInfoAdd,
    tablestructureInfoEdit,
    tablestructureInfoShow,
    tabledataShow
  },
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        isCreate: enums.getIsCreate()
      },
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
      return tablestructureInfoApi.fetchList(this.query)
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
        .then(() => tablestructureInfoApi.deleteById(row.id))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
     /**
      * 创建表结构
      */
    handleTablestructureInfoAdd(row){
      return this.$common.confirm('是否确认创建')
        .then(() => tablestructureInfoApi.createTable(row.id))
        .then(() => {
          this.$common.showMsg('success', '创建成功')
          return this.doQueryList({ page: 1 })
        })
    },
     /**
      * 查询表数据
      */
   handleTableDataShow(row){
          let newQuery = {tableId: row.id};
          console.log(newQuery);
          this.$router.push({
            name: 'tableData',
            query: newQuery
          })
     // this.$refs.tabledataShow.showTableData(row.id);
    },
    /**
     * 批量删除记录
     */
    handleDeleteBatch() {
      if (this.selectItems.length <= 0) {
        this.$common.showMsg('warning', '请选择表结构')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => tablestructureInfoApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.tablestructureInfoAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
       this.$refs.tablestructureInfoShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.tablestructureInfoEdit.handleUpdate(row.id)
    }
  }
}
</script>
