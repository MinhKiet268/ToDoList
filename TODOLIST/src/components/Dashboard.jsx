import Sidebar from "./Sidebar.jsx";
import TaskContainer from "./TaskContainer.jsx";

const Dashboard = () => {
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