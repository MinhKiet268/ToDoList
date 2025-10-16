import React, {useEffect} from 'react';
import {useAuth} from "./AuthProvider.jsx";
import {useNavigate} from "react-router-dom";
import {LuLoaderCircle} from "react-icons/lu";
import LoadingPage from "../../utils/LoadingPage.jsx";



const ProtectedRoute = ({children}) => {
    const {isAuthenticated, loading} = useAuth();
    const history = useNavigate();

    if(loading) {
        return (
            <LoadingPage></LoadingPage>
        )
    }

    if(!isAuthenticated) {
        history("/login", {replace: true});
    }

    return children

}

export default ProtectedRoute