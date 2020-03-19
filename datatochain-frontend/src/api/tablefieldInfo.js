import request from '@/utils/request'

const apiPath = '/tablefieldInfo'
const tablefieldInfoApi = {
  /**
   * 新增【表字段】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【表字段】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【表字段】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查看【表字段】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【表字段】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【表字段】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default tablefieldInfoApi
