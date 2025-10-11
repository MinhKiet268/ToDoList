import apiClient from "../utils/apiClient.jsx";
import { useNavigate } from 'react-router-dom';
import {useAuth} from "./AuthProvider.jsx";


const Sidebar = () => {
    const history = useNavigate();
    const {logout} = useAuth();


    const logoutHandler = async () => {
        try {
            const response = await logout();
            console.log("logout " + response);
            if(response){
                history("/login",{state: response.data});
            }
        } catch (error) {
            console.error("Logout failed: ", error.response ? error.response.data : error.message);
        }
    };


    return (
        <>
            <div className="flex flex-col overflow-y-auto w-0 bg-amber-50 shadow-2 transition-transform -translate-x-full sm:translate-x-0 sm:w-1/6"/>
            <nav className="flex flex-col h-full fixed overflow-y-auto w-0 bg-amber-50 shadow-2 transition-transform -translate-x-full sm:translate-x-0 sm:w-1/6 ">
                <div className="flex items-center p w-full h-20 bg-red-500">
                    <img className=" m-3 rounded-4xl bg-gray-100 h-10 w-10"/>
                    <p>Username</p>
                </div>
                <div className="h-full space-y-3 bg-amber-200 ">
                    <div className="flex items-center justify-center bg-gray-100 h-10">

                    </div>
                    <div className="flex items-center justify-center bg-gray-100 h-10">
                        item 1
                    </div>
                    <div className="flex items-center justify-center bg-gray-100 h-10">
                        item 1
                    </div>
                </div>
                <div className="flex hover:bg-blue-500 justify-center w-full h-15 bg-blue-300">
                    <button onClick={logoutHandler}>
                        Log Out
                    </button>
                </div>
            </nav>
        </>

    )
}

export default Sidebar