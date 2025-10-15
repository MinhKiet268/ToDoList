import {createContext, useContext, useState} from "react";


const ErrorContext = createContext(null);

export const useError = () => useContext(ErrorContext)

const ErrorProvider = ({ children }) => {

    const [error, setError] = useState(null);

    const errorHandler = () => {
        console.log("Error occured");
    }

    return (<ErrorContext.Provider value={{error,errorHandler}}>
        { children }
    </ErrorContext.Provider>)


}

export default ErrorProvider;