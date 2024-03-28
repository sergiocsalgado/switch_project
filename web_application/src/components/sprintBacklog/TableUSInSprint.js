import React, {useContext, useEffect, useState} from 'react';
import TableHeaderUSInSprint from "./TableHeaderUSInSprint";
import TableBodyUSInSprint from "./TableBodyUSInSprint";
import AppContext from "../../context/AppContext";
import {fetchUserStoryInSprint} from "../../context/Actions";

/**
 Renders a table with the data passed as props, or a message if no data is provided.

 @param props - An object containing the properties of the table.
 @param props.data - An array of objects representing the data to be displayed in the table.
 @param props.headers - An array of strings representing the names of the columns.
 @returns The rendered table with header and body, or a message if no data is provided.
 */

function TableUSInSprint({projectCode,sprintID}) {
    const {state, dispatch} = useContext(AppContext);
    const {userStories} = state;
    const {loading, error, data} = userStories;

    const [searchDescription, setSearchDescription] = useState('');
    const [searchPriority, setSearchPriority] = useState('');

    useEffect(() => {
        fetchUserStoryInSprint(projectCode,sprintID, dispatch);
    }, [projectCode,sprintID,dispatch]);

    const [tableSB, setTableSB] = useState([]);
    useEffect(() => {
        if (data.length > 0) {
            setTableSB([...data]);
        }
    }, [data]);

    useEffect(() => {
        if (Array.isArray(data)) { // Check if data is an array
            const filteredData = data.filter((item) => {
                if (
                    searchDescription !== '' &&
                    !item.userStoryText?.toLowerCase().includes(searchDescription.toLowerCase())
                ) {
                    return false;
                }
                if (
                    searchPriority !== '' &&
                    item.priority.toString() !== searchPriority
                ) {
                    return false;
                }
                return true;
            });
            setTableSB(filteredData);
        }
    }, [data, searchDescription, searchPriority]);


    if (loading) {
        return <h1>Loading ....</h1>;
    }
    if (error !== null) {
        return <h1>Error ....</h1>;
    }
    if (data.length > 0) {
        return (
            <div>
                <br/>
                <div>
                    <label>
                        <label style={{marginLeft: "20px", fontWeight: "bold", fontSize: '20px' }}>Search by Description:</label>
                        <input
                            style={{marginLeft: "5px", marginBottom: "10px",  height: '25px' }}
                            type="text"
                            value={searchDescription}
                            onChange={(e) => setSearchDescription(e.target.value)}
                        />
                    </label>
                    <label>
                        <label style={{marginLeft: "20px", fontWeight: "bold", fontSize: '20px' }}>Search by Priority:</label>
                        <input
                            style={{marginLeft: "5px", marginBottom: "10px", height: '25px' }}
                            type="number"
                            value={searchPriority}
                            onChange={(e) => setSearchPriority(e.target.value)}
                        />
                    </label>
                </div>

                <table border="1">
                    <TableHeaderUSInSprint/>
                    <TableBodyUSInSprint data={tableSB} sprintID={sprintID}/>
                </table>
            </div>
        );
    }
    return <h1>No data ....</h1>;
}

export default TableUSInSprint;
