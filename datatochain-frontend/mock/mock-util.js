const baseURL = process.env.VUE_APP_BASE_API

export function paging(list, page, limit) {
  const endIndex = page * limit
  const startIndex = (page - 1) * limit
  return list.slice(startIndex, endIndex)
}

export function copy(obj) {
  return JSON.parse(JSON.stringify(obj))
}

export function getUrlPattern(module, withId, suffix) {
  let pattern = `\\${baseURL}\\/${module}`
  if (withId) {
    pattern += `\\/(\\d+)`
  }
  if (suffix) {
    pattern += `\\/${suffix}`
  }
  pattern += `($|\\?)`
  return new RegExp(pattern)
}
