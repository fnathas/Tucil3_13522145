import { useState } from 'react'
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { useEffect } from 'react'
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogTrigger
} from "@/components/ui/dialog"

import './App.css'

type data = {
    executionTime?: number
    path: string[]
    totalNodesVisited?: number
}

function App() {
    const [startWord, setStartWord] = useState('')
    const [endWord, setEndWord] = useState('')
    const [algorithm, setAlgorithm] = useState('')
    const [data, setData] = useState<data | null>(null)
    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        let intervalId: NodeJS.Timeout | null = null
        if (isLoading) {    
            // Call the API here
            fetch(`http://localhost:8080/run?start=${startWord}&goal=${endWord}&algorithm=${algorithm}`)
                .then(response => {
                    if (!response.ok) {
                        if (response.status === 400) {
                            // If the status is 400, parse the response body to get the error message
                            return response.text().then(errorMessage => {
                                throw new Error(errorMessage);
                            });
                        } else {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data)
                    setData(data)
                })
                .catch(error => {
                    console.error('There was an error!', error)
                    if (error.message) {
                        alert(error.message)
                    }
                })
                .finally(() => {
                    setIsLoading(false)
                    if (intervalId) {
                        clearInterval(intervalId)
                    }
                })
        } 

        return () => {
            if (intervalId) {
                clearInterval(intervalId)
            }
        }
    }, [isLoading])

    const handleSubmit = () => {
        if (startWord === '' || endWord === '' || algorithm === '') {
            return
        }
        setData(null)
        setIsLoading(true)
    }

    return (
        <>
            <div className="bg-gray-800 h-screen">
                <div className="h-[200px]">
                    <h1 className="text-6xl text-white text-center p-[75px]">WORD LADDER</h1>
                </div>
                <div>
                    <div className="px-[30%] h-[200px]">
                        <label className="text-white flex justify-center">Start Word</label>
                        <Input className="text-center text-[20px] border-black border-2"
                               type="text"
                               value={startWord}
                               onChange={(e) => setStartWord(e.target.value)}
                        />
                        <p className="text-white text-center">to</p>
                        <label className="text-white flex justify-center">End Word</label>
                        <Input className="text-center text-[20px] border-black border-2"
                               type="text"
                               value={endWord}
                               onChange={(e) => setEndWord(e.target.value)}
                        />
                    </div>
                    <div className="flex justify-center gap-4 h-[75px]">
                        <Button
                            className="cursor-pointer w-[175px] bg-gray-950 hover:bg-gray-500 active:bg-gray-400 focus:outline-none focus:ring focus:ring-gray-400"
                            onClick={() => setAlgorithm('ucs')}>UCS</Button>
                        <Button
                            className="cursor-pointer w-[175px] bg-gray-950 hover:bg-gray-500 active:bg-gray-400 focus:outline-none focus:ring focus:ring-gray-400"
                            onClick={() => setAlgorithm('gbfs')}>Greedy Best First</Button>
                        <Button
                            className="cursor-pointer w-[175px] bg-gray-950 hover:bg-gray-500 active:bg-gray-400 focus:outline-none focus:ring focus:ring-gray-400"
                            onClick={() => setAlgorithm('astar')}>A*</Button>
                    </div>
                    <div className="flex justify-center h-[50px]">
                        <Dialog>
                            <DialogTrigger>
                                <Button className="cursor-pointer w-[175px] bg-gray-950 hover:bg-gray-500 active:bg-gray-400" onClick={handleSubmit}>Submit</Button>
                            </DialogTrigger>
                            {isLoading && (
                                <DialogContent>
                                    <DialogHeader>
                                        <DialogTitle>Loading...</DialogTitle>
                                    </DialogHeader>
                                    <DialogDescription>
                                        <p>Please wait for the result.</p>
                                    </DialogDescription>
                                </DialogContent>
                            )}
                            {data && ( // Only render the DialogContent when there's no error and showDialog is true
                                <DialogContent>
                                    <DialogHeader>
                                        <DialogTitle>Result</DialogTitle>
                                    </DialogHeader>
                                    <DialogDescription>
                                        {data.path !== null ? (
                                            <div>
                                                <p>Path: {data.path.join(' -> ')}</p>
                                                <p>Execution Time: {data.executionTime} ms</p>
                                                <p>Node Visited: {data.totalNodesVisited}</p>
                                            </div>
                                        ) : (
                                            <div>
                                                <p>No path found</p>
                                                <p>Execution Time: {data.executionTime} ms</p>
                                                <p>Node Visited: {data.totalNodesVisited}</p>
                                            </div>
                                        )}
                                    </DialogDescription>
                                </DialogContent>
                            )}
                        </Dialog>
                    </div>
                </div>
            </div>
        </>
    )
}

export default App