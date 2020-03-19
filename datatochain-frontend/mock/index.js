import Mock from 'mockjs'
import { param2Obj } from '../src/utils'

import _user from './_user'
import operateLog from './operateLog'
import appInfo from './appInfo'
import tablespaceInfo from './tablespaceInfo'
import tablestructureInfo from './tablestructureInfo'
import tablefieldInfo from './tablefieldInfo'
import systemDict from './systemDict'

function initMockData() {
  operateLog.initMockDataStage1()
  appInfo.initMockDataStage1()
  tablespaceInfo.initMockDataStage1()
  tablestructureInfo.initMockDataStage1()
  tablefieldInfo.initMockDataStage1()
  systemDict.initMockDataStage1()
  operateLog.initMockDataStage2()
  appInfo.initMockDataStage2()
  tablespaceInfo.initMockDataStage2()
  tablestructureInfo.initMockDataStage2()
  tablefieldInfo.initMockDataStage2()
  systemDict.initMockDataStage2()
}

const reqMocks = [
  ..._user,
  ...operateLog.reqMocks,
  ...appInfo.reqMocks,
  ...tablespaceInfo.reqMocks,
  ...tablestructureInfo.reqMocks,
  ...tablefieldInfo.reqMocks,
  ...systemDict.reqMocks
]

// for front mock
// please use it cautiously, it will redefine XMLHttpRequest,
// which will cause many of your third-party libraries to be invalidated(like progress event).
export function mockXHR() {
  // mock patch
  // https://github.com/nuysoft/Mock/issues/300
  Mock.setup({
    timeout: '100-500'
  })
  Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
  Mock.XHR.prototype.send = function() {
    if (this.custom.xhr) {
      this.custom.xhr.withCredentials = this.withCredentials || false

      if (this.responseType) {
        this.custom.xhr.responseType = this.responseType
      }
    }
    this.proxy_send(...arguments)
  }

  function XHR2ExpressReqWrap(respond) {
    return function(options) {
      const { body, type, url } = options
      console.info(`${type} ${url} ${body ? 'body=' + JSON.stringify(body) : ''}`)
      let result = null
      if (respond instanceof Function) {
        // https://expressjs.com/en/4x/api.html#req
        result = respond({
          method: type,
          body: JSON.parse(body),
          query: param2Obj(url),
          url: url
        })
      } else {
        result = respond
      }
      const mockResult = Mock.mock(result)
      console.info(mockResult)
      return mockResult
    }
  }
  // 初始化所有mock数据
  initMockData()
  // 设置异步请求的mock规则
  for (const i of reqMocks) {
    Mock.mock(new RegExp(i.url), i.type || 'get', XHR2ExpressReqWrap(i.response))
  }
}

// for mock server
const responseFake = (url, type, respond) => {
  return {
    url: new RegExp(`/mock${url}`),
    type: type || 'get',
    response(req, res) {
      res.json(Mock.mock(respond instanceof Function ? respond(req, res) : respond))
    }
  }
}

export default reqMocks.map(route => {
  return responseFake(route.url, route.type, route.response)
})
