import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/_login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [{
      path: 'home',
      name: 'home',
      component: () => import('@/views/_home/index'),
      meta: { title: '首页', icon: 'home' }
    }]
  },
  {
    path: '/operateLog',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'OperateLog',
        component: () => import('@/views/operateLog'),
        meta: { title: '操作日志管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/appInfo',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'AppInfo',
        component: () => import('@/views/appInfo'),
        meta: { title: '应用管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/tablespaceInfo',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'TablespaceInfo',
        component: () => import('@/views/tablespaceInfo'),
        meta: { title: '表空间管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/tablestructureInfo',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'TablestructureInfo',
        component: () => import('@/views/tablestructureInfo'),
        meta: { title: '表结构管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/tablefieldInfo',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'TablefieldInfo',
        component: () => import('@/views/tablefieldInfo'),
        meta: { title: '表字段管理', icon: 'table' }
      }
    ]
  },
{
  path: '/tabledataInfo',
    component: Layout,
  children: [
  {
    path: 'index',
    name: 'TabledataInfo',
    component: () => import('@/views/tabledata'),
  meta: { title: '表数据管理', icon: 'table' }
}
]
},
  {
    path: '/systemDict',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'SystemDict',
        component: () => import('@/views/systemDict'),
        meta: { title: '系统参数管理', icon: 'table' }
      }
    ]
  },
{
  path: '/tableData',
  name:'tableData',
    component: () => import('@/views/tabledata/index.vue'),
  hidden: true
},
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
