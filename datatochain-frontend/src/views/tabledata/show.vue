<template>
  <el-dialog title="查看链上数据" :visible.sync="formVisible">
    <el-form ref="dataForm" :model="form"
             label-position="left" size="small"
             label-width="100px" style="width: 700px; margin-left:50px;">
      <el-form-item label="数据链HASH值">
        <span class="form-item-show">
          {{ form.hash }}
        </span>
      </el-form-item>
      <el-form-item label="链上数据">
        <span class="form-item-show">
          {{ form.remark }}
        </span>
      </el-form-item>
      <el-form-item label="上链时间">
        <span class="form-item-show">
          {{ form.time }}
        </span>
      </el-form-item>
      <el-form-item label="上链高度">
        <span class="form-item-show">
          {{ form.blockHeight }}
        </span>
      </el-form-item>
      <el-form-item label="是否篡改">
        <span class="form-item-show">
          {{ form.isModify }}
        </span>
      </el-form-item>
      <el-form-item label="二维码">
     <vue-qr
      :bgSrc="config.imagePath"
      :text="config.value"
      :size="100"
      :margin="0"></vue-qr>
      </el-form-item>

  </el-form>
  </el-dialog>
</template>

<script>
import QRCode from 'qrcodejs2'
//import QRCode from 'qrcode'
import VueQArt from 'vue-qart'
import VueQr from 'vue-qr'
import enums from '@/utils/enums'
import tableDataApi from '@/api/tableData'

export default {
  name: 'VerifyDataShow',
  components: {
  VueQArt,
      QRCode,
      VueQr
   },
  filters: {
    findEnumLabel: enums.findEnumLabel
  },
  data() {
    return {
      enums: {
        isCreate: enums.getIsCreate()
      },
      form: {
        hash: null,
        remark: null,
        time: null,
        blockHeight:null,
        isModify: null
      },
      config: {
          value: '',
          imagePath: require('../../assets/nuls.png')
      	},
      formVisible: false
    }
  },
  methods: {
    /**
     * 打开查看表单
     */
    handleShow(tableId,innerId) {
      tableDataApi.verifyData(tableId,innerId)
        .then(data => {
          this.form = data
          this.formVisible = true
          this.config.value=data.hash
          //this.createQrCode()
       // this.message('TEST')
        })
    },
    qrcode() {
           let qrcode = new QRCode("qrcode", {
             width: 232, // 设置宽度
             height: 232, // 设置高度
             text: "33333"
           });
         }

  },
    mounted() {
      this.$nextTick(function() {
        this.qrcode();
      });
    }
}
</script>
<style>
#qrcode img {
  display: block;
  margin: 0 auto;
}
.el-dialog__body{
  padding:20px 20px;
}
.el-form-item--mini.el-form-item, .el-form-item--small.el-form-item{
  margin-bottom:10px;
}
.el-dialog{
margin-top:10vh;
width:60%;
}
</style>
