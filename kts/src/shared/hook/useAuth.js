import {jwtDecode} from "jwt-decode";
import { useState, useEffect } from "react";

const useAuth = () => {
    const [user, setUser] = useState(null)

    useEffect(() => {
        const token = localStorage.getItem('token');

        if(!token){
            setUser = null
        } else {
            const decodedToken = jwtDecode(token)
            setUser({
                id : decodedToken.id,
                username: decodedToken.username,
                email : decodedToken.sub
            })
        }
    }, [])


    return {user}
}

export default useAuth;