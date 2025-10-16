import { IoCloseOutline } from "react-icons/io5";
import {useGlobalContext} from "../../context/GlobalContext.jsx";

const PopupContainer = () => {
    const {setEnablePopup} = useGlobalContext();

    const disablePopup = () => {
        setEnablePopup(false);
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

                </div>
            </div>
        </div>

    )
}

export default PopupContainer;