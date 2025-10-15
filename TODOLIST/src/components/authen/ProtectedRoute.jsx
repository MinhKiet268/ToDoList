import React, {useEffect} from 'react';
import {useAuth} from "./AuthProvider.jsx";
import {useNavigate} from "react-router-dom";



const ProtectedRoute = ({children}) => {
    const {isAuthenticated, loading} = useAuth();
    const history = useNavigate();

    useEffect(() => {

        console.log(isAuthenticated + " | " + loading);
        if (!loading && !isAuthenticated) {
            history("/login", {replace: true});
        }
    },[isAuthenticated,loading, history]);

    // if(loading){
    //     return (
    //         <div className="flex h-screen items-center text-xl backdrop-blur-2xl backdrop-opacity-10">Loading</div>
    //     )
    // }

    return isAuthenticated ? children : null;
}

export default ProtectedRoute