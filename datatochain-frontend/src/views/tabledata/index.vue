<template >
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="tableInfo.tablespaceName" @change="selectTableSpace"
                 style="width:20%;"  placeholder="表空间名称"
                 filterable clearable>
        <el-option v-for="item in tablespaces.tablespaceInfo"
                   :key="item.key"
                   :label="item.value"
                   :value="item.key">
        </el-option>
      </el-select>
      <el-select v-model="tableInfo.tableName" @change="selectTableName"
                 style="width:20%;"  placeholder="已创建表的表名称"
                 filterable clearable>
        <el-option v-for="item in tablenames.tablenameInfo"
                   :key="item.key"
                   :label="item.value"
                   :value="item.key">
        </el-option>
      </el-select>
      <!--
      <el-button class="filter-item" icon="el-icon-search" type="primary"
                 @click="handleQuery">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="success"
                 icon="el-icon-edit" @click="handleCreate">
        新建
      </el-button>
       -->
      <el-button class="filter-item" style="margin-left: 10px;" type="danger"
                 icon="el-icon-delete" @click="handleDeleteBatch">
        删除
      </el-button>

    </div>
<el-table :data="tableData" v-loading="listLoading"  @selection-change='selectionChange' @row-click="verifyData">
 <el-table-column type="selection" width="45px"></el-table-column>
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
    <tabledata-add ref="tabledataAdd" @created="doQueryList({ page: 1 })"/>
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
  name: 'tabledata',
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
      tableData:[],
      tableInfo:[]
    }
  },
  created() {
    this.queryTableSpaceInfo();
    if(this.tableId){
      this.queryTableInfo(this.tableId);
      this.doQueryList({page: 1, tableId:this.tableId })
    }
  },
  methods: {
   selectTableSpace(value){
      if(value){
             tablestructureInfoApi.findCreatedTableName(value).then(data => { this.tablenames.tablenameInfo = data})
      }
   },
   selectTableName(value){
      this.doQueryList({ page: 1, tableId:value })
      this.tableId=value
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
    queryTableSpaceInfo(){
      tablespaceInfoApi.findOptions().then(data => {
             this.tablespaces.tablespaceInfo = data
       })
    },
    queryTableInfo(tableId){
      tableDataApi.getTableInfo(tableId).then(data => {
             this.tableInfo.tablespaceName=data.tableSpaceName;
             this.tableInfo.tableName=data.tableName;
       })
    },
    /**
     * 执行列表查询
     */
    doQueryList({ page, limit ,tableId}) {
      if (page) {
        this.query.page = page
      }
      if (limit) {
        this.query.limit = limit
      }
      if(tableId){
      this.query.tableId=tableId
      }
      this.listLoading = true
      return tableDataApi.fetchList(this.query)
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
      this.doQueryList({page: 1,tableId:id })
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
        .then(() => tableDataApi.deleteBatch(this.tableId,this.selectItems.map(row => row._id)))
        .then(() => {
          this.$common.showMsg('success', '删除成功')
          return this.doQueryList({ page: 1,tableId:this.tableId })
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
