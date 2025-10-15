import {createContext, useContext, useState} from "react";

const GlobalContextObject = createContext(null);

export const useGlobalContext = () => useContext(GlobalContextObject)

const GlobalContext = ({ children }) => {

    const [currentPage, setCurrentPage] = useState("Upcoming");

    const value = {currentPage,setCurrentPage};

    return (<GlobalContextObject.Provider value={value}>
        { children }
    </GlobalContextObject.Provider>)

}

export default GlobalContext;