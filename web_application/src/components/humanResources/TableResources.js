import React, {useContext, useEffect, useState} from 'react';
import AppContext from '../../context/AppContext';
import TableHeaderResources from './TableHeaderResources';
import TableBodyResources from './TableBodyResources';
import {fetchResourcesFormProject} from '../../context/Actions';
import  {showAlertModal} from "../utils/Alert";

function TableResources({projectCode}) {
    const {state, dispatch} = useContext(AppContext);
    const {resources} = state;
    const {loading, error, data} = resources;


    const [searchEmail, setSearchEmail] = useState('');
    const [searchRole, setSearchRole] = useState('');
    const [searchActive, setSearchActive] = useState('');
    const [roleOptions, setRoleOptions] = useState([
        'Team Member',
        'Product Owner',
        'Scrum Master',
        'Project Manager'
    ]);

    useEffect(() => {
        fetchResourcesFormProject(projectCode, dispatch);
    }, [dispatch, projectCode]);

    const [tableData, setTableData] = useState([]);
    useEffect(() => {
        if (data.length > 0) {
            setTableData([...data]);
        }
    }, [data]);

    useEffect(() => {
        const filterData = data.filter((item) => {
            if (
                searchEmail !== '' &&
                !item.email?.toLowerCase().includes(searchEmail.toLowerCase())
            ) {
                return false;
            }
            if (
                searchRole !== '' &&
                !item.role.toLowerCase().includes(searchRole.toLowerCase())
            ) {
                return false;
            }
            if (searchActive === 'active' && !isResourceActive(item.startDate, item.endDate)) {
                return false;
            }
            if (searchActive === 'inactive' && isResourceActive(item.startDate, item.endDate)) {
                return false;
            }
            return true;
        });
        setTableData(filterData);
    }, [data, searchEmail, searchRole, searchActive]);


    const isResourceActive = (startDate, endDate) => {
        const currentDate = new Date();
        const startDateObj = new Date(startDate);
        const endDateObj = new Date(endDate);
        return currentDate >= startDateObj && currentDate <= endDateObj;
    };

    if (loading) {
        return <h1>Loading ....</h1>;
    }

    if (error !== null) {
        showAlertModal("Error!", "Failed to create resource. Please try again.");
    }

    if (data.length > 0) {
        return (
            <div>
                <div className="table">
                    <label style={{marginLeft: '20px', fontWeight: 'bold', fontSize: '20px'}}>Search by Email:</label>
                    <input
                        style={{marginLeft: '7px', marginBottom: '10px', height: '20px' }}
                        type="text"
                        value={searchEmail}
                        onChange={(e) => setSearchEmail(e.target.value)}
                    />
                    <label style={{marginLeft: '20px', fontWeight: 'bold', fontSize: '20px'}}>Search by Role:</label>
                    <select
                        style={{marginLeft: '7px', marginBottom: '10px', height: '25px'}}
                        value={searchRole}
                        onChange={(e) => setSearchRole(e.target.value)}
                    >
                        <option value="">All</option>
                        {roleOptions.map((option) => (
                            <option key={option} value={option}>{option}</option>
                        ))}
                    </select>
                    <label style={{marginLeft: '20px', fontWeight: 'bold', fontSize: '20px'}}>Filter by Active/Inactive:</label>
                    <select
                        style={{marginLeft: '7px', marginBottom: '10px', height: '25px'}}
                        value={searchActive}
                        onChange={(e) => setSearchActive(e.target.value)}
                    >
                        <option value="">All</option>
                        <option value="active">Active</option>
                        <option value="inactive">Inactive</option>
                    </select>
                </div>
                <table>
                    <TableHeaderResources/>
                    <TableBodyResources data={tableData} projectCode={projectCode}/>
                </table>
            </div>
        );
    }

    return <h1>No data ....</h1>;
}


export default TableResources;
