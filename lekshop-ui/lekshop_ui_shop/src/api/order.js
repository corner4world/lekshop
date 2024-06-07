import request from '@/utils/request'

export function getOrderList(data) {
  return request({
    url: 'member/order/getList',
    method: 'get',
		params: data
  })
}

export function getOrder(data) {
  return request({
    url: 'member/order/get',
    method: 'get',
		params: data
  })
}

export function delivery(data) {
  return request({
    url: 'member/order/delivery',
    method: 'post',
    data
  })
}

export function getFreightList(data) {
  return request({
    url: 'member/order/getFreightList',
    method: 'get',
		params: data
  })
}

export function getCommentList(data) {
  return request({
    url: 'member/comment/getList',
    method: 'get',
		params: data
  })
}

export function getLeftCommentList(data) {
  return request({
    url: 'member/comment/getLeftList',
    method: 'get',
		params: data
  })
}

export function answerComment(data) {
  return request({
    url: 'member/comment/answer',
    method: 'post',
    data
  })
}

export function deleteLeftComment(data) {
  return request({
    url: 'member/comment/deleteLeftComment',
    method: 'post',
    data
  })
}



export function getRefundList(data) {
  return request({
    url: 'member/refund/getList',
    method: 'get',
		params: data
  })
}

export function getRefund(data) {
  return request({
    url: 'member/refund/get',
    method: 'get',
		params: data
  })
}

export function agreeRefund(data) {
  return request({
    url: 'member/refund/agree',
    method: 'post',
    data
  })
}

export function refuseRefund(data) {
  return request({
    url: 'member/refund/refuse',
    method: 'post',
    data
  })
}

export function confirmReceiveByShop(data) {
  return request({
    url: 'member/refund/confirmReceiveByShop',
    method: 'post',
    data
  })
}

export function getGroupList(data) {
  return request({
    url: 'member/assemble/getList',
    method: 'get',
		params: data
  })
}

export function getGroupMemberList(data) {
  return request({
    url: 'member/assemble/getMemberList',
    method: 'get',
		params: data
  })
}

export function editPrice(data) {
  return request({
    url: 'member/order/editPriceSubmit',
    method: 'post',
    data
  })
}

export function getPriceList(data) {
  return request({
    url: 'member/order/getPriceLog',
    method: 'get',
		params: data
  })
}


export function writeOff(data) {
  return request({
    url: 'member/order/writeOff',
    method: 'post',
    data
  })
}

export function getWriteOffList(data) {
  return request({
    url: 'member/order/getWriteOffList',
    method: 'get',
		params: data
  })
}

export function getOrderMergeList(data) {
  return request({
    url: 'member/orderMerge/getList',
    method: 'get',
		params: data
  })
}

export function getOrderMergeDetail(data) {
  return request({
    url: 'member/orderMerge/getDetail',
    method: 'get',
		params: data
  })
}

export function mergeOrder(data) {
  return request({
    url: 'member/orderMerge/merge',
    method: 'post',
    data
  })
}

export function distributionOrder(data) {
  return request({
    url: 'member/orderMerge/distribution',
    method: 'post',
    data
  })
}

export function sortOutOrder(data) {
  return request({
    url: 'member/orderMerge/sortOut',
    method: 'post',
    data
  })
}

export function cancelMerge(data) {
  return request({
    url: 'member/orderMerge/cancel',
    method: 'post',
    data
  })
}