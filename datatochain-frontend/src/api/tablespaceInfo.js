import request from '@/utils/request'

const apiPath = '/tablespaceInfo'
const tablespaceInfoApi = {
  /**
   * 新增【表空间】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【表空间】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【表空间】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查询【表空间】选项列表
   */
  findOptions(query) {
    return request.get(`${apiPath}/options`, { params: query })
  },
  /**
   * 查看【表空间】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【表空间】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【表空间】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default tablespaceInfoApi
