import {LuLoaderCircle} from "react-icons/lu";


const LoadingPage = () => {
    return (
        <div className="flex flex-1 h-screen justify-center items-center">
            <LuLoaderCircle className="w-30 h-30 stroke-3 animate-spin text-grayclick"></LuLoaderCircle>
        </div>
    )
 }

 export default LoadingPage;