import React from 'react';

/**
 Renders the table body with the data passed as props.
 @returns The rendered table body.
 */

function TableBodyUSInSprint({data}) {
    const rows = data.map((row, index) => {
        return (
            <tr key={index} style={{cursor: "pointer"}}>
                <td style={{ textAlign: 'center' }}>{row.userStoryNumber}</td>
                <td style={{ textAlign: 'center' }}>{row.userStoryText}</td>
                <td style={{ textAlign: 'center' }}>{row.actor}</td>
                <td style={{ textAlign: 'center' }}>{row.priority}</td>
            </tr>
        )
    });
    return (
        <tbody>{rows}</tbody>
    );
}

export default TableBodyUSInSprint;