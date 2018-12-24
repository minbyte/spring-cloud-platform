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
      'client_id': 'webApp',
      'client_secret': '$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72',
      'scope': 'webApp'
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
    url: '/admins/admin/logout',
    method: 'post'
  })
}
