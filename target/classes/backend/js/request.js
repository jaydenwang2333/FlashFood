(function (win) {
    axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
    const service = axios.create({
        baseURL: '/',
        timeout: 100000  
    })
    service.interceptors.request.use(config => {
       
        if (config.method === 'get' && config.params) {
            let url = config.url + '?';
            for (const propName of Object.keys(config.params)) {
                const value = config.params[propName];
                var part = encodeURIComponent(propName) + "=";
                if (value !== null && typeof (value) !== "undefined") {
                    if (typeof value === 'object') {
                        for (const key of Object.keys(value)) {
                            let params = propName + '[' + key + ']';
                            var subPart = encodeURIComponent(params) + "=";
                            url += subPart + encodeURIComponent(value[key]) + "&";
                        }
                    } else {
                        url += part + encodeURIComponent(value) + "&";
                    }
                }
            }
            url = url.slice(0, -1);
            config.params = {};
            config.url = url;
        }
        return config
    }, error => {
        console.log(error)
        Promise.reject(error)
    })

    service.interceptors.response.use(res => {
            if (res.data.code === 0 && res.data.msg === 'NOTLOGIN') {
                console.log('---/backend/page/login/login.html---')
                localStorage.removeItem('userInfo')
                window.top.location.href = '/backend/page/login/login.html'
            } else {
                return res.data
            }
        },
        error => {
            console.log('err' + error)
            let {message} = error;
            if (message == "Network Error") {
                message = "Back-end interface connection exception";
            } else if (message.includes("timeout")) {
                message = "Interface connection overtime";
            } else if (message.includes("Request failed with status code")) {
                message = "Interface" + message.substr(message.length - 3) + "error";
                // message = "Process failed";
            }
            window.ELEMENT.Message({
                message: message,
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject(error)
        }
    )
    win.$axios = service
})(window);
