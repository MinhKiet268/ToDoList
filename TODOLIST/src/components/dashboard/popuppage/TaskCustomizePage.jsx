import {useContext, useState} from "react";
import {useGlobalContext} from "../../context/GlobalContext.jsx";
import apiClient from "../../../utils/apiClient.jsx";


const TaskCustomizePage = ({currentTask, tagMap, listMap, listData}) => {

    const mockColor = "background-color: yellow";

    const [task, setTask] = useState(currentTask);

    const handleChange = (e) => {
        // Destructure 'name' (the input field name) and 'value' (the input value)
        const { name, value } = e.target;

        // Update the state dynamically
        setFormData(prevData => ({
            ...prevData,
            [name]: value // [name] uses the input's name attribute as the state key
        }));
    };

    return (


            <div className="flex flex-1 flex-col rounded-lg font-funnel ">

                <form className="flex flex-1 flex-col font-bold text-fontgray">
                    <div className="flex flex-1 flex-col space-y-2 ">
                        <input name="name" onChange={handleChange} value={currentTask!=null ? currentTask.name : ""} className="rounded-3xl p-4 w-full h-12 bg-whitecustom items-center focus:outline-none border-2 border-graycustom2 focus:border-2 focus:border-grayclick" type="text" placeholder="Task name"/>
                        <textarea name="description" onChange={handleChange} value={currentTask!=null ? currentTask.description : ""} className="rounded-3xl p-4 w-full resize-none h-40  bg-whitecustom items-center focus:outline-none border-2 border-graycustom2 focus:border-2 border-grayclick" placeholder="Description"/>
                        <div className="flex flex-1 w-100 flex-col space-y-2 ">
                            <div className="flex space-y-2 items-center">
                                <p className="flex w-25">List</p>
                                <select name="listId" onChange={handleChange} defaultValue={currentTask!=null ? currentTask.listId : ""} className=" flex h-full rounded-3xl text-center p-3 w-60 h-12 bg-whitecustom items-center focus:outline-none border-2 border-graycustom2 focus:border-2 focus:border-grayclick">
                                    <option value="0">None</option>
                                    {listData.map((item) => (
                                        <option key={item.id} value={item.id}>{item.name} </option>
                                    ))}

                                </select>
                            </div>
                            <div className="flex space-y-2 items-center">
                                <p className="flex w-25">Due date</p>
                                <input name="dueDate" onChange={handleChange} value={currentTask.dueDate} type="date" placeholder="Due date" className="flex h-full text-center rounded-3xl p-3 w-60 h-12 bg-whitecustom focus:outline-none border-2 border-graycustom2 focus:border-2 focus:border-grayclick"/>
                            </div>
                            <div className="flex items-center">
                                <p className="flex w-25">Tags</p>
                                <div className="flex h-full rounded-3xl p-3 w-20 h-12 bg-whitecustom justify-center items-center focus:outline-none border-2 border-graycustom2 focus:border-2 focus:border-grayclick"
                                     style={{backgroundColor: "red"}}
                                >
                                    <input value={""} name="taskIds" onChange={handleChange}/>
                                </div>
                            </div>

                        </div>
                    </div>


                    <div className="flex justify-around text-xl font-bold text-fontgray">

                        <button className="flex h-17 min-w-60 border-2 border-graycustom3 items-center justify-center bg-graycustom2 rounded-2xl">
                            <p className="font-funnel">
                                Delete Task
                            </p>
                        </button>
                        <button className="flex h-17 min-w-60 border-2 border-graycustom3 items-center justify-center bg-orangecustom rounded-2xl" type={"submit"}>
                            <p className="font-funnel">
                                Save Change
                            </p>
                        </button>
                    </div>
                </form>

            </div>

    )
}

export default TaskCustomizePage