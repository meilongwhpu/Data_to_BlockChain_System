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
      'tableId|1-20': 1,
      'fieldName': '@word(1, 10)',
      'fieldDesc': '@word(1, 10)',
      'fieldType|1': [
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9
      ],
      'fieldLength|0-100': 1,
      'fieldDecimalLength|0-100': 1,
      'fieldIsKey|1': [
        0,
        1
      ],
      'allowNull|1': [
        0,
        1
      ],
      'fieldDefaultValue': '@word(1, 10)',
      'creatorId': '@word(1, 10)',
      'tableName': '@word(1, 10)'
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
  // 添加【表字段】
  {
    url: getUrlPattern('tablefieldInfo', false),
    type: 'post',
    response: ({ body }) => {
      body.id = Mock.mock(mockNewIdRule).id
      data.list.push(body)
      return copy(body)
    }
  },
  // 修改【表字段】
  {
    url: getUrlPattern('tablefieldInfo', false),
    type: 'put',
    response: ({ body }) => {
      const obj = data.list.find(item => item.id === body.id)
      Object.assign(obj, body)
      return copy(obj)
    }
  },
  // 分页查询【表字段】
  {
    url: getUrlPattern('tablefieldInfo', false),
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
  // 查看【表字段】详情
  {
    url: getUrlPattern('tablefieldInfo', true),
    type: 'get',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('tablefieldInfo', true))[1]
      const obj = data.list.find(item => item.id === parseInt(id))
      return copy(obj)
    }
  },
  // 删除单个【表字段】
  {
    url: getUrlPattern('tablefieldInfo', true),
    type: 'delete',
    response: ({ url }) => {
      const id = url.match(getUrlPattern('tablefieldInfo', true))[1]
      removeById(data.list, parseInt(id))
      return 1
    }
  },
  // 批量删除【表字段】
  {
    url: getUrlPattern('tablefieldInfo', false),
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
