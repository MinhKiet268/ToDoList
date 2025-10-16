import Sidebar from "../sidebar/Sidebar.jsx";
import PageContainer from "./PageContainer.jsx";
import {useGlobalContext} from "../context/GlobalContext.jsx";
import PopupContainer from "./popuppage/PopupContainer.jsx";

const Dashboard = () => {

    const {enablePopup} = useGlobalContext();

    return (
            <div className="flex flex-col overflow-x-hidden min-h-screen sm:p-4">
                <div className="flex flex-1 h-full sm:space-x-5">
                    <Sidebar></Sidebar>
                    <PageContainer setIs/>
                    {enablePopup ? (<PopupContainer></PopupContainer>) : null}
                </div>

            </div>
    )
}

export default Dashboard;