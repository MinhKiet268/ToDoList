import Header from "./Header.jsx";

const TaskContainer = (props) => {
    return (
        <div className="flex flex-col flex-1">
            <Header></Header>
            <main className="flex flex-1 overflow-y-auto bg-gray-50 ">
                <div className="flex flex-col flex-1 min-h-screen items-center">
                    <div><h1 className="font-sans font-bold">TASK LIST</h1></div>


                    <ul className="flex flex-col w-full p-3 space-y-3">
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                        <li className="bg-gray-400 rounded-xl h-20">
                            item 1
                        </li>
                    </ul>
                </div>

            </main>
        </div>

    )
}

export default TaskContainer