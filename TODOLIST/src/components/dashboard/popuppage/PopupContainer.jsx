import { IoCloseOutline } from "react-icons/io5";
import TaskCustomizePage from "./TaskCustomizePage.jsx";
import TagCustomizePage from "./TagCustomizePage.jsx";
import ListCustomizePage from "./ListCustomizePage.jsx";


const PopupContainer = ({currentPopupPage, setEnablePopup,currentList, currentTask, currentTag, tagMap, listData}) => {

    const disablePopup = () => {
        setEnablePopup(false);
    }



    const renderPage = () => {
        switch (currentPopupPage) {
            case 'Task':
                return (
                    <TaskCustomizePage currentTask={currentTask} tagMap={tagMap} listData={listData}></TaskCustomizePage>
                )
            case 'Tag':
                return (
                    <TagCustomizePage currentTag={currentTag} ></TagCustomizePage>
                )
            case 'List':
                return (
                    <ListCustomizePage currentList={currentList}  ></ListCustomizePage>
                )
        }
    }

    return (
        <div className="flex flex-col h-[calc(100vh-2rem)] w-0 transition-transform translate-x-full lg:translate-x-0 lg:min-w-150">
            <div className=" fixed flex p-4 flex-col h-[calc(100vh-2rem)] w-0 bg-graycustom lg:rounded-2xl transition-transform translate-x-full lg:translate-x-0 lg:min-w-full">
                <div className="flex flex-1 justify-end ">
                    <button className="hover:bg-graycustom2 rounded-md" onClick={disablePopup}>
                        <IoCloseOutline className="w-10 h-10"></IoCloseOutline>
                    </button>
                </div>

                <div className="flex flex-col h-full ">
                    {/*<TaskCustomizePage></TaskCustomizePage>*/}
                    {renderPage()}
                </div>
            </div>
        </div>

    )
}

export default PopupContainer;