import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";

/**
 Renders the header of a table with the column names passed as props.

 @param headers - An array of strings representing the names of the columns.
 @returns The rendered table header.
 */

const TableHeaderProj = () => {
    const { state } = useContext(AppContext);
    const { headersProjects } = state;
    return (
        <thead className='table-header'>
        <tr>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.name}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.description}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.customer}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.businessSector}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.typology}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.startDate}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.endDate}</th>
            <th style={{width:"75px", height:"30px"}}>{headersProjects.status}</th>
            <th style={{width:"75px", height:"30px"}}>{headersProjects.sprintDuration}</th>
            <th style={{width:"75px", height:"30px"}}>{headersProjects.numberOfPlannedSprints}</th>
            <th style={{width:"100px", height:"30px"}}>{headersProjects.budget}</th>
            <th style={{width:"100px", height:"30px"}}></th>
        </tr>
        </thead>
    );
};

export default TableHeaderProj;