import {createContext} from "react";


const errorContext = createContext(null);

const ErrorProvider = ({props,formcase}) => {

    const responseMessage = (props) => {
        console.log("does this run at all");
            if(props.hasError){
               return Object.keys(props).map((key) => (
                    <p>{props[key]}</p>
                ))
            }
            return (<p>{props["message"]}</p>)
        }


    switch(formcase) {
        case "login": {
            return (
                <div className="flex flex-col  fixed justify-center w-full p-3 bg-green-400 bottom-0 md:bottom-5 sm:left-5 sm:w-lg sm:rounded-sm">
                    {
                        responseMessage(props)
                    }
                    Login
                </div>
            );
        }
        case "register": {
            return (
                <div className={'flex flex-col fixed justify-center w-full p-3 ' + (props.hasError==null ? "bg-green-400 text-black " : "bg-red-500 text-white") + ' bottom-0 md:bottom-5 sm:left-5 sm:w-lg sm:rounded-sm'}>
                    {
                        responseMessage(props)
                    }
                    Register
                </div>
            );
        }
    }


}

export default ErrorProvider;