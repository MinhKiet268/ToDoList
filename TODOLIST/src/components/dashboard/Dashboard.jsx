import Sidebar from "../sidebar/Sidebar.jsx";
import PageContainer from "./PageContainer.jsx";
import {useGlobalContext} from "../context/GlobalContext.jsx";
import PopupContainer from "./popuppage/PopupContainer.jsx";
import {useEffect, useState} from "react";
import apiClient from "../../utils/apiClient.jsx";
import {useAuth} from "../authen/AuthProvider.jsx";
import {LuLoaderCircle} from "react-icons/lu";
import LoadingPage from "../../utils/LoadingPage.jsx";

const Dashboard = () => {

    const [isDataLoading, setIsDataLoading] = useState(false);
    const {enablePopup} = useGlobalContext();
    const [data, setData] = useState([null]);
    const [currentData, setCurrentData] = useState(null);

    useEffect(() => {
        setIsDataLoading(true);


        setTimeout(()=>{
            setIsDataLoading(false);
        },500)


    }, []);

    if(isDataLoading) {
        return (<LoadingPage></LoadingPage>)
    }

    return (
            <div className="flex flex-col overflow-x-hidden min-h-screen sm:p-4">
                <div className="flex flex-1 h-full sm:space-x-5">
                    <Sidebar data={data}></Sidebar>
                    <PageContainer data={data} setCurrentData={setCurrentData} />
                    {enablePopup ? (<PopupContainer currentData={currentData} ></PopupContainer>) : null}
                </div>

            </div>
    )
}

export default Dashboard;