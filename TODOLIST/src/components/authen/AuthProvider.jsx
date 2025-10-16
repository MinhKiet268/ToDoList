import React, { useState, createContext, useContext, useEffect } from 'react';
import apiClient from "../../utils/apiClient.jsx";



const AuthContext = createContext(null);

export const useAuth = () => useContext(AuthContext)

const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(false);
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        if (localStorage.getItem("isAuthenticated")) {
            setIsAuthenticated(true);
            setUser(localStorage.getItem("user"));
            setLoading(false);
        }
    },[])

    const login = async (username, password) => {
        setLoading(true);
        try {
            const response = await apiClient.post("/api/auth/signin",
                {
                    username: username,
                    password: password
                }
            );
            if(response.status >= 200 && response.status < 300) {
                setUser(response.data.user);
                setIsAuthenticated(true);
                localStorage.setItem("user", response.data.user);
                localStorage.setItem("isAuthenticated", true);
                setLoading(false);
                return true;
            }else {
                //throw new Error(response.date.username + "Unexpected status code");
                return false;
            }
        } catch (error) {
            setIsAuthenticated(false);

            let errorMessage = "An unknown error occurred.";

            if(error.response)
            {
                errorMessage = error.response.data.message || 'login failed:' + error.response.statusText;
            }
            return false;
        }

    }

    const logout = async () => {
        try {
            const response = await apiClient.post("/api/auth/signout");
            if(response.status >= 200 && response.status < 300) {
                setIsAuthenticated(false);
                setLoading(false);
                localStorage.clear();
                return true;
            }
            return false;
        } catch (error) {
            console.log(error);
            return false
        }

    }


    return (
        <AuthContext.Provider value={{user,loading, setLoading,isAuthenticated,login,logout}}>
            {children}
        </AuthContext.Provider>
    );
}

export default AuthProvider;