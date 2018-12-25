import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets CSS重置的通常替代方案

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control 权限控制
import { isAuth } from '@/utils'
import request from '@/utils/request'

// 使用ElementUI组件，并传入locale国际化参数
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

// 挂载全局
Vue.prototype.isAuth = isAuth // 权限方法
Vue.prototype.$http = request // ajax请求方法

// 创建和挂载根实例。
// 通过 router 配置参数注入路由，从而让整个应用都有路由功能
//
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
// 现在，应用已经启动了！
