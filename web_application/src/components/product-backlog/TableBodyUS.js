import React from 'react';

/**
 Renders the table body with the data passed as props.
 @returns The rendered table body.
 */

function TableBodyUS({data}) {
    const rows = data.map((row, index) => {
        return (
            <tr key={index} style={{cursor: "pointer", textAlign: 'center'}}>
                <td style={{ textAlign: 'center' }}>{row.userStoryNumber}</td>
                <td style={{ textAlign: 'center' }}>{row.userStoryText}</td>
                <td style={{ textAlign: 'center' }}>{row.status}</td>
                <td style={{ textAlign: 'center' }}>{row.actor}</td>
                <td style={{ textAlign: 'center' }}>{row.priority}</td>
            </tr>
        )
    });
    return (
        <tbody>{rows}</tbody>
    );
}

export default TableBodyUS;