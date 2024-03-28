import React from 'react';

/**
 Renders the header of a table with the column names passed as props.

 @param headers - An array of strings representing the names of the columns.
 @returns The rendered table header.
 */

const TableHeader = ({headers}) => {
    console.log('headers:', headers);
    return (
        <thead className='table-header'>
        <tr>
            {headers.map(header => <th key={header}>{header}</th>)}
        </tr>
        </thead>
    );
};

export default TableHeader;
