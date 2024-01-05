import request from '@/utils/request'

/**
 * 查询数据列表
 * @param data
 * @param page
 * @returns {AxiosPromise}
 */
export function list(data, page) {
  return request({
    url: '/emp/list',
    method: 'post',
    data,
    params: page
  })
}

/**
 * 编辑数据列表
 * @param data
 * @returns {AxiosPromise}
 */
export function edit(data) {
  return request({
    url: '/emp/edit',
    method: 'post',
    data
  })
}

/**
 * 删除数据列表
 * @param data
 * @returns {AxiosPromise}
 */
export function deleteData(data) {
  return request({
    url: `/emp/delete/${data}`,
    method: 'post',
    data
  })
}
