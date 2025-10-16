import { IoChevronBackOutline, } from "react-icons/io5";
import { useNavigate } from 'react-router-dom';
import {useAuth} from "../authen/AuthProvider.jsx";
import ListNavItem from "./ListNavItem.jsx";
import {useState} from "react";
import {useGlobalContext} from "../context/GlobalContext.jsx";


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
            <div className="flex flex-col overflow-y-auto w-0 bg-color-graycustom shadow-2 transition-transform -translate-x-full sm:translate-x-0 sm:min-w-90 sm:rounded-xl">
                <nav className=" flex flex-col h-[calc(100vh-2rem)] overflow-y-auto w-0 fixed p-4 bg-graycustom transition-transform -translate-x-full sm:translate-x-0 sm:min-w-full ">
                    <div className="flex justify-between items-center w-full h-20">
                        <p className="text-3xl font-roboto text-fontgray font-bold ">Menu</p>
                        <div className="flex flex-1bg-whitecustom hover:bg-graycustom2 rounded-md h-10 w-10 items-center justify-center">
                            <button className="flex w-full h-full justify-center items-center rounded-md"><IoChevronBackOutline className="w-full h-full"/></button>
                        </div>
                    </div>
                    <div className="h-full space-y-3  ">
                        <ListNavItem title="Tasks"></ListNavItem>
                    </div>
                    <div className=" group flex justify-center w-full rounded-md h-15 bg-graycustom2 hover:bg-grayclick">
                        <button className=" group-hover:text-white" onClick={logoutHandler}>
                            Log Out
                        </button>
                    </div>
                </nav>
            </div>

        </>

    )
}

export default Sidebar