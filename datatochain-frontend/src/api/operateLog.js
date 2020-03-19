import request from '@/utils/request'

const apiPath = '/operateLog'
const operateLogApi = {
  /**
   * 分页查询【操作日志】
   */
  fetchList(query) {
    return request.get(apiPath, { params: query })
  }
}
export default operateLogApi
