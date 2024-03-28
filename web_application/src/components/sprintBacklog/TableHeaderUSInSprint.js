import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";

/**
 Renders the header of a table with the column names passed as props.

 @param headers - An array of strings representing the names of the columns.
 @returns The rendered table header.
 */

const TableHeaderUSInSprint = () => {
    const { state } = useContext(AppContext);
    const { headersUS } = state;
    return (
        <thead className='table-header'>
        <tr>
            {/*<th style={{width:"50px"}}>{headersUS.projectCode}</th>*/}
            {/*<th style={{width:"100px"}}>{headersUS.userStoryID}</th>*/}
            <th style={{width:"100px", height:"30px"}}>{headersUS.userStoryNumber}</th>
            <th style={{width:"345px", height:"30px"}}>{headersUS.userStoryText}</th>
            {/*<th style={{width:"100px"}}>{headersUS.status}</th>*/}
            <th style={{width:"100px", height:"30px"}}>{headersUS.actor}</th>
            <th style={{width:"100px", height:"30px"}}>{headersUS.priority}</th>
        </tr>
        </thead>
    );
};

export default TableHeaderUSInSprint;
