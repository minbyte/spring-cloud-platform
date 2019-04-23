import request from '@/utils/request'
const querystring = require('querystring')

export function login(username, password) {
  return request({
    url: '/security/oauth/admin/login',
    method: 'post',
    headers: { 'client_id': 'console', 'client_secret': 'console' },
    data: querystring.stringify({
      username,
      password
    })
  })
}

export function getInfo(access_token) {
  return request({
    url: '/admins/admin/current',
    method: 'get',
    params: { access_token }
  })
}

export function logout() {
  return request({
    url: '/security/oauth/logout',
    method: 'post'
  })
}

export function loadMenu() {
  return request({
    url: '/admins/menu/nav',
    method: 'get'
  })
}
