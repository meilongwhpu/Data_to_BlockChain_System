<template >
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
<el-table :data="tableData" v-loading="listLoading" @selection-change='selectionChange' @row-click="verifyData">
  <el-table-column type="selection" width="45px"></el-table-column>
  </el-table-column>
    <el-table-column align="center" v-for="(item,index) in activePlayerDataPropLabelArray" :prop="item.prop" :label="item.label"
      :key="item.prop">
      <template slot-scope="scope">
              {{scope.row[item.prop]?scope.row[item.prop]:'暂无数据'}}
      </template>
    </el-table-column>
 </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="query.page"
                :limit.sync="query.limit" @pagination="doQueryList"/>
    <!-- 新建表单 -->
    <tabledata-add ref="tabledataAdd" />
    <!-- 编辑表单 -->
    <tablestructureInfo-edit ref="tablestructureInfoEdit" @updated="doQueryList({})"/>
    <!-- 查看表单 -->
    <tabledata-show ref="tabledataShow"/>
  </div>
</template>

<script>
import tabledataAdd from './add'
import tabledataEdit from './edit'
import tabledataShow from './show'
import tableDataApi from '@/api/tableData'
import enums from '@/utils/enums'
import Pagination from '@/components/Pagination'
import tablespaceInfoApi from '@/api/tablespaceInfo'
import tablestructureInfoApi from '@/api/tablestructureInfo'

export default {
  name: 'tabledataShow',
  components: {
    Pagination,
    tabledataAdd,
    tabledataEdit,
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
      tablespaces: {
        tablespaceInfo: []
      },
      tablenames:{
        tablenameInfo:[]
      },
      list: [],
      total: 0,
      listLoading: true,
      tableId: this.$route.query.tableId,
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
         console.log("---------"+this.tableId)

         //  this.doQueryList({ tableId:this.tableId ,page: 1 })

  },
  methods: {
   selectTableSpace(value){
       tablestructureInfoApi.findTableName(value).then(data => { this.tablenames.tablenameInfo = data})
   },
    /**
     * 选择框变化
     */
    selectionChange(val) {
      this.selectItems = val
    },
    verifyData(val){
        this.$refs.tabledataShow.handleShow(this.tableId,val._id);
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
        console.log("---2---"+tableId)
      this.query.tableId=tableId
      this.listLoading = true
      return tableDataApi.fetchList(this.query)
        .then(data => {
        cosole.log("---1---"+data.tableData);
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
        .then(() => tableDataApi.createTable(row.id))
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
        .then(() => tableDataApi.deleteBatch(this.selectItems.map(row => row.hash)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1 })
        })
    },
    /**
     * 打开新建表单
     */
    handleCreate() {
      this.$refs.tabledataAdd.handleCreate(this.tableId)
    },
    /**
     * 打开查看表单
     */
    handleShow(row) {
      this.$refs.tabledataShow.handleShow(row.id)
    },
    /**
     * 打开编辑表单
     */
    handleUpdate(row) {
      this.$refs.tabledataEdit.handleUpdate(row.id)
    }
  }
}
</script>
