import {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import ErrorProvider from "../errorhandler/ErrorProvider.jsx";
import apiClient from "../../utils/apiClient.jsx";


const RegisterForm = () => {
    const [userDetail, setUserDetail] = useState( {
        username: "",
        password: "",
        fullName: "",
        imageUrl: "None",
        email: "",
        gender: "",
    });
    const [responseMessage,setResponseMessage] = useState("");
    const [showPopup, setShowPopup] = useState(false);
    useEffect(() => {
        let timer;
        if (showPopup) {
            timer = setTimeout(() => {
                setShowPopup(false);
            }, 5000); // 1000 milliseconds = 1 second
        }
        return () => clearTimeout(timer);
    }, [showPopup]);
    const history = useNavigate();
    const [repeatPassword, setRepeatPassword] = useState("");

    const registerHandler = async () => {
        try {
            const newError  = {};
            console.log(userDetail);
            if(!userDetail.username){
                newError.username = "User name is required";
            }
            if(!userDetail.password){
               newError.password = "Password is required";
            }
            if(!userDetail.fullName){
                newError.fullName = "Full name is required";
            }
            if(!userDetail.email){
               newError.email = "Email is required";
            }
            if(!userDetail.gender){
                newError.gender = "Gender is required";
            }
            if(!userDetail.password==repeatPassword){
                newError.gender = "Password does not match";
            }

            const hasError = Object.keys(newError).length > 0;

            if(hasError){
                newError.hasError = hasError;
                console.log("Have Errors !!!");
                setResponseMessage(newError);
                setShowPopup(true);
                return;
            }

            const response = await apiClient.post("http://localhost:8089/api/auth/signup",userDetail);

            if (response.status >= 200 && response.status < 300) {

            }

            console.log(response);
            history("/login",{replace: true});
        } catch (error) {
            setResponseMessage(error.data);
        }
    };
    return (
        <div className="flex flex-1 min-h-screen justify-center items-center">

            {showPopup ? <ErrorProvider formcase="register" props={responseMessage}/> : null}
            <div className="flex flex-col w-full h-screen items-center p-5 bg-gray-400 sm:rounded-2xl sm:shadow-2xl space-y-5 sm:w-140 sm:h-275">
                <h1 className="text-3xl font-audiowide mb-20"><p>Sign up</p></h1>
                <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type="text" placeholder="Username" onChange={(e) => setUserDetail({...userDetail, username: e.target.value})}/>
                <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type="password" placeholder="Password" onChange={(e) => setUserDetail({...userDetail, password: e.target.value})}/>
                <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type="password" placeholder="Repeat the password" onChange={(e) => setRepeatPassword(e.target.value)}/>
                <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type="text" placeholder="Fullname" onChange={(e) => setUserDetail({...userDetail, fullName: e.target.value})}/>
                <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type="text" placeholder="Email" onChange={(e) => setUserDetail({...userDetail, email: e.target.value})}/>
                <input className="bg-gray-100 h-10 w-3/5 p-2 rounded-2xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" type="text" placeholder="Gender" onChange={(e) => setUserDetail({...userDetail, gender: e.target.value})}/>

                <button className="bg-gray-500 pointer  sm:hover:bg-gray-600 sm:hover:shadow-2xl w-50 h-20 rounded-2xl mt-5" onClick={registerHandler}>Sign up</button>
            </div>
        </div>
    )
}

export default RegisterForm;