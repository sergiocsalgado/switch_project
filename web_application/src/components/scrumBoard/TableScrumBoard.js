import React, {useContext, useEffect, useState} from 'react';
import AppContext from "../../context/AppContext";
import {fetchScrumoardAction} from "../../context/Actions";
import TableHeaderSB from "./TableHeaderScumBoard";
import TableBodySB from "./TableBodyScrumBoard";

/**
 Renders a table with the data passed as props, or a message if no data is provided.

 @param props - An object containing the properties of the table.
 @param props.data - An array of objects representing the data to be displayed in the table.
 @param props.headers - An array of strings representing the names of the columns.
 @returns The rendered table with header and body, or a message if no data is provided.
 */

function TableScrumBoard({projectCode}) {
    const {state, dispatch} = useContext(AppContext);
    const {userStories} = state;
    const {loading, error, data} = userStories;

    const [searchStatus, setSearchStatus] = useState('');
    const [searchPriority, setSearchPriority] = useState('');

    useEffect(() => {
        fetchScrumoardAction(projectCode, dispatch);
    }, [dispatch, projectCode]);

    const [tableData, setTableData] = useState([]);
    useEffect(() => {
        if (data.length > 0) {
            setTableData([...data]);
        }
    }, [data]);

    useEffect(() => {
        const filteredData = data.filter((item) => {
            if (
                searchStatus !== '' && !item.status?.toLowerCase().includes(searchStatus.toLowerCase())) {
                return false;
            }
            if (
                searchPriority !== '' && item.priority.toString() !== searchPriority) {
                return false;
            }
            return true;
        });
        setTableData(filteredData);
    }, [data, searchStatus, searchPriority]);

    if (loading) {
        return <h1>Loading ....</h1>;
    }
    if (error !== null) {
        return <h1>No data ...</h1>;
    }
    if (data.length > 0) {
        return (
            <div>
                <br/>
                <div>
                    <label>
                        <label style={{marginLeft: "20px", fontWeight: "bold", fontSize: '20px'}}>Search by Status:</label>
                        <input
                            style={{marginLeft: "5px", marginBottom: "10px",  height: '25px'}}
                            type="text"
                            value={searchStatus}
                            onChange={(e) => setSearchStatus(e.target.value)}
                        />
                    </label>
                    <label>
                        <label style={{marginLeft: "20px", fontWeight: "bold", fontSize: '20px'}}>Search by Priority:</label>
                        <input
                            style={{marginLeft: "5px", marginBottom: "10px",  height: '25px'}}
                            type="number"
                            value={searchPriority}
                            onChange={(e) => setSearchPriority(e.target.value)}
                        />
                    </label>
                </div>

                <table border="1">
                    <TableHeaderSB/>
                    <TableBodySB data={tableData} projectCode={projectCode} />
                </table>
            </div>
        );
    }
    return <h1>No data ....</h1>;
}

export default TableScrumBoard;
