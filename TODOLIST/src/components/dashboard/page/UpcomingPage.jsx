import Header from "../Header.jsx";
import Task from "../Task.jsx";
import { FaPlus } from "react-icons/fa6";


const UpcomingPage = () => {
    return (
        <div className="flex p-7 flex-col flex-1 rounded-xl bg-white">
            <main className="flex flex-1 overflow-y-auto ">
                <div className="flex flex-col flex-1 min-h-screen items-center">
                    <ul className="flex flex-col h-screen w-full">
                        <div className="group flex h-16 p-3 border-t-2 border-graycustom3 hover:bg-graycustom">
                            <button className="flex flex-1 items-center">
                                <div className="flex h-full m-2 items-center">
                                    <FaPlus className="w-3/4 h-3/4 text-grayclick group-hover:text-black" />
                                </div>

                                <p className="text-xl text-grayclick group-hover:text-black">Add new task</p>
                            </button>
                        </div>

                        <Task></Task>
                    </ul>
                </div>
            </main>
        </div>
    )
}

export default UpcomingPage