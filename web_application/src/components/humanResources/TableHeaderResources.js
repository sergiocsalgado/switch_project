import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";

const TableHeaderResources = () => {
    // Access the state from the AppContext using the useContext hook
    const {state} = useContext(AppContext);
    // Destructure the headersHumanResource from the state
    const {headersHumanResource} = state;

    return (
        <thead className='table-header'>
        <tr>
            {/* Render table header cells based on the headersHumanResource */}
            <th style={{width: "150px", height: "30px"}}>{headersHumanResource.email}</th>
            <th style={{width: "150px", height: "30px"}}>{headersHumanResource.role}</th>
            <th style={{width: "100px", height: "30px"}}>{headersHumanResource.allocation}</th>
            <th style={{width: "100px", height: "30px"}}>{headersHumanResource.costPerHour}</th>
            <th style={{width: "100px", height: "30px"}}>{headersHumanResource.currency}</th>
            <th style={{width: "150px", height: "30px"}}>{headersHumanResource.startDate}</th>
            <th style={{width: "150px", height: "30px"}}>{headersHumanResource.endDate}</th>
        </tr>
        </thead>
    );
};

export default TableHeaderResources;
