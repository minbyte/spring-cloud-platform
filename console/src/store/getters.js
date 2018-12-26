const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token, // 登录token
  avatar: state => state.user.avatar, // 用户头像
  name: state => state.user.name, // 用户昵称
  roles: state => state.user.roles, // 用户角色
  permissions: state => state.user.permissions, // 权限
  dynamicRouters: state => state.user.dynamicRouters // 动态路由
}
export default getters
