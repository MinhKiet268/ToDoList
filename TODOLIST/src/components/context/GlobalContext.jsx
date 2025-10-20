import {createContext, useContext, useState} from "react";

const GlobalContextObject = createContext(null);

export const useGlobalContext = () => useContext(GlobalContextObject)

const GlobalContext = ({ children }) => {

    const [currentTask, setCurrentTask] = useState(null)
    const [currentTag, setCurrentTag] = useState(null)
    const [currentList, setCurrentList] = useState(null)



    const [currentPage, setCurrentPage] = useState("Upcoming");

    const [currentPopupPage, setCurrentPopupPage] = useState(null);

    const [isCustomized, setIsCustomized] = useState(true);

    const [enablePopup, setEnablePopup] = useState(false);

    const value = {currentPage,setCurrentPage
        , isCustomized, setIsCustomized
        , enablePopup, setEnablePopup
        , setCurrentTask, currentTask
        , currentTag, setCurrentTag
        , currentList, setCurrentList
        , currentPopupPage, setCurrentPopupPage};

    return (<GlobalContextObject.Provider value={value}>
        { children }
    </GlobalContextObject.Provider>)

}

export default GlobalContext;