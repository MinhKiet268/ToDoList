import NavItem from "./NavItem.jsx";
import { TbCalendarUp } from "react-icons/tb";
import { FaTasks,FaRegCalendarAlt   } from "react-icons/fa";
import {useGlobalContext} from "../context/GlobalContext.jsx";
import {useEffect} from "react";


const ListNavItem = ({title, activedTab, setActivedTab, currentPage, setCurrentPage}) => {
    //const {currentPage} = useGlobalContext();

    // useEffect(() => {
    //     console.log(activedTab +"+"+ currentPage);
    // }, []);

    const navItems = [
        // Passing the component function reference as the prop 'IconComponent'
        { id: 'upcoming', label: 'Upcoming', IconComponent: TbCalendarUp },
        { id: 'today', label: 'Today', IconComponent: FaTasks },
        { id: 'calender', label: 'Calendar', IconComponent: FaRegCalendarAlt },
    ];

    const navList = [
        // Passing the component function reference as the prop 'IconComponent'
        { id: 'upcoming', label: 'Upcoming', IconComponent: TbCalendarUp },
        { id: 'today', label: 'Today', IconComponent: FaTasks },
        { id: 'calender', label: 'Calendar', IconComponent: FaRegCalendarAlt },
    ];

    const navTag = [
        // Passing the component function reference as the prop 'IconComponent'
        { id: 'upcoming', label: 'Upcoming', IconComponent: TbCalendarUp },
        { id: 'today', label: 'Today', IconComponent: FaTasks },
        { id: 'calender', label: 'Calendar', IconComponent: FaRegCalendarAlt },
    ];

    return (
        <div className="flex flex-col ">
            <div className="flex items-center h-10">
                <p className="font-roboto ">{title}</p>
            </div>
            <div className="flex flex-col justify-center">
                {navItems.map((item) => (
                    <NavItem
                        key={item.id}
                        label={item.label}
                        IconComponent={item.IconComponent}
                        color={item.color}
                        setActivedTab={setActivedTab}
                        isActived={navItems.label === currentPage}
                        activedTab={ activedTab}
                        setCurrentPage={setCurrentPage}
                    ></NavItem>
                ))}
            </div>


        </div>


    )
}

export default ListNavItem