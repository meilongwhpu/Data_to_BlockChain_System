import request from '@/utils/request'

const apiPath = '/tableData'
const tableDataApi = {
  /**
   * 新增【表结构】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【表结构】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【表结构】
   */
  fetchList(query) {
    console.log(query);
    return request.get(apiPath, { params: query })
  },

  fetchTableHeader(id){
    return request.get(`${apiPath}/getTableHeader/${id}`)
  },
  addTableValue(data){
    console.log(data);
    return request.post(`${apiPath}/save`, data)
  },
  /**
   * 查询【表结构】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  /**
   * 查看【表结构】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【表结构】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 创建表结构【表结构】
   */
  createTable(tableId){
    return request.put(`${apiPath}/createTable/${tableId}`)
  },
  verifyData(tableId,innerId){
    return request.get(`${apiPath}/verifyData/${tableId}/${innerId}`)
  },
  getTableInfo(tableId){
    return request.get(`${apiPath}/getTableInfo/${tableId}`)
  },
  /**
   * 批量删除【表结构】
   */
  deleteBatch(tableId,ids) {
    return request.delete(`${apiPath}/deleteData/${tableId}`, { data: ids })
  }
}
export default tableDataApi
