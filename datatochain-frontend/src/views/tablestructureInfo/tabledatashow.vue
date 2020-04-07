<template>
 <div class="app-container">
<el-table :data="tableData" v-loading="listLoading">
    <el-table-column align="center" v-for="(item,index) in activePlayerDataPropLabelArray" :prop="item.prop" :label="item.label"
      :key="item.prop">
      <template slot-scope="scope">
        {{scope.row[item.prop]?scope.row[item.prop]:'暂无数据'}}
      </template>
    </el-table-column>
 </el-table>
  </div>
</template>

<script>
import tabledataApi from '@/api/tableData'
import enums from '@/utils/enums'
import Pagination from '@/components/Pagination'

export default {
  name: 'tabledataShow',
  components: {
    Pagination
  },
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
     tableId: this.$route.query.tableId,
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

      selectItems: [],
      activePlayerDataPropLabelArray:[],
      tableData:[]
    }
  },
  created() {
  console.log(this.tableId);
    this.doQueryList({ tableId:this.tableId ,page: 1 })
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
    doQueryList({tableId, page, limit }) {
      if (page) {
        this.query.page = page
      }
      if (limit) {
        this.query.limit = limit
      }

      this.query.id=tableId
      this.listLoading = true
      return tabledataApi.fetchList(this.query)
        .then(data => {
          this.activePlayerDataPropLabelArray=data.tableHeader
          this.tableData=data.tableData
          this.list = data.list
          this.total = data.total
        })
        .finally(() => {
          this.listLoading = false
        })
    },

    /**
     * 触发搜索操作
     */
    showTableData(id) {
      this.doQueryList({tableId:id, page: 1 })
    },

     /**
      * 创建表结构
      */
    handleTablestructureInfoAdd(row){
      return this.$common.confirm('是否确认创建')
        .then(() => tabledataApi.createTable(row.id))
        .then(() => {
          this.$common.showMsg('success', '创建成功')
          return this.doQueryList({ page: 1 })
        })
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
        .then(() => tabledataApi.deleteBatch(this.selectItems.map(row => row.id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    }
  }
}
</script>
