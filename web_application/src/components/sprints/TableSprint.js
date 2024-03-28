import React, { useContext, useEffect, useState } from 'react';
import TableHeaderSprint from './TableHeaderSprint';
import TableBodySprint from './TableBodySprint';
import AppContext from '../../context/AppContext';
import { fetchSprintsFromProject } from '../../context/Actions';
import {showAlertModal} from "../utils/Alert";

function TableSprint({ projectCode }) {
    const { state, dispatch } = useContext(AppContext);
    const { sprints } = state;
    const { loading, error, data } = sprints;

    const [searchNumber, setSearchNumber] = useState('');
    const [filterOption, setFilterOption] = useState('All');

    useEffect(() => {
        fetchSprintsFromProject(projectCode, dispatch);
    }, [dispatch, projectCode]);

    const [tableData, setTableData] = useState([]);
    useEffect(() => {
        if (data.length > 0) {
            setTableData([...data]);
        }
    }, [data]);

    useEffect(() => {
        let filteredData = data;

        if ((filteredData)) {
            filteredData = filteredData.filter((item) => {
                if (
                    searchNumber !== '' && item.sprintNumber.toString() !== searchNumber
                ) {
                    return false;
                }

                if (filterOption === 'planned' && item.sprintStatus !== 'planned') {
                    return false;
                }

                if (filterOption === 'open' && item.sprintStatus !== 'open') {
                    return false;
                }

                if (filterOption === 'closed' && item.sprintStatus !== 'closed') {
                    return false;
                }

                return true;

            });

        }

        setTableData(filteredData);
    }, [data, searchNumber, filterOption]);

    if (loading) {
        return <h1>Loading ....</h1>;
    }

    if (data.length > 0) {
        return (
            <div>
                <div>
                    <label>
                        <label style={{ marginLeft: '20px', fontWeight: 'bold', fontSize: '20px' }}>
                            Search by Number:
                        </label>
                        <input
                            style={{ marginLeft: '10px', marginBottom: '10px', height: '20px' }}
                            type="number"
                            value={searchNumber}
                            onChange={(e) => setSearchNumber(e.target.value)}
                        />
                    </label>
                    <label>
                        <label
                            style={{ marginLeft: '30px', fontWeight: 'bold', fontSize: '20px'  }}>
                            Filter by Status:
                        </label>
                        <select
                            style={{ marginLeft: '5px', marginBottom: '10px', height:'30px' }}
                            value={filterOption}
                            onChange={(e) => setFilterOption(e.target.value)}

                        >
                            <option value="all">All</option>
                            <option value="planned">Planned</option>
                            <option value="open">Open</option>
                            <option value="closed">Closed</option>
                        </select>
                    </label>
                </div>
                <table border="1">
                    <TableHeaderSprint />
                    <TableBodySprint data={tableData} projectCode={projectCode} />
                </table>
            </div>
        );
    }
    return <h1>No data ....</h1>;
}

export default TableSprint;
