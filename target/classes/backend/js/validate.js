function isValidUsername(str) {
    return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal(path) {
    return /^(https?:|mailto:|tel:)/.test(path);
}

function isCellPhone(val) {
    if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false
    } else {
        return true
    }
}

function checkUserName(rule, value, callback) {
    if (value == "") {
        callback(new Error("Enter User Account"))
    } else if (value.length > 20 || value.length < 3) {
        callback(new Error("The length should be between 3 and 20."))
    } else {
        callback()
    }
}

function checkName(rule, value, callback) {
    if (value == "") {
        callback(new Error("Enter Name"))
    } else if (value.length > 12) {
        callback(new Error("The length should be between 1 and 12"))
    } else {
        callback()
    }
}

function checkPhone(rule, value, callback) {
    if (value == "") {
        callback(new Error("Enter phone number"))
    } else if (!isCellPhone(value)) {
        callback(new Error("Please enter a valid phone number!"))
    } else {
        callback()
    }
}


function validID(rule, value, callback) {
    let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
    if (value == '') {
        callback(new Error('Enter ID number'))
    } else if (reg.test(value)) {
        callback()
    } else {
        callback(new Error('Invalid ID'))
    }
}