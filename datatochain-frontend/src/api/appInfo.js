import request from '@/utils/request'

const apiPath = '/appInfo'
const appInfoApi = {
  /**
   * 新增【应用】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【应用】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【应用】
   */
  fetchList(query) {
      return request.get(apiPath, { params: query })
  },
  /**
   * 查询【应用】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  /**
   * 查看【应用】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【应用】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【应用】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  },
  /**
   * 获取【表空间】关联
   */
  fetchTablespaceInfoList(id) {
    return request.get(`${apiPath}/${id}/tablespaceInfo`)
  },
  /**
   * 添加【表空间】关联
   */
  addTablespaceInfo(id, data) {
    return request.post(`${apiPath}/${id}/tablespaceInfo`, data)
  },
  /**
   * 移除【表空间】关联
   */
  removeTablespaceInfo(id, data) {
    return request.delete(`${apiPath}/${id}/tablespaceInfo`, { data })
  }
}
export default appInfoApi
