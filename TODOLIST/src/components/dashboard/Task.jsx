import {FaPlus} from "react-icons/fa6";
import { FaCalendarTimes } from "react-icons/fa";
import { FaAngleRight  } from "react-icons/fa6";
import {useGlobalContext} from "../context/GlobalContext.jsx";

const Task = ({ props }) => {

    const {setEnablePopup} = useGlobalContext()



    const enablePopup = () => {
        setEnablePopup(true);
    }

    return (
        <div className="flex max-h-22 p-3  bg-whitecustom hover:bg-graycustom">
            <button className="flex flex-1 flex-col items-center" onClick={enablePopup}>
                <div className="flex flex-1 w-full items-center justify-between">
                    <p className="text-xl">This is a task</p>
                    <FaAngleRight className="w-10 h-10 text-grayclick"></FaAngleRight>
                </div>
                {props ? (
                    <div className="flex flex-1 w-full space-x-2">
                        {props.due_date ? (
                            <>
                                <div className="flex space-x-1 items-center">
                                    <FaCalendarTimes className="w-4 h-4 text-grayclick"></FaCalendarTimes>
                                    <p className="font-funnel font-bold text-grayclick">{props.due_date}</p>
                                </div>

                            </>
                        ) : null}

                        {props.tasklist ? (
                            <>
                                <div className="border-l border-gray-300 h-5"></div>
                                <div className="flex flex-1 space-x-1 items-center">
                                    <div className={"h-5 w-5 border border-graycustom3 h-5 rounded-md"}
                                        style={props.tasklist.color ? {backgroundColor: props.tasklist.color} : null}
                                    >
                                    </div>
                                    <p className="font-funnel font-bold text-grayclick">{props.tasklist.name}</p>
                                </div>
                            </>
                        ) : null}


                    </div>
                ) : null}


            </button>
        </div>
    )
}

export default Task

// private long id;
// private String name;
// private String description;
//
//
// private Set<Long> tagIds;
//
// private long listId;
//
// private String status;
// private Date issueDate;
// private Date dueDate;
//
// // A many-to-one relationship back to the UserEntity
// // This is the owning side, which has the foreign key.
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "user_id")
// private UserEntity user;