import {useGlobalContext} from "../context/GlobalContext.jsx";
import {useEffect, useState} from "react";

const NavItem = ({label, IconComponent, isActived, setActivedTab, activedTab}) => {
    const {currentPage,setCurrentPage} = useGlobalContext();

    const onClickEvent = () => {
        setCurrentPage(label);
    }

    const containerClassName = "flex flex-1 justify-between p-2  items-center rounded-md bg-graycustom3"
    const itemClassName = "flex items-center justify-center rounded-sm space-x-2 min-w-6 min-h-6 bg-whitecustom";

    return (
        <button onClick={onClickEvent}>
            <div className={(currentPage===label) ?
                containerClassName
                : " group flex flex-1 justify-between p-2  items-center rounded-md focus:bg-graycustom2 hover:bg-graycustom2 bg-graycustom"}>
                <div className="flex flex-1 items-center space-x-2">
                    <IconComponent></IconComponent>
                    <p>
                        {label}
                    </p>
                </div>
                <div className={(currentPage===label) ?
                    itemClassName
                    : "flex items-center justify-center rounded-sm space-x-2 min-w-6 min-h-6 bg-graycustom2 group-hover:bg-whitecustom"}>
                    <p>5</p>
                </div>
            </div>
        </button>

    )
}


export default NavItem