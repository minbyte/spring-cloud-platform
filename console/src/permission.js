import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // 验权

const whiteList = ['/login'] // 不重定向白名单

/**
 * 全局的导航守卫
 * to: Route: 即将要进入的目标 路由对象
 * from: Route: 当前导航正要离开的路由
 * next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
 */
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    // 如果已经有token
    if (store.getters.dynamicRouters.length < 1) {
      // 如果加载动态路由，则重新加载
      store.dispatch('LoadMenu').then(res => { // 拉取用户信息
        router.addRoutes(store.getters.dynamicRouters)
      }).catch((err) => {
        store.dispatch('FedLogOut').then(() => {
          Message.error(err || 'LoadMenu failed, please login again')
          next({ path: '/' })
        })
      })
    }
    if (to.path === '/login') {
      // 访问的是登录页，则定向到首页
      next({ path: '/' })
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      // 其他页面，看是否有用户信息
      if (store.getters.roles.length === 0) {
        // 没有用户信息的，拉取用户信息
        store.dispatch('GetInfo').then(res => { // 拉取用户信息
          next()
        }).catch((err) => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || 'Verification failed, please login again')
            next({ path: '/' })
          })
        })
      } else {
        // 有用户信息的，跳转下一个页面。
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在白名单里面的，放行
      next()
    } else {
      // 否则全部重定向到登录页
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
