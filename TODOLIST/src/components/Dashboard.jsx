import Sidebar from "./Sidebar.jsx";
import TaskContainer from "./TaskContainer.jsx";
import {useEffect, useState} from "react";
import { useNavigate} from 'react-router-dom';
import apiClient from "../utils/apiClient.jsx";

const Dashboard = () => {
    const history = useNavigate()

    useEffect(() => {

        const axiosData = async () => {
            const newError = {};
            try {
                const response = await apiClient.get("/api/app/get");
            } catch (error) {
                newError.error = error;
                console.error("Login failed: ", error.response ? error.response.data : error.message);
            }
        }
        axiosData();

    }, []);



    return (
            <div className="flex flex-col min-h-screen">

                <div className="flex flex-1">
                    <Sidebar></Sidebar>
                    <TaskContainer/>
                </div>

            </div>
    )
}

export default Dashboard;