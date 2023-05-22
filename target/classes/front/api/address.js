//获取所有Location
function addressListApi() {
    return $axios({
        'url': '/addressBook/list',
        'method': 'get',
    })
}

//获取最新Location
function addressLastUpdateApi() {
    return $axios({
        'url': '/addressBook/lastUpdate',
        'method': 'get',
    })
}

//AddLocation
function addAddressApi(data) {
    return $axios({
        'url': '/addressBook',
        'method': 'post',
        data
    })
}

//ModifyLocation
function updateAddressApi(data) {
    return $axios({
        'url': '/addressBook',
        'method': 'put',
        data
    })
}

//DeleteLocation
function deleteAddressApi(params) {
    return $axios({
        'url': '/addressBook',
        'method': 'delete',
        params
    })
}

//查询单个Location
function addressFindOneApi(id) {
    return $axios({
        'url': `/addressBook/${id}`,
        'method': 'get',
    })
}

//设置默认Location
function setDefaultAddressApi(data) {
    return $axios({
        'url': '/addressBook/default',
        'method': 'put',
        data
    })
}

//获取默认Location
function getDefaultAddressApi() {
    return $axios({
        'url': `/addressBook/default`,
        'method': 'get',
    })
}