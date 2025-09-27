import {useState} from "react";


const Task = (props) => {
    const [task, setTask] = useState(false);

    return (
        <div>
            <h1>Task</h1>
            <p>Name</p>
            <p>description</p>
            <p>status</p>
            <button onClick={() => setTask(true)}>{task ? "Liked" : "Like"}</button>
        </div>
    )
}

export default Task;