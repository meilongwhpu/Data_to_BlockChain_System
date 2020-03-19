import Mock from 'mockjs'
import { paging, copy, getUrlPattern } from './mock-util'
import tablespaceInfo from './tablespaceInfo'

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
      'appName': '@word(1, 10)',
      'appDesc': '@word(1, 10)',
      'appKey': '@word(1, 10)',
      'createTime': '@date(yyyy-MM-dd) 00:00:00',
      'updateTime': '@date(yyyy-MM-dd) 00:00:00',
      'creatorId': '@word(1, 10)'
    }]
  })
}

/**
 * 初始化mock数据阶段2
 */
function initMockDataStage2() {
  for (const item of data.list) {
    item.tablespaceInfoList = [Mock.Random.pick(tablespaceInfo.getMockData().list)]
  }
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
  // 添加【应用】
  {
    url: getUrlPattern('appInfo', false),
    type: 'post',
    response: ({ body }) => {
      body.id = Mock.mock(mockNewIdRule).id
      data.list.push(body)
      return copy(body)
    }
  },
  // 修改【应用】
  {
    url: getUrlPattern('appInfo', false),
    type: 'put',
    response: ({ body }) => {
      const obj = data.list.find(item => item.id === body.id)
      Object.assign(obj, body)
      return copy(obj)
    }
  },
  // 分页查询【应用】
  {
    url: getUrlPattern('appInfo', false),
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
  // 查询【应用】选项列表
  {
    url: getUrlPattern('appInfo', false, 'options'),
    type: 'get',
    response: () => {
      return data.list.map(item => ({
        key: item.id,
        value: item.appName
      }))
    }
  },
  // 查看【应用】详情
  {
    url: getUrlPattern('appInfo', true),
    type: 'get',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('appInfo', true))[1]
      const obj = data.list.find(item => item.id === parseInt(id))
      return copy(obj)
    }
  },
  // 删除单个【应用】
  {
    url: getUrlPattern('appInfo', true),
    type: 'delete',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('appInfo', true))[1]
      removeById(data.list, parseInt(id))
      return 1
    }
  },
  // 批量删除【应用】
  {
    url: getUrlPattern('appInfo', false),
    type: 'delete',
    response: ({ body }) => {
      for (var id of body) {
        removeById(data.list, id)
      }
      return body.length
    }
  },
  // 获取【表空间】关联
  {
    url: getUrlPattern('appInfo', true, 'tablespaceInfo'),
    type: 'get',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('appInfo', true, 'tablespaceInfo'))[1]
      const obj = data.list.find(item => item.id === parseInt(id))
      return copy(obj.tablespaceInfoList)
    }
  },
  // 添加【表空间】关联
  {
    url: getUrlPattern('appInfo', true, 'tablespaceInfo'),
    type: 'post',
    response: ({ url, body }) => {
      const id = url.match(getUrlPattern('appInfo', true, 'tablespaceInfo'))[1]
      const obj = data.list.find(item => item.id === parseInt(id))
      tablespaceInfo.getMockData().list
        // 过滤出需要添加的
        .filter(i => body.findIndex(j => j === i.id) > -1)
        // 过滤掉之前已经存在的
        .filter(i => obj.tablespaceInfoList.findIndex(j => j === i) < 0)
        .forEach(i => obj.tablespaceInfoList.push(i))
      return body.length
    }
  },
  // 移除【表空间】关联
  {
    url: getUrlPattern('appInfo', true, 'tablespaceInfo'),
    type: 'delete',
    response: ({ url, body }) => {
      const id = url.match(getUrlPattern('appInfo', true, 'tablespaceInfo'))[1]
      const obj = data.list.find(item => item.id === parseInt(id))
      body.forEach(i => {
        const index = obj.tablespaceInfoList.findIndex(j => j.id === i)
        if (index > -1) {
          obj.tablespaceInfoList.splice(index, 1)
        }
      })
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
