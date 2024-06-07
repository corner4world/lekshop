/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const shopRouter = {
  path: '/shop',
  component: Layout,
  name: 'shop',
	alwaysShow: true,
  meta: {
    title: '店铺',
    icon: 'shop-list'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/shop/index/index'),
      name: 'shopList',
      meta: { title: '店铺列表', icon:'', role: ['admin'] }
    },
    {
      path: 'verify',
      component: () => import('@/views/shop/verify/index'),
      name: 'verifyShop',
      meta: { title: '新店铺审核', icon:'', role: ['admin'] }
    },
    {
      path: 'data',
      component: () => import('@/views/shop/data/index'),
      name: 'shopDataList',
      meta: { title: '店铺数据', icon:'', role: ['admin'] }
    },
    {
      path: 'shopService',
      component: () => import('@/views/shop/service/index'),
      name: 'shopService',
      meta: { title: '服务管理', icon:'', role: ['admin'] }
    },
    {
      path: 'freight',
      component: () => import('@/views/shop/freight/index'),
      name: 'freightTemplate',
      meta: { title: '运费模板', icon:'', role: ['admin'] }
    },
  ]
}
export default shopRouter
