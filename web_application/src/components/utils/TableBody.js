import React from 'react';

/**
 Renders the table body with the data passed as props.

 @param body - An array of objects representing the rows of the table.
 @returns The rendered table body.
 */

function TableBody({ body }) {
    return (
        <tbody>
        {body.map((item, index) => (
            <tr key={index.code} className='table-body'>
               {Object.values(item).map((value,i) => (
                <td key={i}>{value}</td>
               ))}
            </tr>
        ))}
        </tbody>
    );
}

export default TableBody;