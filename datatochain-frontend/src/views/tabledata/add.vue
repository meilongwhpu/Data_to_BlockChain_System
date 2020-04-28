<template>
  <el-dialog title="新建表结构" :visible.sync="formVisible">
      <el-form :model="dataForm" label-width="120px" ref="dataForm">
          <el-form-item :label="domain.label"
                        :prop="domain.prop"
                        v-for="(domain,index) in activePlayerDataPropLabelArray">
              <el-input   v-model="dataForm[domain.prop]" />
          </el-form-item>
      </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button @click="formVisible = false">
        取消
      </el-button>
      <el-button type="primary"
                 @click="doCreate()">
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import tablestructureInfoApi from '@/api/tablestructureInfo'
import tablespaceInfoApi from '@/api/tablespaceInfo'
import tableDataApi from '@/api/tableData'
import enums from '@/utils/enums'

export default {
  name: 'TableDataAdd',
  data() {
    return {
      options: {
        tablespaceInfo: []
      },
      dataForm: {},
      tableId: '',
      formVisible: false,
      activePlayerDataPropLabelArray:[]
    }
  },
  methods: {

  initFormBean(propLabelArray) {
     const formBean ={};
    for(let i in propLabelArray){
      formBean[propLabelArray[i].prop]="";
     }
     return formBean;
  },

    /**
     * 打开新建表单
     */
    handleCreate(tableId) {
      this.tableId=tableId;
      tableDataApi.fetchTableHeader(tableId)
        .then(data => {
          this.activePlayerDataPropLabelArray=data.tableHeader
          this.dataForm=this.initFormBean(this.activePlayerDataPropLabelArray);
          this.dataForm['tableId']=tableId;
        })
      this.formVisible = true
    },
    /**
     * 执行新建操作
     */
    doCreate() {
      this.$refs['dataForm'].validate()
        .then(() => tableDataApi.addTableValue(this.dataForm))
        .then(data => {
          this.formVisible = false
          //this.$common.showMsg('success', '创建成功')
         // this.$emit('created', data)
        })
    }
  }
}
</script>
