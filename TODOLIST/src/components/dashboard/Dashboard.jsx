import Sidebar from "../sidebar/Sidebar.jsx";
import PageContainer from "./PageContainer.jsx";
import {useGlobalContext} from "../context/GlobalContext.jsx";
import PopupContainer from "./popuppage/PopupContainer.jsx";
import {useEffect, useMemo, useState} from "react";
import apiClient from "../../utils/apiClient.jsx";
import {useAuth} from "../authen/AuthProvider.jsx";
import {LuLoaderCircle} from "react-icons/lu";
import LoadingPage from "../../utils/LoadingPage.jsx";

const Dashboard = () => {


    const tasks = [
        {
            id: 1,
            name: "Task 1",
            description: "asldkfj;lasddjfasdl;kjfffffffffffffffffffff",
            status: "active",
            dueDate: "2025-12-01T15:30:00Z",
            tagIds: [1,2],
            listId: 1
        },
        {
            id: 2,
            name: "Task 2",
            description: "ffsdfasdfsad",
            status: "active",
            dueDate: "2025-12-01T15:30:00Z",
            tagIds: [1,2],
            listId: 2
        },
        {
            id: 3,
            name: "Task 3",
            description: "",
            status: "active",
            dueDate: "2025-12-01T15:30:00Z",
            tagIds: [1,2],
            listId: 3
        }
    ]

    const lists = [
        {
            id: 1,
            name: "List 1",
            color: "blue"
        },{
            id: 2,
            name: "List 2",
            color: "red"
        },
        {
            id: 3,
            name: "List 3",
            color: "green"
        },
    ]

    const tags = [
        {
            id: 1,
            name: "Tag 1",
            color: "blue"
        },{
            id: 2,
            name: "Tag 2",
            color: "red"
        },
        {
            id: 3,
            name: "Tag 3",
            color: "green"
        },
    ]

    const indexData = (data) => {             // method will run twice because of StrictMode so its fine
        return data.reduce((acc, item) => {
            console.log(item);
            acc[item.id] = item;
            return acc;
        }, {});
    };


    const [isDataLoading, setIsDataLoading] = useState(false);

    const [enablePopup, setEnablePopup] = useState(false);

    const [currentPage, setCurrentPage] = useState("Upcoming");

    const [currentPopupPage, setCurrentPopupPage] = useState("Task");

    const [currentTask, setCurrentTask] = useState(null)


    const [currentTag, setCurrentTag] = useState(null)

    const [currentList, setCurrentList] = useState(null)


    const [listData, setListData] = useState(lists);
    const [tagData, setTagData] = useState(tags);
    const [taskData, setTaskData] = useState(tasks);

    const tagMap = useMemo(() => indexData(tagData), [tagData]);
    const listMap = useMemo(() => indexData(listData), [listData]);

    //const [currentData, setCurrentData] = useState(null);

    // useEffect(() => {
    //     setIsDataLoading(true);
    //
    //     const taskData = apiClient("/api/app/task/get")
    //     const tagData = apiClient("/api/app/tag/get")
    //     const listData =  apiClient("/api/app/list/get")
    //
    //     const fetchData = async () => {
    //         setIsDataLoading(true);
    //
    //         try{
    //             const [tasks,tags,lists] = await Promise.all([taskData,tagData, listData]);
    //             setResults({tasks,tags,lists});
    //         } catch(err){
    //             console.log(err);
    //
    //         } finally {
    //             setIsDataLoading(false);
    //         };
    //     }
    //
    //     fetchData();
    //
    //     setTaskData(taskData);
    //     setListData(listData);
    //     setTagData(tagData);
    //
    //
    //     setIsDataLoading(false);
    // }, []);

    if(isDataLoading) {
        return (<LoadingPage></LoadingPage>)
    }

    return (
            <div className="flex flex-col overflow-x-hidden min-h-screen sm:p-4">
                <div className="flex flex-1 h-full sm:space-x-5">
                    <Sidebar
                        //listData={listData}
                        //tagData={tagData}
                        setCurrentPage={setCurrentPage}
                        currentPage={currentPage}
                        setEnablePopup={setEnablePopup}
                        tagMap={tagMap}
                        listMap={listMap}
                    ></Sidebar>
                    <PageContainer
                        currentPage={currentPage}
                        setEnablePopup={setEnablePopup}
                        setCurrentTask={setCurrentTask}
                        setCurrentPopupPage={setCurrentPopupPage}
                        taskData={taskData}
                        listMap={listMap}
                        //taskData={taskData}
                    />
                    {enablePopup ? (<PopupContainer
                        tagMap={tagMap}
                        listData={listData}
                        currentTask={currentTask}
                        currentTag={currentTag}
                        currentList={currentList}
                        currentPopupPage={currentPopupPage}
                        setEnablePopup={setEnablePopup}
                    ></PopupContainer>) : null}
                </div>

            </div>
    )
}

export default Dashboard;