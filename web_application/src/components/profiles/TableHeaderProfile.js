import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";


const TableHeaderProfile = () => {
    const { state } = useContext(AppContext);
    const { headersProfile } = state;
    return (
        <thead className='table-header'>
        <tr>
            <th style={{width:"50px", height:"30px"}}>{headersProfile.profileID}</th>
            <th style={{width:"200px", height:"30px"}}>{headersProfile.description}</th>
        </tr>
        </thead>
    );
};

export default TableHeaderProfile;
