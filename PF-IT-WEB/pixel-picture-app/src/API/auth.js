const tokenKey = 'JWT';

export const setToken = token => {
    localStorage.setItem(tokenKey, token);
}

export const getToken = () => localStorage.getItem(tokenKey);

export const deleteToken = () => {
    localStorage.removeItem(tokenKey)
}

const fetchWithAuthorization = async (url, options = {}) => {
    const headers = {
      ...options.headers,
      Authorization: `Bearer ${getToken()}`,
    };
  
    const response = await fetch(url, { ...options, headers });
    return response;
  };

export const closeSession = () => {
    // Limpiar todo el localStorage
    localStorage.clear();
    window.location.href = "/"
}