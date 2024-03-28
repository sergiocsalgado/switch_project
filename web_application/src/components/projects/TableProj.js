import React, { useContext, useEffect, useState } from 'react';
import TableHeaderProj from './TableHeaderProj';
import TableBodyProj from './TableBodyProj';
import AppContext from "../../context/AppContext";
import {fetchProjectsAction} from "../../context/Actions";
import {showAlertModal} from "../utils/Alert";

import '../../App.css';

/**
 * Renders a table with the data passed as props, or a message if no data is provided.
 *
 * @param props - An object containing the properties of the table.
 * @param props.data - An array of objects representing the data to be displayed in the table.
 * @param props.headers - An array of strings representing the names of the columns.
 * @returns The rendered table with header and body, or a message if no data is provided.
 */
function TableProj() {
    const {state, dispatch} = useContext(AppContext);
    const {projects} = state;
    const {loading, error, data} = projects;

    useEffect(() => {
        fetchProjectsAction(dispatch);
    }, [dispatch]);

    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        if (data.length > 0) {
            setTableData([...data]);
        }
    }, [data]);

    const [searchName, setSearchName] = useState('');
    const [searchCustomer, setSearchCustomer] = useState('');
    const [searchStatus, setSearchStatus] = useState('');

    // Apply filters to the table data
    const filteredTableData = tableData.filter((item) => {
        const nameMatch =
            (item.name && item.name.toLowerCase().includes(searchName.toLowerCase().trim())) ||
            (searchName === '');

        const customerMatch =
            (item.customer && item.customer.toLowerCase().includes(searchCustomer.toLowerCase().trim())) ||
            (searchCustomer === '');

        const statusMatch =
            (item.status &&
                item.status.toLowerCase().includes(searchStatus.toLowerCase().trim())) ||
            searchStatus === '' ||
            (searchStatus === 'Open' &&
                ['inception', 'elaboration', 'construction', 'planned', 'transition'].includes(
                    item.status.toLowerCase()
                )) ||
            (searchStatus === 'Closed' &&
                ['closed'].includes(item.status.toLowerCase()));

        return nameMatch && customerMatch && statusMatch;
    });

    if (loading === true) {
        return <h1>Loading ....</h1>;
    } else {
        if (error !== null) {
            return <h1>Error ....</h1>;
        } else {
            return (
                <div>
                    <br />
                    <div className="filter-section">
                        <label style={{ marginLeft: '20px', fontWeight: 'bold', fontSize: '20px'}}>Search by Name: </label>
                        <input
                            type="text"
                            placeholder="Insert name"
                            value={searchName}
                            onChange={(e) => setSearchName(e.target.value)}
                            style={{ height: '25px' }}
                        />
                        <label style={{ marginLeft: '20px', fontWeight: 'bold', fontSize: '20px' }}>Search by Customer: </label>
                        <input
                            type="text"
                            placeholder="Insert Customer"
                            value={searchCustomer}
                            onChange={(e) => setSearchCustomer(e.target.value)}
                            style={{ height: '25px' }}
                        />
                        <label style={{ marginLeft: '20px', fontWeight: 'bold', fontSize: '20px' }}>Filter by Status: </label>
                        <select
                            value={searchStatus}
                            onChange={(e) => setSearchStatus(e.target.value)}
                            style={{ height: '30px' }}
                        >
                            <option value="Open">Open</option>
                            <option value="Closed">Closed</option>
                            <option value="">All</option>
                        </select>
                    </div>
                    <br />
                    <div className="table-container">
                        {filteredTableData.length > 0 ? (
                            <table border="1">
                                <TableHeaderProj />
                                <TableBodyProj data={filteredTableData} />
                            </table>
                        ) : (
                            <h1>No data ....</h1>
                        )}
                    </div>
                </div>
            );
        }
    }

    if (error !== null) {
        showAlertModal("Error!", "Form not submitted due to invalid inputs!");
    }

    if (data.length > 0) {
        return (
            <div>
                <br/>
                <div className="filter-section">
                    <label style={{marginLeft: '20px', fontWeight: 'bold'}}>Search by Name: </label>
                    <input
                        type="text"
                        placeholder="Search by name"
                        value={searchName}
                        onChange={(e) => setSearchName(e.target.value)}
                    />
                    <label style={{marginLeft: '20px', fontWeight: 'bold'}}>Search by Customer: </label>
                    <input
                        type="text"
                        placeholder="Search by customer"
                        value={searchCustomer}
                        onChange={(e) => setSearchCustomer(e.target.value)}
                    />
                    <label style={{marginLeft: '20px', fontWeight: 'bold'}}>Search by Status: </label>
                    <select
                        value={searchStatus}
                        onChange={(e) => setSearchStatus(e.target.value)}
                    >
                        <option value="Open">Open</option>
                        <option value="Closed">Closed</option>
                        <option value="">All</option>
                    </select>
                </div>
                <br/>
                <div className="table-container">
                    {filteredTableData.length > 0 ? (
                        <table border="1">
                            <TableHeaderProj/>
                            <TableBodyProj data={filteredTableData}/>
                        </table>
                    ) : (
                        <h1>No data ....</h1>
                    )}
                </div>
            </div>
        );
    }

}

export default TableProj;
