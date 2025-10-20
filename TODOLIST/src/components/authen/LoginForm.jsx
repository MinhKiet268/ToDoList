import {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import apiClient from "../../utils/apiClient.jsx";
import {useAuth} from "./AuthProvider.jsx";


const LoginForm = () => {
    const {login} = useAuth();
    const [userDetail, setUserDetail] = useState( {
        username: "",
        password: "",
    });
    const history = useNavigate();

    useEffect(() => {
        console.log("authen " + localStorage.getItem("isAuthenticated"));
        if (localStorage.getItem("isAuthenticated")) {
            history("/dashboard");
        }
    })

    const loginHandler = async () => {
        const response = await login(userDetail.username, userDetail.password);
        if (response) {
            history("/dashboard");
            console.log("login success");
        }
    }

    // const loginHandler = async () => {
    //     try {
    //         console.log(userDetail);
    //         if(!userDetail.username || !userDetail.password){
    //             setError('Please enter a both username and password');
    //             return;
    //         }
    //         const response = await apiClient.post("/api/auth/signin",userDetail);
    //
    //         console.log(response);
    //         history("/dashboard",{state: response.data});
    //     } catch (error) {
    //         console.error("Login failed: ", error.response ? error.response.data : error.message);
    //         setError("Invalid username or password");
    //     }
    // };
    if(!localStorage.getItem("isAuthenticated")) {
        return (

            <div className="flex flex-1 min-h-screen justify-center items-center">
                <div className="flex flex-col w-full h-screen items-center p-5 bg-gray-400 sm:rounded-2xl sm:shadow-2xl space-y-5 sm:w-140 sm:h-275">
                    <h1 className="text-3xl font-audiowide mb-20"><p>Login</p></h1>
                    <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type={"text"} placeholder={"Username"} onChange={(e) => setUserDetail({...userDetail, username: e.target.value})}/>
                    <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type={"password"} placeholder={"Username"} onChange={(e) => setUserDetail({...userDetail, password: e.target.value})}/>
                    <div className="w-6/10">
                        <a className="text-blue-950" href="/register">Sign up here</a>
                    </div>
                    <button className="bg-gray-500 hover:bg-gray-600 hover:shadow-2xl w-50 h-20 rounded-2xl mt-5" onClick={loginHandler}>Login</button>

                </div>

            </div>
        )
    }

}

export default LoginForm;