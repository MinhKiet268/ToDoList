import {createContext, useContext, useEffect, useState} from "react";


const ErrorContext = createContext(null);

export const useError = () => useContext(ErrorContext)

const ErrorProvider = ({ children }) => {

    const [error, setError] = useState(null);
    const [enablePopup, setEnablePopup] = useState(false);

    useEffect(() => {
        setEnablePopup()
        const timer = setTimeout(() => {
            setEnablePopup(false)
        }, 3000)
    },error)

    const errorHandler = () => {
        console.log("Error occured");
    }

    return (<ErrorContext.Provider value={{error,setError}}>
        {enablePopup ? (<div className="flex flex-1 justify-center fixed top-1 "></div>) : null}
        { children }
    </ErrorContext.Provider>)


}

export default ErrorProvider;