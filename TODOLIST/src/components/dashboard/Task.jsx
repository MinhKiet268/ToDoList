import {FaPlus} from "react-icons/fa6";

const Task = ({ props }) => {
    return (
        <div className="flex h-16 p-3 border-t-2 border-graycustom3 bg-whitecustom">
            <div className="flex flex-1 items-center">
                <p className="text-xl">This is a task</p>
            </div>
        </div>
    )
}

export default Task