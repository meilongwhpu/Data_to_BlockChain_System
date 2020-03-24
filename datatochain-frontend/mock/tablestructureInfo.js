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
      'tableName': '@word(1, 10)',
      'tableDesc': '@word(1, 10)',
      'createTime': '@date(yyyy-MM-dd) 00:00:00',
      'updateTime': '@date(yyyy-MM-dd) 00:00:00',
      'creatorId': '@word(1, 10)',
      'isCreate|1': [
        0,
        1
      ],
      'tablespaceName': '@word(1, 10)'
    }]
  })
}

/**
 * 初始化mock数据阶段2
 */
function initMockDataStage2() {
}

/**
 * 新的id生成规则
 */
const mockNewIdRule = {
  'id|+1': 21
}

function removeById(list, id) {
  const index = list.findIndex(item => item.id === id)
  list.splice(index, 1)
}

const reqMocks = [
  // 添加【表结构】
  {
    url: getUrlPattern('tablestructureInfo', false),
    type: 'post',
    response: ({ body }) => {
      body.id = Mock.mock(mockNewIdRule).id
      data.list.push(body)
      return copy(body)
    }
  },
  // 修改【表结构】
  {
    url: getUrlPattern('tablestructureInfo', false),
    type: 'put',
    response: ({ body }) => {
      const obj = data.list.find(item => item.id === body.id)
      Object.assign(obj, body)
      return copy(obj)
    }
  },
  // 分页查询【表结构】
  {
    url: getUrlPattern('tablestructureInfo', false),
    type: 'get',
    response: ({ query }) => {
      // 列表分页
      const page = paging(data.list, query.page, query.limit)
      return {
        total: data.list.length,
        list: copy(page)
      }
    }
  },
  // 查询【表结构】选项列表
  {
    url: getUrlPattern('tablestructureInfo', false, 'options'),
    type: 'get',
    response: () => {
      return data.list.map(item => ({
        key: item.id,
        value: item.tableName
      }))
    }
  },
  // 查看【表结构】详情
  {
    url: getUrlPattern('tablestructureInfo', true),
    type: 'get',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('tablestructureInfo', true))[1]
      const obj = data.list.find(item => item.id === parseInt(id))
      return copy(obj)
    }
  },
  // 删除单个【表结构】
  {
    url: getUrlPattern('tablestructureInfo', true),
    type: 'delete',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('tablestructureInfo', true))[1]
      removeById(data.list, parseInt(id))
      return 1
    }
  },
  // 批量删除【表结构】
  {
    url: getUrlPattern('tablestructureInfo', false),
    type: 'delete',
    response: ({ body }) => {
      for (var id of body) {
        removeById(data.list, id)
      }
      return body.length
    }
  }
]

export default {
  initMockDataStage1,
  initMockDataStage2,
  reqMocks,
  getMockData
}
