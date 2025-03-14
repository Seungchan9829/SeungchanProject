import {jwtDecode} from "jwt-decode";

export const saveTokenLocalStorage = (token) => {

    if(validateToken(token)){
        localStorage.setItem("token",token)
        return true
    }
    else {
        return false
    }
}

export const validateToken = (token) => {
    if(!token) {
        return false
    }

    const decodedToken = jwtDecode(token);
    const currentTime = Math.floor(Date.now() / 1000);

    try {  
        if (decodedToken.exp) {
            if(decodedToken.exp < currentTime) {
                return false
            }
        }
        else {
            return false;
        }

        return true

    } catch(error) {
        return false;
    }
}

export const getUserIdByToken = () => {
    const token = localStorage.getItem("token")

    if(validateToken(token)) {
        const decodedToken = jwtDecode(token);
        const userId = decodedToken.id;
        return userId
    } else {
        return null
    }
}