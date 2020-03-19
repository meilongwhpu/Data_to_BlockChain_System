import Mock from 'mockjs'
import { paging, copy, getUrlPattern } from './mock-util'

/**
 * mock数据缓存
 */
let data = null

/**
 * 获取mock数据缓存
 */
function getMockData() {
  return data
}

/**
 * 初始化mock数据阶段1
 */
function initMockDataStage1() {
  data = Mock.mock({
    'list|20': [{
      'id|+1': 1,
      'userId': '@word(1, 10)',
      'operTime': '@date(yyyy-MM-dd) 00:00:00',
      'operSubject': '@word(1, 10)',
      'operDetail': '@word(1, 10)',
      'operIp': '@word(1, 10)',
      'remark': '@word(1, 10)'
    }]
  })
}

/**
 * 初始化mock数据阶段2
 */
function initMockDataStage2() {
}

const reqMocks = [
  // 分页查询【操作日志】
  {
    url: getUrlPattern('operateLog', false),
    type: 'get',
    response: ({ query }) => {
      // 列表分页
      const page = paging(data.list, query.page, query.limit)
      return {
        total: data.list.length,
        list: copy(page)
      }
    }
  }
]

export default {
  initMockDataStage1,
  initMockDataStage2,
  reqMocks,
  getMockData
}
