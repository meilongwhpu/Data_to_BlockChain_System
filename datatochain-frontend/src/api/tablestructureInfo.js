import request from '@/utils/request'

const apiPath = '/tablestructureInfo'
const tablestructureInfoApi = {
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
    return request.get(apiPath, { params: query })
  },
  /**
   * 查询【表结构】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  findTableName(id) {
    return request.get(`${apiPath}/findTableName/${id}`)
  },
  findCreatedTableName(id) {
    return request.get(`${apiPath}/findCreatedTableName/${id}`)
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
  /**
   * 批量删除【表结构】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default tablestructureInfoApi
