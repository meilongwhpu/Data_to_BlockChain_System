<template>
  <div class="app-container">
    <div class="filter-container">
      <el-date-picker v-model="query.operTimeStart"
                      type="date"
                      style="width:200px;" class="filter-item"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      placeholder="操作时间(开始于)"></el-date-picker>
      <el-date-picker v-model="query.operTimeEnd"
                      type="date"
                      style="width:200px;" class="filter-item"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      placeholder="操作时间(结束于)"></el-date-picker>
      <el-button class="filter-item" icon="el-icon-search" type="primary"
                 @click="handleQuery">
        搜索
      </el-button>
    </div>

    <el-table v-loading="listLoading" :data="list"
              border stripe style="width: 100%;">
      <el-table-column label="主键"
                       prop="id"
                       align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户ID"
                       prop="userId"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.userId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作时间"
                       prop="operTime"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.operTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作科目"
                       prop="operSubject"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.operSubject }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作明细"
                       prop="operDetail"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.operDetail }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户端IP"
                       prop="operIp"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.operIp }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注"
                       prop="remark"
                       align="center">
        <template slot-scope="{row}">
          <span>{{ row.remark }}</span>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="query.page"
                :limit.sync="query.limit" @pagination="doQueryList"/>
  </div>
</template>

<script>
import operateLogApi from '@/api/operateLog'
import Pagination from '@/components/Pagination'

export default {
  name: 'OperateLogTable',
  components: {
    Pagination
  },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      query: {
        page: 1,
        limit: 10,
        operTimeStart: '',
        operTimeEnd: ''
      }
    }
  },
  created() {
    this.doQueryList({ page: 1 })
  },
  methods: {
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
      return operateLogApi.fetchList(this.query)
        .then(data => {
          this.list = data.list
          this.total = data.total
        })
        .finally(() => {
          this.listLoading = false
        })
    }
  }
}
</script>
