import Sidebar from "./Sidebar.jsx";
import TaskContainer from "./TaskContainer.jsx";
import PopupForm from "./PopupForm.jsx";
import {useEffect, useState} from "react";
import { useNavigate} from 'react-router-dom';
import apiClient from "../utils/apiClient.jsx";
import taskContainer from "./TaskContainer.jsx";

const Dashboard = () => {
    const [showPopup, setShowPopup] = useState(false);
    const history = useNavigate()
    const [user, setUser] = useState({});
    const [loading, setLoading] = useState(true);
    const [responseMessage,setResponseMessage] = useState("");
    useEffect(() => {

        const axiosData = async () => {
            const newError = {};
            try {

                const response = await apiClient.post("/api/todolist");
                if (response.status === 403) {
                    history("/login");
                    return;
                }
                if (!response.status === 200) {
                    console.error("Error: " + response.status);
                    return;
                }
                history("/dashboard", {state: response.data});
            } catch (error) {
                newError.error = error;
                setResponseMessage(newError);
                console.error("Login failed: ", error.response ? error.response.data : error.message);
            }
        }

        axiosData();

    }, []);
    useEffect(() => {
        let timer;
        if (showPopup) {
            timer = setTimeout(() => {
                setShowPopup(false);
            }, 50000); // 1000 milliseconds = 1 second
        }
        return () => clearTimeout(timer);
    }, [showPopup]);



    return (
        <>
            {showPopup ? <PopupForm formcase="register" props={location.state}/> : null}
            <div className="flex flex-col min-h-screen">

                <div className="flex flex-1">
                    <Sidebar></Sidebar>
                    <TaskContainer props={loading}/>
                </div>

            </div>
        </>

    )
}

export default Dashboard;