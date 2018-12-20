'use strict'
require('./check-versions')()

// 设置当前环境为生产环境
process.env.NODE_ENV = 'production'

// loading 插件
// https://github.com/sindresorhus/ora
const ora = require('ora')
// 可以在 node 中执行`rm -rf`的工具
// https://github.com/isaacs/rimraf
const rm = require('rimraf')
// node自带的文件路径工具
const path = require('path')
// 在终端输出带颜色的文字
// https://github.com/chalk/chalk
const chalk = require('chalk')
const webpack = require('webpack')
// 配置文件
const config = require('../config')
const webpackConfig = require('./webpack.prod.conf')

// 在终端显示loading效果，并输出提示
const spinner = ora('building for production...')
spinner.start()

// 删除这个文件夹 （递归删除）
rm(path.join(config.build.assetsRoot, config.build.assetsSubDirectory), err => {
  if (err) throw err

  // 构建
  webpack(webpackConfig, (err, stats) => {
    // 构建成功

    // 停止 loading动画
    spinner.stop()
    if (err) throw err
    process.stdout.write(
      stats.toString({
        colors: true,
        modules: false,
        children: false,
        chunks: false,
        chunkModules: false
      }) + '\n\n'
    )

    if (stats.hasErrors()) {
      console.log(chalk.red('  Build failed with errors.\n'))
      process.exit(1)
    }

    // 打印提示
    console.log(chalk.cyan('  Build complete.\n'))
    console.log(
      chalk.yellow(
        '  Tip: built files are meant to be served over an HTTP server.\n' +
          "  Opening index.html over file:// won't work.\n"
      )
    )
  })
})
