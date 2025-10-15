import {FaBars, FaSearch} from "react-icons/fa";


const Header = () => {
    const searchOnClick = () => {
        console.log("search");
    }

    return (
        <div className="flex flex-1 bg-graycustom shadow-xl rounded-xl sticky-top content-center justify-items-center">
                <div className="flex flex-1 pl-5 w-4/5 h-2/3 content-center p-2  space-x-3 sm:w-1/5">
                    <div className="flex items-center justify-center">
                        <button  type="submit" onSubmit={searchOnClick}><FaSearch className="h-5 w-5" /></button>
                    </div>
                    <div className="flex flex-1 bg-whitecustom rounded-2xl content-center focus:bg-graycustom">
                        <form className="pl-3 content-center" >
                            <input className="focus:outline-none" type="search" placeholder="Search" />
                        </form>
                    </div>
                </div>
        </div>
    )
}

export default Header