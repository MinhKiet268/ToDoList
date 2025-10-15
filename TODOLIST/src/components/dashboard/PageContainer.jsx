import Header from "./Header.jsx";
import {useEffect, useState} from "react";
import apiClient from "../../utils/apiClient.jsx";
import UpcomingPage from "./page/UpcomingPage.jsx";
import TodayPage from "./page/TodayPage.jsx";
import CalendarPage from "./page/CalendarPage.jsx";
import {useGlobalContext} from "../context/GlobalContext.jsx";

const PageContainer = () => {
    const {currentPage} = useGlobalContext();


    const renderPage = (currentPage) => {
        switch (currentPage) {
            case 'Upcoming':
                return (
                    <UpcomingPage></UpcomingPage>
                )
            case 'Today':
                return (
                    <TodayPage></TodayPage>
                )
            case 'Calendar':
                return (
                    <CalendarPage></CalendarPage>
                )
        }
    }


    // useEffect(() => {
    //     const [data,setData] = useState({});
    //     const axiosData = async () => {
    //         const newError = {};
    //         try {
    //             const response = await apiClient.get("/api/app/get");
    //         } catch (error) {
    //             newError.error = error;
    //             console.error("Login failed: ", error.response ? error.response.data : error.message);
    //         }
    //
    //     }
    // }, []);



    return (
        <div className=" flex flex-col flex-1 space-y-3 h-screen">
            <div className="flex flex-col h-full transition-all duration-300">
                <h1 className="text-5xl font-extrabold pl-5 font-roboto text-black mb-8">{currentPage}</h1>
                {
                    renderPage(currentPage)
                }
            </div>
        </div>
    )
}

export default PageContainer