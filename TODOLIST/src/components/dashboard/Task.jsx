import {FaPlus} from "react-icons/fa6";
import { TiDelete } from "react-icons/ti";
import { FaAngleRight  } from "react-icons/fa6";
import {useGlobalContext} from "../context/GlobalContext.jsx";

const Task = ({ props }) => {

    const {setEnablePopup} = useGlobalContext()

    const enablePopup = () => {
        setEnablePopup(true);
    }

    return (
        <div className="flex h-16 p-3  bg-whitecustom hover:bg-graycustom">
            <button className="flex flex-1 items-center justify-between " onClick={enablePopup}>
                <p className="text-xl">This is a task</p>
                <FaAngleRight className="w-10 h-10 text-grayclick"></FaAngleRight>

            </button>
        </div>
    )
}

export default Task