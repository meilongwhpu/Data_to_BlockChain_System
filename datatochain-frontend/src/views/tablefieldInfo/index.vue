<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.tableName" placeholder="表名称"
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
      <el-table-column label="表结构名称"
                       prop="tableId"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.tableId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="字段名称"
                       prop="fieldName"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.fieldName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="字段描述"
                       prop="fieldDesc"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.fieldDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column label="字段类型"
                       prop="fieldType"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.fieldType | findEnumLabel(enums.fieldType) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="字段长度"
                       prop="fieldLength"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.fieldLength }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否允许为空"
                       prop="allowNull"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.allowNull | findEnumLabel(enums.allowNull) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者ID"
                       prop="creatorId"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.creatorId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表名称"
                       prop="tableName"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.tableName }}</span>
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
    <tablefieldInfo-add ref="tablefieldInfoAdd" @created="doQueryList({ page: 1 })"/>
    <!-- 编辑表单 -->
    <tablefieldInfo-edit ref="tablefieldInfoEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <tablefieldInfo-show ref="tablefieldInfoShow"/>
  </div>
</template>

<script>
import tablefieldInfoAdd from './add'
import tablefieldInfoEdit from './edit'
import tablefieldInfoShow from './show'
import tablefieldInfoApi from '@/api/tablefieldInfo'
import enums from '@/utils/enums'
import Pagination from '@/components/Pagination'

export default {
  name: 'TablefieldInfoTable',
  components: {
    Pagination,
    tablefieldInfoAdd,
    tablefieldInfoEdit,
    tablefieldInfoShow
  },
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        fieldType: enums.getFieldType(),
        allowNull: enums.getAllowNull()
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
      return tablefieldInfoApi.fetchList(this.query)
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
        .then(() => tablefieldInfoApi.deleteById(row.id))
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
        this.$common.showMsg('warning', '请选择表字段')
        return
      }
      return this.$common.confirm('是否确认删除')
        .then(() => tablefieldInfoApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.tablefieldInfoAdd.handleCreate()
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.tablefieldInfoShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.tablefieldInfoEdit.handleUpdate(row.id)
    }
  }
}
</script>
