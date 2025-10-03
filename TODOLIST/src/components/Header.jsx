import {FaBars, FaSearch} from "react-icons/fa";


const Header = () => {
    const searchOnClick = () => {
        console.log("search");
    }

    return (
        <div className="bg-gray-50 h-20 bg-gray-500 sticky-top content-center justify-items-center">
                <div className="flex flex-1 w-4/5 h-2/3 content-center p-2  space-x-3 sm:w-1/5">
                    <div className="flex items-center justify-center">
                        <button  type="submit" onSubmit={searchOnClick}><FaSearch className="h-10 w-10" /></button>
                    </div>
                    <div className="flex flex-1 bg-amber-50 rounded-2xl content-center ">
                        <form className="pl-3 content-center" >
                            <input className="focus:outline-none" type="search" placeholder="Search" />
                        </form>
                    </div>
                </div>
        </div>
    )
}

export default Header