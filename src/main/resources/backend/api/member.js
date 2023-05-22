// 查询---加载员工列表
function getMemberList(params) {
    return $axios({
        url: '/employee/page',
        method: 'get',
        params
    })
}

// Modify---启用BAN接口
function enableOrDisableEmployee(params) {
    return $axios({
        url: '/employee',
        method: 'put',
        data: {...params}
    })
}

// Add---ADD NEW
function addEmployee(params) {
    return $axios({
        url: '/employee',
        method: 'post',
        data: {...params}
    })
}

// Modify---ADD NEW
function editEmployee(params) {
    return $axios({
        url: '/employee',
        method: 'put',
        data: {...params}
    })
}

// ModifyPage面反查详情接口
function queryEmployeeById(id) {
    return $axios({
        url: `/employee/${id}`,
        method: 'get'
    })
}