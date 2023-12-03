export const setToken = (token) => {
    localStorage.setItem('jwtToken', token);
};

export const setUsername = (username) => {
    localStorage.setItem('username', username);
}

export const setFullName = (fullName) => {
    localStorage.setItem('userFullName', fullName);
}

export const setRole = (role) => {
    localStorage.setItem('userRole', role);
}

export const getToken = () => {
    return localStorage.getItem('jwtToken');
};

export const getUsername = () => {
    return localStorage.getItem('username');
}

export const getFullName = () => {
    return localStorage.getItem('userFullName');
}

export const getRole = () => {
    return localStorage.getItem('userRole');
}

export const clearToken = () => {
    return localStorage.clear();
}

export const checkInitialLoginState = () => {
    const token = getToken();
    return token !== null;
}
