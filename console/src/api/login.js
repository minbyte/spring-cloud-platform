import request from '@/utils/request'
const querystring = require('querystring')

export function login(username, password) {
  return request({
    url: '/security/oauth/token',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
    data: querystring.stringify({
      username,
      password,
      'grant_type': 'password',
      'client_id': 'app',
      'client_secret': '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO',
      'scope': 'app'
    })
  })
}

export function getInfo(access_token) {
  return request({
    url: '/admins/admin/info',
    method: 'get',
    params: { access_token }
  })
}

export function logout() {
  return request({
    url: '/admins/admin/logout',
    method: 'post'
  })
}
