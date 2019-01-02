import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon' // svg组件

// 下载地址 https://www.iconfont.cn/
// register globally
Vue.component('svg-icon', SvgIcon)

const requireAll = requireContext => requireContext.keys().map(requireContext)
const svgFiles = require.context('./svg', false, /\.svg$/)
requireAll(svgFiles)

const iconList = svgFiles.keys().map(item => svgFiles(item))

export default {
  // 获取图标icon-(*).svg名称列表, 例如[shouye, xitong, zhedie, ...]
  getNameList () {
    return iconList.map(item => item.default.id.split('-')[1])
  }
}
