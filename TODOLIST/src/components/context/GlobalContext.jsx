import {createContext, useContext, useState} from "react";

const GlobalContextObject = createContext(null);

export const useGlobalContext = () => useContext(GlobalContextObject)

const GlobalContext = ({ children }) => {

    const [currentPage, setCurrentPage] = useState("Upcoming");

    const [isCustomized, setIsCustomized] = useState(true);

    const [enablePopup, setEnablePopup] = useState(null);

    const value = {currentPage,setCurrentPage, isCustomized, setIsCustomized, enablePopup, setEnablePopup};

    return (<GlobalContextObject.Provider value={value}>
        { children }
    </GlobalContextObject.Provider>)

}

export default GlobalContext;