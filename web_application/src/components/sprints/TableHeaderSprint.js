import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";

const TableHeaderSprint = () => {
    const {state} = useContext(AppContext);
    const {headersSprint} = state;
    return (
        <thead className='table-header'>
        <tr>
            <th style={{width: "100px", height: "30px"}}>{headersSprint.sprintNumber}</th>
            <th style={{width: "100px", height: "30px"}}>{headersSprint.startDate}</th>
            <th style={{width: "100px", height: "30px"}}>{headersSprint.endDate}</th>
            <th style={{width: "100px", height: "30px"}}>{headersSprint.sprintStatus}</th>
            <th style={{width: "200px", height: "30px"}}></th>
        </tr>
        </thead>
    );
};

export default TableHeaderSprint;
