// Query List Interface
const getCategoryPage = (params) => {
    return $axios({
        url: '/category/page',
        method: 'get',
        params
    })
}

// Interface to edit page counter-check details
const queryCategoryById = (id) => {
    return $axios({
        url: `/category/${id}`,
        method: 'get'
    })
}

// Delete the current front interface
const deleCategory = (id) => {
    return $axios({
        url: '/category',
        method: 'delete',
        params: {id}
    })
}

// Modify the interface
const editCategory = (params) => {
    return $axios({
        url: '/category',
        method: 'put',
        data: {...params}
    })
}

// New interface
const addCategory = (params) => {
    return $axios({
        url: '/category',
        method: 'post',
        data: {...params}
    })
}