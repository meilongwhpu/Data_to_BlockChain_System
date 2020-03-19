import request from '@/utils/request'

const apiPath = '/systemDict'
const systemDictApi = {
  /**
   * 新增【系统参数】
   */
  create(data) {
    return request.post(apiPath, data)
  },
  /**
   * 修改【系统参数】
   */
  update(data) {
    return request.put(apiPath, data)
  },
  /**
   * 分页查询【系统参数】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  },
  /**
   * 查看【系统参数】详情
   */
  fetchById(id) {
    return request.get(`${apiPath}/${id}`)
  },
  /**
   * 删除单个【系统参数】
   */
  deleteById(id) {
    return request.delete(`${apiPath}/${id}`)
  },
  /**
   * 批量删除【系统参数】
   */
  deleteBatch(ids) {
    return request.delete(apiPath, { data: ids })
  }
}
export default systemDictApi
