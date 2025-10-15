import Sidebar from "../sidebar/Sidebar.jsx";
import PageContainer from "./PageContainer.jsx";
import {useEffect, useState} from "react";
import { useNavigate} from 'react-router-dom';
import apiClient from "../../utils/apiClient.jsx";

const Dashboard = () => {

    return (
            <div className="flex flex-col min-h-screen sm:p-4">
                <div className="flex flex-1 h-full sm:space-x-5">
                    <Sidebar
                    ></Sidebar>
                    <PageContainer/>
                </div>

            </div>
    )
}

export default Dashboard;